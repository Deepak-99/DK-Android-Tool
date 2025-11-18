package com.hawkshaw.library.deviceinfo

import com.hawkshaw.library.logger.Logger
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.Collections
import java.util.UUID
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.ReentrantLock
import android.util.Log // Added for logging

/**
 * Singleton object for executing shell commands
 */
object Shell {
    private const val TAG = "Shell"
    private var shell: ShellInstance? = null

    init {
        Log.d(TAG, "[DEBUG] Shell object initializing.")
        try {
            shell = ShellInstance()
            Log.d(TAG, "[DEBUG] ShellInstance created successfully in init.")
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Shell init: Failed to initialize shell", e) // Debug log for exception
            Logger.e(TAG, "Failed to initialize shell", e, false, 12, null)
        }
    }

    /**
     * Executes a shell command and returns the result as a string
     *
     * @param command The shell command to execute
     * @return The output of the command as a string
     */
    fun runShellCommand(command: String): String {
        Log.d(TAG, "[DEBUG] runShellCommand called with command: \"$command\"")
        val shellInstance = shell ?: run {
            Log.e(TAG, "[DEBUG] runShellCommand: Shell not initialized, attempting to reinitialize.")
            try {
                shell = ShellInstance() // Attempt reinitialization
                Log.d(TAG, "[DEBUG] runShellCommand: Shell reinitialized successfully.")
                shell!! // Will throw if reinitialization failed
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] runShellCommand: Failed to reinitialize shell after it was null.", e)
                throw IllegalStateException("Shell not initialized and failed to reinitialize", e)
            }
        }
        
