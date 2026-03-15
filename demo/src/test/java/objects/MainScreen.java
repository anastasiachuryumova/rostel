package objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainScreen {

    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.e1/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
    private SelenideElement playButton;

    public void clickPlayButton() {
        playButton.shouldBe(Condition.visible).click();
    }
}
