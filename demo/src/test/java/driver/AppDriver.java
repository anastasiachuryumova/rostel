package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppDriver {

    private static AppiumDriverLocalService getAppium(){
        return new AppiumServiceBuilder()
        .withIPAddress("127.0.0.1")
        .usingPort(4723)
        .build();
    }

    public static AppiumDriver setupDriver() throws Exception {
        var appiumLocalService = getAppium();
        appiumLocalService.start();
        UiAutomator2Options options = new UiAutomator2Options()
        .setPlatformName("Android")
        .setPlatformVersion("16.0")
        .setDeviceName("emulator-5554")
        .setAutomationName("UiAutomator2");
        return new AndroidDriver(appiumLocalService, options);
    }

    public static void stopAppium(){
        getAppium().stop();
    }
}