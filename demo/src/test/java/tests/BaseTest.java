package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import driver.AppDriver;
import io.appium.java_client.AppiumDriver;

public class BaseTest {

    protected static AppiumDriver driver;

    @BeforeSuite
    public void setUp() throws Exception {
        driver = AppDriver.setupDriver();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            AppDriver.stopAppium();
        }
    }
    
}