        // Check if the shell process has exited and reinitialize if needed
        try {
            val isAlive = shellInstance.isProcessAlive()
            Log.d(TAG, "[DEBUG] runShellCommand: shellInstance.isProcessAlive() returned: $isAlive")
            if (!isAlive) {
                Log.w(TAG, "[DEBUG] runShellCommand: Shell process was not alive. Reinitializing.")
                // Process has exited, reinitialize shell
                shell = ShellInstance()
                Log.d(TAG, "[DEBUG] runShellCommand: ShellInstance re-created after process was not alive.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] runShellCommand: Error checking shell process, reinitializing.", e) // Debug log for exception
            Logger.e(TAG, "Error checking shell process", e, false, 12, null)
            shell = ShellInstance() // Reinitialize shell on error
            Log.d(TAG, "[DEBUG] runShellCommand: ShellInstance re-created on error.")
        }

        val currentShell = shell ?: throw IllegalStateException("Shell is null after reinitialization checks.")
        Log.d(TAG, "[DEBUG] runShellCommand: Using shell instance: $currentShell")

        val uuid = UUID.randomUUID()
        Log.d(TAG, "[DEBUG] runShellCommand: Generated UUID: $uuid for command: \"$command\"")

        synchronized(currentShell) {
            Log.d(TAG, "[DEBUG] runShellCommand: Entered synchronized block. Shell state: ${currentShell.state}")
            if (currentShell.state == ShellState.SHUTDOWN) {
                Log.e(TAG, "[DEBUG] runShellCommand: Shell is shutdown. Throwing IllegalStateException.")
                throw IllegalStateException("The shell is shutdown")
            }
            
            // Create synchronized lists for stdout and stderr
            val stdout = Collections.synchronizedList(ArrayList<String>())
            val stderr = Collections.synchronizedList(ArrayList<String>())
            Log.d(TAG, "[DEBUG] runShellCommand: Initialized stdout and stderr lists.")

            // Create a countdown latch for synchronization
            val latch = CountDownLatch(2) // Expecting 2 countdowns (stdout & stderr markers)
            Log.d(TAG, "[DEBUG] runShellCommand: CountDownLatch created with count 2.")
            
            // Create a result holder
            val resultStatus = CommandResult().apply { 
                exitCode = 256 // Default to a non-zero exit code
            }
            Log.d(TAG, "[DEBUG] runShellCommand: CommandResult initialized with exitCode: ${resultStatus.exitCode}")

            // Setup process monitoring
            val processState = ProcessState(uuid, resultStatus, latch, true)
            val lock = ReentrantLock()
            val output = Collections.synchronizedList(ArrayList<String>()) // Combined output
            Log.d(TAG, "[DEBUG] runShellCommand: ProcessState, ReentrantLock, and combined output list created.")
            
            // Setup output handlers
            val stdoutHandler = OutputHandler(processState)
            val stderrHandler = OutputHandler(processState)
            
            // Capture stdout
            Log.d(TAG, "[DEBUG] runShellCommand: Setting up STDOUT handler.")
            stdoutHandler.setHandler(
                OutputConfig(
                    commandConfig = CommandConfig(uuid, null, OutputOption.STDOUT, OutputOption.STDERR, InputOption.STDIN, InputOption.PIPE, true),
                    output = stdout,
                    combinedOutput = output,
                    buffer = currentShell.getStdoutBuffer(),
                    lock = lock,
                    option = OutputOption.STDOUT
                )
            )
            
            // Capture stderr
            Log.d(TAG, "[DEBUG] runShellCommand: Setting up STDERR handler.")
            stderrHandler.setHandler(
                OutputConfig(
                    commandConfig = CommandConfig(uuid, null, OutputOption.STDOUT, OutputOption.STDERR, InputOption.STDIN, InputOption.PIPE, true),
                    output = stderr,
                    combinedOutput = output,
                    buffer = currentShell.getStderrBuffer(),
                    lock = lock,
                    option = OutputOption.STDERR
                )
            )
            
            val startTime = System.currentTimeMillis()
            Log.d(TAG, "[DEBUG] runShellCommand: Start time: $startTime")

            try {
                // Set shell state to busy
                Log.d(TAG, "[DEBUG] runShellCommand: Setting shell state to BUSY.")
                currentShell.state = ShellState.BUSY
                
                // Execute command with UUID markers to identify output
                val commandsToExecute = arrayOf(
                    command,
                    "echo '$uuid' \$?", // Marker for stdout and exit code of original command
                    "echo '$uuid' >&2"    // Marker for stderr
                )
                Log.d(TAG, "[DEBUG] runShellCommand: Executing commands: ${commandsToExecute.joinToString(";")}")

                currentShell.executeCommands(commandsToExecute)
                Log.d(TAG, "[DEBUG] runShellCommand: Waiting for latch.await(). Current latch count: ${latch.count}")
                latch.await() // Wait for completion (both stdout and stderr markers found)
                Log.d(TAG, "[DEBUG] runShellCommand: latch.await() completed. Exit code from resultStatus: ${resultStatus.exitCode}")
                
                // Set shell state back to idle
                Log.d(TAG, "[DEBUG] runShellCommand: Setting shell state to IDLE.")
                currentShell.state = ShellState.IDLE
            } catch (e: InterruptedException) {
                Log.e(TAG, "[DEBUG] runShellCommand: InterruptedException during latch.await().", e) // Debug log
                resultStatus.exitCode = 158 // Specific exit code for interruption
                processState.onComplete() // Ensure latch is decremented if not already fully done
                processState.onComplete() // Call again to be sure if one was missed due to interruption.
                currentShell.state = ShellState.IDLE
                Log.d(TAG, "[DEBUG] runShellCommand: Set shell state to IDLE after InterruptedException.")
            }
            
            // Check if we need to run an exit command to set the exit code
            // This part seems complex and potentially problematic as it tries to force an exit code.
            // The original command's exit code should be captured by "echo '$uuid' \$?"
            // For now, logging its behavior.
            if (resultStatus.exitCode != 0) {
                Log.d(TAG, "[DEBUG] runShellCommand: resultStatus.exitCode is ${resultStatus.exitCode} (non-zero). Executing 'exit ${resultStatus.exitCode}'.")
                val exitCommands = arrayOf("exit ${resultStatus.exitCode}")
                currentShell.executeCommands(exitCommands)
            } else {
                Log.d(TAG, "[DEBUG] runShellCommand: resultStatus.exitCode is 0. No explicit 'exit' command needed.")
            }
            
            // Process the output
            val commandOutput = CommandOutput(
                uuid = uuid,
                command = command,
                stdout = stdout,
                stderr = stderr,
                combinedOutput = output,
                exitCode = resultStatus.exitCode,
                startTime = startTime
            )
            Log.d(TAG, "[DEBUG] runShellCommand: CommandOutput created. ExitCode: ${commandOutput.exitCode}, Stdout lines: ${stdout.size}, Stderr lines: ${stderr.size}")

            // Return the output based on exit code
            return if (commandOutput.exitCode == 0) {
                Log.d(TAG, "[DEBUG] runShellCommand: Exit code is 0. Returning stdout.")
                commandOutput.stdout.joinToString("\n")
            } else {
                Log.w(TAG, "[DEBUG] runShellCommand: Exit code is ${commandOutput.exitCode}. Returning stderr.")
                commandOutput.stderr.joinToString("\n")
            }
        }
    }
}

// Internal classes for shell command execution
private enum class ShellState { IDLE, BUSY, SHUTDOWN }
private enum class OutputOption { STDOUT, STDERR }
private enum class InputOption { STDIN, PIPE }

private data class CommandConfig(
    val uuid: UUID,
    val callback: (() -> Unit)?,
    val stdoutOption: OutputOption,
    val stderrOption: OutputOption,
    val stdinOption: InputOption,
    val pipeOption: InputOption,
    val useCallback: Boolean
)

private data class OutputConfig(
    val commandConfig: CommandConfig,
    val output: MutableList<String>,
    val combinedOutput: MutableList<String>,
    val buffer: BufferedReader,
    val lock: ReentrantLock,
    val option: OutputOption
) {
    init {
        Log.d("Shell", "[DEBUG] OutputConfig created for option: $option, UUID: ${commandConfig.uuid}") // Potentially too verbose
    }
}

