package Pages.Messages;

import Pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MessagesPage extends BasePage {
    private By moreOptionsLocator = By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]");
    private By settingsLocator = By.xpath("//*[contains(@text, 'Settings')]//ancestor::android.widget.LinearLayout[@resource-id=\"com.google.android.apps.messaging:id/content\"]");

    public MessagesPage(AndroidDriver driver) {
        super(driver, By.xpath("//android.support.v7.widget.RecyclerView[@content-desc=\"Conversation list\"]"));
    }

    public void clickMoreOptions(){
        findElementAssertDisplayed(moreOptionsLocator,"More options").click();
    }

    public void clickSettingsInMoreMenuOptions(){
        findElementAssertDisplayed(settingsLocator,"Settings button").click();
    }
}
