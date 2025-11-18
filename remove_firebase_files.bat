@echo off
echo Removing Firebase-related files from DK Hawkshaw App...

REM Remove Firebase FCM related files
if exist "app\src\main\java\com\hawkshaw\library\fcm\Firebase.kt" del "app\src\main\java\com\hawkshaw\library\fcm\Firebase.kt"
if exist "app\src\main\java\com\hawkshaw\library\fcm\MyFirebaseMessagingService.kt" del "app\src\main\java\com\hawkshaw\library\fcm\MyFirebaseMessagingService.kt"
if exist "app\src\main\java\com\hawkshaw\library\fcm\Pushy.kt" del "app\src\main\java\com\hawkshaw\library\fcm\Pushy.kt"

REM Remove Firebase configuration files
if exist "app\google-services.json" del "app\google-services.json"
if exist "app\src\main\java\com\hawkshaw\library\config\RemoteConfig.kt" del "app\src\main\java\com\hawkshaw\library\config\RemoteConfig.kt"

REM Remove Firebase model files
if exist "app\src\main\java\com\hawkshaw\library\datalayer\models\PushFCMTokenRequest.kt" del "app\src\main\java\com\hawkshaw\library\datalayer\models\PushFCMTokenRequest.kt"
if exist "app\src\main\java\com\hawkshaw\library\datalayer\models\PushFCMTokenResponse.kt" del "app\src\main\java\com\hawkshaw\library\datalayer\models\PushFCMTokenResponse.kt"

REM Remove Firebase resource files
if exist "app\src\main\res\raw\firebase_common_keep.xml" del "app\src\main\res\raw\firebase_common_keep.xml"

echo Firebase files removed successfully!
echo.
echo Note: You may need to manually clean up any remaining Firebase references in:
echo - AndroidManifest.xml
echo - Config.kt
echo - Any other files that import Firebase classes
echo.
pause