private class CommandResult {
    var exitCode: Int = 0
}

private class ProcessState(
    val uuid: UUID,
    val result: CommandResult,
    val latch: CountDownLatch,
    val isAsync: Boolean
) {
    fun onComplete() {
        Log.d("Shell", "[DEBUG] ProcessState.onComplete called for UUID: $uuid. Latch count before: ${latch.count}")
        latch.countDown()
        Log.d("Shell", "[DEBUG] ProcessState.onComplete: Latch count after: ${latch.count}")
    }
}

private class OutputHandler(private val processState: ProcessState) {
    private var handler: OutputConfig? = null
    
    fun setHandler(config: OutputConfig) {
        Log.d("Shell", "[DEBUG] OutputHandler.setHandler called for option: ${config.option}, UUID: ${config.commandConfig.uuid}")
        handler = config
        // Actual stream reading logic is missing from this snippet, but if it were here, it would be logged.
        // For example, if a thread was started here to read from config.buffer:
        // Thread { readLoop(config) }.start()
        // And readLoop would log lines and call processState.onComplete when marker is found.
    }
}

private class ShellInstance {
    private val TAG_INSTANCE = "ShellInstance" // Separate tag for clarity
    private val process: java.lang.Process
    private val stdoutBuffer: BufferedReader
    private val stderrBuffer: BufferedReader
    private val outputWriter: OutputStreamWriter
    var state: ShellState = ShellState.IDLE
    
    init {
        Log.d(TAG_INSTANCE, "[DEBUG] ShellInstance init: Executing 'sh'.")
        process = Runtime.getRuntime().exec("sh")
        stdoutBuffer = BufferedReader(InputStreamReader(process.inputStream))
        stderrBuffer = BufferedReader(InputStreamReader(process.errorStream))
        outputWriter = OutputStreamWriter(process.outputStream)
        Log.d(TAG_INSTANCE, "[DEBUG] ShellInstance init: Process and streams initialized. State: $state")
    }

    // Add getter methods for the buffers
    fun getStdoutBuffer(): BufferedReader = stdoutBuffer
    fun getStderrBuffer(): BufferedReader = stderrBuffer
    
    fun isProcessAlive(): Boolean {
        Log.d(TAG_INSTANCE, "[DEBUG] isProcessAlive called.")
        return try {
            val exitVal = process.exitValue()
            Log.d(TAG_INSTANCE, "[DEBUG] isProcessAlive: Process exited with value: $exitVal. Returning false (not alive).")
            false
        } catch (e: IllegalThreadStateException) {
            Log.d(TAG_INSTANCE, "[DEBUG] isProcessAlive: IllegalThreadStateException caught. Process is alive. Returning true.")
            true
        }
    }
    
    fun executeCommands(commands: Array<String>) {
        Log.d(TAG_INSTANCE, "[DEBUG] executeCommands called with ${commands.size} commands.")
        commands.forEach { command ->
            Log.d(TAG_INSTANCE, "[DEBUG] executeCommands: Writing command: \"$command\"")
            outputWriter.write("$command\n")
            outputWriter.flush()
            Log.d(TAG_INSTANCE, "[DEBUG] executeCommands: Flushed command: \"$command\"")
        }
        Log.d(TAG_INSTANCE, "[DEBUG] executeCommands: Finished writing all commands.")
    }
    
    fun cleanup() {
        Log.d(TAG_INSTANCE, "[DEBUG] cleanup called. Current state: $state")
        state = ShellState.SHUTDOWN
        Log.d(TAG_INSTANCE, "[DEBUG] cleanup: State set to SHUTDOWN.")
        try {
            Log.d(TAG_INSTANCE, "[DEBUG] cleanup: Closing stdoutBuffer.")
            stdoutBuffer.close()
            Log.d(TAG_INSTANCE, "[DEBUG] cleanup: Closing stderrBuffer.")
            stderrBuffer.close()
            Log.d(TAG_INSTANCE, "[DEBUG] cleanup: Closing outputWriter.")
            outputWriter.close()
            Log.d(TAG_INSTANCE, "[DEBUG] cleanup: Destroying process.")
            process.destroy()
            Log.d(TAG_INSTANCE, "[DEBUG] cleanup: Process destroyed.")
        } catch (e: Exception) {
            Log.e(TAG_INSTANCE, "[DEBUG] cleanup: Error during cleanup", e) // Debug log for exception
            Logger.e("ShellInstance", "Error cleaning up shell instance", e, false, 12, null)
        }
    }
}

private data class CommandOutput(
    val uuid: UUID,
    val command: String,
    val stdout: List<String>,
    val stderr: List<String>,
    val combinedOutput: List<String>,
    val exitCode: Int,
    val startTime: Long
)

