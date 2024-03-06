import Logger.LoggerUtil;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("ALL")
public class TestsAppium extends BaseTest {
    public TestsAppium(){
        super();
        LoggerUtil.logInfo("Run tests");
    }

    @Test(groups = "Mastodon")
    @Description("Mastodon app swipe test")
    public void openAppAndOpenLearnMoreThenClose() {
        //Arrange
        LoggerUtil.logInfo("Open mastodon and open learn more");
        var learnMore = findElementAssertDisplayed(By.xpath(
                "//android.widget.Button[@resource-id=\"org.joinmastodon.android:id/btn_learn_more\"]"),
                "Learn more view");
        learnMore.click();

        //Act
        LoggerUtil.logInfo("Find opened learn more panel and assert it displayed");
        var learnMorePanelLocator = By.xpath("//android.widget.TextView[@text=\"Welcome to Mastodon\"]//parent::android.widget.LinearLayout");
        var learnMorePanel = findElementAssertDisplayed(learnMorePanelLocator, "Learn more panel");

        LoggerUtil.logInfo("Hide the panel using swipe");
        HashMap<String, Object> swipeArgs = new HashMap<>();
        swipeArgs.put("direction", "down");
        swipeArgs.put("element", ((RemoteWebElement)learnMorePanel).getId());
        swipeArgs.put("percent", 0.5);

        driver.executeScript("mobile: swipeGesture", swipeArgs);

        //Assert
        LoggerUtil.logInfo("Wait for panel to became hidden");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        assertTrue(wait.until((driver) ->
        {
            return driver.findElements(learnMorePanelLocator).isEmpty();
        }));
    }

    @Test(groups = "Messages")
    @Description("Messages app test")
    public void openMessagesAndCheckTermsAndConditions(){
        LoggerUtil.logInfo("Open messages app");

        findElementAssertDisplayed(By.xpath(
                "//android.widget.ImageView[@content-desc=\"More options\"]"),
                "More options")
                .click();

        findElementAssertDisplayed(By.xpath(
                "//*[contains(@text, 'Settings')]//ancestor::android.widget.LinearLayout[@resource-id=\"com.google.android.apps.messaging:id/content\"]"),
                "Settings button")
                .click();

        findElementAssertDisplayed(By.xpath(
                "//android.support.v7.widget.RecyclerView[@resource-id=\"com.google.android.apps.messaging:id/recycler_view\"]"),
                "Settings view");

        findElementAssertDisplayed(By.xpath(
                "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"About, terms & privacy\"]"),
                "Terms and services button")
                .click();

        var termsAndServices = findElementAssertDisplayed(By.xpath(
                "//android.widget.FrameLayout[@resource-id=\"android:id/list_container\"]"),
                "Terms and services view");

        LoggerUtil.logInfo("Find element version nama and asset visibility");
        var termsAndServicesVersionInfo = termsAndServices.findElement(By.xpath(
                "//android.widget.LinearLayout[1]/android.widget.RelativeLayout"));
        assertTrue(termsAndServicesVersionInfo.isDisplayed());
    }

    private WebElement findElementAssertDisplayed(By locator, String elementName){
        LoggerUtil.logInfo(MessageFormat.format("Find element: {0} and assert visibility", elementName));
        var element = driver.findElement(locator);
        assertTrue(element.isDisplayed());

        return element;
    }
}
