package Pages.Mastodon;

import Pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MastodonInitialPage extends BasePage {
    private final By learnMoreButtonLocator = By.xpath("//android.widget.Button[@resource-id=\"org.joinmastodon.android:id/btn_learn_more\"]");
    private final By learnMorePanelLocator = By.xpath("//android.widget.TextView[@text=\"Welcome to Mastodon\"]//parent::android.widget.LinearLayout");

    public MastodonInitialPage(AndroidDriver driver, By locator) {
        super(driver, locator);
    }

    public WebElement GetLearnMoreButton(){
        return findElementAssertDisplayed(learnMoreButtonLocator,"Learn more view");
    }

    public WebElement GetLearnMorePanel(){
        return findElementAssertDisplayed(learnMorePanelLocator, "Learn more panel");
    }
}
