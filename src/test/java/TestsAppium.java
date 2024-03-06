import Logger.LoggerUtil;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

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

        LoggerUtil.logInfo("Find element version name and asset visibility");
        var termsAndServicesVersionInfo = termsAndServices.findElement(By.xpath(
                "//android.widget.LinearLayout[1]/android.widget.RelativeLayout"));
        assertTrue(termsAndServicesVersionInfo.isDisplayed());
    }

    @Test(groups = "Clock")
    @Description("Clock assertion and alarm set up")
    public void clockTest(){
        LoggerUtil.logInfo("Open clock app on the clock page");
        findElementAssertDisplayed(By.xpath(
                "//android.widget.TextView[@text=\"Clock\"]"),
                "Clock button")
                .click();

        LoggerUtil.logInfo("Find element with time");
        var time = LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm"));
        findElementAssertDisplayed(By.xpath(
                MessageFormat.format("//android.widget.TextView[contains(@content-desc,\"{0}\")]",
                        time)),
                "Clock time");

        LoggerUtil.logInfo("Find alarm button and click");
        findElementAssertDisplayed(By.xpath(
                "//android.widget.TextView[@text=\"Alarm\"]"),
                "Alarm")
                .click();

        LoggerUtil.logInfo("Find add alarm button and click");
        findElementAssertDisplayed(By.xpath(
                "//android.widget.ImageButton[@content-desc=\"Add alarm\"]"),
                "Add alarm button")
                .click();

        var timeForAlarm = LocalTime.now()
                .plusHours(new Random().nextInt(12) + 1)
                .format(DateTimeFormatter.ofPattern("h"));

        LoggerUtil.logInfo(MessageFormat.format("Set alarm to {0} AM/PM", timeForAlarm));
        findElementAssertDisplayed(By.xpath(
                        MessageFormat.format(
                                "//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"{0}\"]",
                                timeForAlarm)),
                MessageFormat.format("{0} hours button", timeForAlarm))
                .click();

        LoggerUtil.logInfo("Set alarm to 30 minutes");
        findElementAssertDisplayed(By.xpath(
                "//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"30\"]"),
                "30 minutes button")
                .click();

        LoggerUtil.logInfo("Press ok");
        findElementAssertDisplayed(By.xpath(
                "//android.widget.Button[@resource-id=\"android:id/button1\"]"),
                "Ok button")
                .click();

        LoggerUtil.logInfo("Find newly created alarm with time");
        String locator = MessageFormat.format(
                "//android.view.ViewGroup//android.widget.TextView[contains(@content-desc,\"{0}:30\")]",
                timeForAlarm);
        var alarmElement = findElementAssertDisplayed(By.xpath(locator),
                "Alarm",
                20);

        LoggerUtil.logInfo("Verify that it is toggled ON");
        //Chain locator don't work here, for no apparent reason
        var toggle = findElementAssertDisplayed(By.xpath(
                locator + "/parent::android.view.ViewGroup" +
                        "//android.widget.Switch[contains(@resource-id,\"com.google.android.deskclock:id/onoff\")]"),
                "Alarm toggle");
        assertTrue(toggle.getText().equals("ON"));

        LoggerUtil.logInfo("Swipe to the right, to the Timer");
        var screenElement = findElementAssertDisplayed(By.xpath(
                "/hierarchy/android.widget.FrameLayout"),
                "Screen base element");

        HashMap<String, Object> swipeArgs = new HashMap<>();
        swipeArgs.put("direction", "left");
        swipeArgs.put("element", ((RemoteWebElement)screenElement).getId());
        swipeArgs.put("percent", 0.5);

        driver.executeScript("mobile: swipeGesture", swipeArgs);
        driver.executeScript("mobile: swipeGesture", swipeArgs);

        LoggerUtil.logInfo("Verify that timer element is present");
        findElementAssertDisplayed(By.xpath(
                "//android.widget.LinearLayout[@resource-id=\"com.google.android.deskclock:id/timer_setup\"]" +
                        "/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout"),
                "Timer");
    }

    private WebElement findElementAssertDisplayed(By locator, String elementName){
        return getWebElement(elementName, Duration.ofSeconds(8), locator);
    }

    private WebElement findElementAssertDisplayed(By locator, String elementName, long customExtendedWait){
        return getWebElement(elementName, Duration.ofSeconds(customExtendedWait), locator);
    }

    private WebElement getWebElement(String elementName, Duration customExtendedWait, By locator) {
        LoggerUtil.logInfo(MessageFormat.format("Find element: {0} and assert visibility", elementName));
        WebDriverWait wait = new WebDriverWait(driver, customExtendedWait);
        var element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        assertTrue(element.isDisplayed());

        return element;
    }
}
