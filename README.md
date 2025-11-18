# Hawkshaw Android App

This is a Kotlin-based Android application that provides device management functionality.

## Project Structure

The project is organized as follows:

- `src/main/java/com/hawkshaw/app` - Main application package
- `src/main/java/com/hawkshaw/library` - Library code with core functionality
- `src/main/res` - Android resources (layouts, strings, drawables, etc.)
- `src/main/assets` - App assets including configuration

## Requirements

- Android Studio Arctic Fox (2020.3.1) or newer
- Kotlin 1.7.10 or newer
- Gradle 7.2.2 or newer
- Android SDK 33 (Android 13) or newer
- JDK 11 or newer

## Project Setup Instructions

This project has been restructured to fix build errors. Follow these steps to open and build the project in Android Studio:

1. Open Android Studio
2. Select "File" > "Open" and navigate to the project directory
3. Allow Android Studio to sync the project
4. Android Studio will automatically detect and configure your Android SDK path in local.properties
5. If prompted, update the Gradle version or plugins as recommended by Android Studio

## Fixes Applied

The following fixes have been applied to resolve build errors:

1. Updated Gradle wrapper to version 8.0
2. Updated build.gradle files to be compatible with Gradle 8.0
3. Fixed XML resource issues in attrs.xml (replaced invalid resource references)
4. Removed package attribute from AndroidManifest.xml (using the namespace in app/build.gradle instead)
5. Added proper directory structure for Kotlin files
6. Created basic MainActivity and App classes
7. Added settings.gradle with proper pluginManagement configuration

## Additional Configuration

If you still encounter build issues:

1. Make sure you have Android SDK installed and set up properly
2. In Android Studio, go to File > Settings > Build, Execution, Deployment > Build Tools > Gradle to configure Gradle settings
3. Set "Gradle JDK" to Java 11 or newer

## Known Issues

When building on a path with spaces or special characters:
- Add `android.nonFinalResIds=false` to gradle.properties (already done)
- Make sure SDK path in local.properties uses escaped backslashes (e.g., `C:\\Users\\username\\AppData\\Local\\Android\\Sdk`)

## Building the App

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Build the project by selecting "Build > Make Project"
5. Run the app by selecting "Run > Run 'app'"

## Features

- Device information gathering
- Location tracking
- System overlay functionality
- Accessibility service for device management
- Background job scheduling
- Firebase integration

## Converting from Java to Kotlin

This project has been fully converted from Java to Kotlin, providing:

- Modern Kotlin language features
- Coroutines for asynchronous operations
- Extension functions
- Null safety
- Property delegation
- Data classes
- Sealed classes
- Object singletons

## Gradle Dependencies

The project uses the following key dependencies:

- AndroidX Core and AppCompat
- Material Design components
- ConstraintLayout
- Lifecycle components
- Kotlin Coroutines
- Firebase Messaging
- Room Database

## License

This project is proprietary and confidential. 