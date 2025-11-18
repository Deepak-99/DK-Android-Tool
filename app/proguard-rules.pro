# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep the HawkshawInitializer and Hawkshaw classes
-keep class com.hawkshaw.library.HawkshawInitializer { *; }
-keep class com.hawkshaw.library.Hawkshaw { *; }

# Keep any annotated classes with @Keep
-keep class androidx.** { *; }
-keep class com.google.** { *; }
-keep class javax.** { *; }
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }

# Keep the FirebaseMessagingService subclasses
-keep public class * extends com.google.firebase.messaging.FirebaseMessagingService
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.app.Service 