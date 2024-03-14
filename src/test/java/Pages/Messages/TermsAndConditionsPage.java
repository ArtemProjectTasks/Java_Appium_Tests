package Pages.Messages;

import Pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TermsAndConditionsPage extends BasePage {
    private final By termsAndServicesVersionInfoLocator = By.xpath("//android.widget.LinearLayout[1]/android.widget.RelativeLayout");
    public TermsAndConditionsPage(AndroidDriver driver) {
        super(driver, By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/list_container\"]"));
    }

    public WebElement getTermsAndServicesVersionInfo(){
        return findElementAssertDisplayed(termsAndServicesVersionInfoLocator, "Terms and Conditions additional info");
    }
}
