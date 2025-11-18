package com.hawkshaw.library.datalayer.room.files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.room.converters.FileRequestSourceConverter
import com.hawkshaw.library.datalayer.room.converters.FileRequestMediumConverter
import kotlinx.serialization.Serializable
import java.io.File

/**
 * Entity representing a file upload task
 */
@Entity(tableName = "push_file_task_entity")
@TypeConverters(FileRequestSourceConverter::class, FileRequestMediumConverter::class)
@Serializable
data class PushFileTaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "path")
    var path: String = "",

    @ColumnInfo(name = "source")
    var source: Command.FileRequest.Source = Command.FileRequest.Source.Unknown,

    @ColumnInfo(name = "medium")
    var medium: Command.FileRequest.Medium = Command.FileRequest.Medium.Grpc,

    @ColumnInfo(name = "buffer")
    var buffer: Int = 1048576,

    @ColumnInfo(name = "length")
    var length: Long = 0,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "last_push_timestamp")
    var lastPushTimestamp: Long = 0,

    @ColumnInfo(name = "priority")
    var priority: Int = 1,

    @ColumnInfo(name = "retry_count")
    var retryCount: Int = 0,

    @ColumnInfo(name = "error_message")
    var errorMessage: String = ""
) {
    /**
     * Update the length based on the file path
     */
    fun updateLength() {
        length = try {
            File(path).length()
        } catch (e: Exception) {
            0
        }
    }
} 