package Pages.Messages;

import Pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SettingsPage extends BasePage {
    private By tAcLocator = By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"About, terms & privacy\"]");

    public SettingsPage(AndroidDriver driver) {
        super(driver, By.xpath("//android.support.v7.widget.RecyclerView[@resource-id=\"com.google.android.apps.messaging:id/recycler_view\"]"));
    }

    public void clickTermsAndConditions(){
        findElementAssertDisplayed(tAcLocator, "Settings view").click();
    }
}
