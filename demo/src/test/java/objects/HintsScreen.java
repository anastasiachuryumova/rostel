package objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class HintsScreen {

    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.e1/android.view.View/android.view.View/android.view.View[1]/android.view.View[22]/android.view.View[1]/android.widget.Button")
    private SelenideElement watchAdButton;

    //androidx.compose.ui.platform.e1/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.Button

    @AndroidFindBy(id = "(//android.widget.TextView[@text=\"2 hints\"])[1]")
    private SelenideElement hintsCount;

    public void getHintFromAd() {
        watchAdButton.shouldBe(Condition.visible).click();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
        }
    }

    public int getHintsCount() {
        String countText = hintsCount.shouldBe(Condition.visible).getText();
        return Integer.parseInt(countText);
    }
}
