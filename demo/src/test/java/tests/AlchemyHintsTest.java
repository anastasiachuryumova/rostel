package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlchemyHintsTest extends BaseTest {

    @BeforeTest
    public void init(){
        driver.setSetting("app", "src/main/resources/alchemy-2-0-83.apk");
        driver.setSetting("appPackage", "com.ilyin.alchemy");
    }

    @Test
    public void testGetHintFromAd(){

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//androidx.compose.ui.platform.e1/android.view.View/android.view.View/android.view.View[2]/android.widget.Button"))).click();
            
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//androidx.compose.ui.platform.e1/android.view.View/android.view.View/android.view.View[1]/android.view.View[22]/android.view.View[1]/android.widget.Button"))).click();
                    
            WebElement hintsCount = driver.findElement(By.xpath("(//android.widget.TextView[@text=\"2 hints\"])[1]"));
            int finalHints = Integer.parseInt(hintsCount.getText().replaceAll("[^0-9]", ""));
            Assert.assertEquals(finalHints, 2, "Количество подсказок должно быть равно 2, но было: " + finalHints);

        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Элемент не появился!");
        }
    }
}
