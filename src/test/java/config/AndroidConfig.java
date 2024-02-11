package config;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidConfig {
    private static String  ANDROID_DEVICE_NAME = "Android";
    private static String  ANDROID_APP_PACKAGE = "com.wdiodemoapp";
    private static String  ANDROID_APP_MAIN_ACTIVITY = "com.wdiodemoapp.MainActivity";
    private static String  ANDROID_PLATFORM_NAME = "Android";
    private static boolean ANDROID_RESET_CONFIG = false;

    //please change the android device id and platform version of android
    private static String  ANDROID_DEVICE_ID = "RR8R801GPNZ";
    private static String  ANDROID_PLATFORM_VERSION = "13.0";

    public static DesiredCapabilities setupAndroidConnection(){
        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            caps.setCapability("deviceName", ANDROID_DEVICE_NAME);
            caps.setCapability("appPackage", ANDROID_APP_PACKAGE);
            caps.setCapability("appActivity", ANDROID_APP_MAIN_ACTIVITY);
            caps.setCapability("platformName", ANDROID_PLATFORM_NAME);
            caps.setCapability("udid", ANDROID_DEVICE_ID);
            caps.setCapability("platformVersion", ANDROID_PLATFORM_VERSION);
            caps.setCapability("noReset", ANDROID_RESET_CONFIG);
            return caps;
        }catch (Exception e){
           return caps;
        }
    }
}
