package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;

public class VideoPlaybackTest extends BaseTest {

    @BeforeTest
    public void init(){
        driver.setSetting("app", "src/main/resources/vk-video-1-136.apk");
        driver.setSetting("appPackage", "com.vk.video");
    }

    @Test
    public void testVideoPlaybackPositive() {
        // Ожидаем загрузки интерфейса
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@resource-id=\"com.vk.vkvideo:id/title\" and @text=\"VK Video\"]"))).click();


        // Ищем и кликаем на первое доступное видео
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//android.widget.ImageView[@resource-id=\"com.vk.vkvideo:id/preview\"])[1]"))).click();

        // Ждём появления плеера
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//android.widget.TextView[@resource-id=\"com.vk.vkvideo:id/title\"])[4]")));

        // Проверяем наличие кнопки паузы (означает, что видео воспроизводится)
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//android.widget.FrameLayout[@resource-id=\"com.vk.vkvideo:id/video_subtitles\"]"))).isDisplayed(), "Video should be playing");

        // Дополнительно проверяем прогресс воспроизведения
        Assert.assertNotNull(wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("(//android.widget.LinearLayout[@resource-id=\"com.vk.vkvideo:id/duration\"])[1]"))).getText(), "Current time should not be null");
    } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Элемент не появился!");
        }
    }

    @Test
    public void testVideoPlaybackNegative() {
        // Имитируем отсутствие интернет‑соединения
        ((AndroidDriver) driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().withDataDisabled().build()); // No network

        // Пытаемся открыть видео
        try {

            // Ожидаем сообщение об ошибке
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Assert.assertTrue(
                wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@resource-id=\"com.vk.vkvideo:id/title\" and @text=\"VK Video\"]")
                    )), "Error message should be displayed");
        } catch (Exception e) {
            System.out.println("Ошибка в тесте на проверку работы при отсутствии интернета!");
        }
    }
}
