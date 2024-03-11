package Pages.Clock;

import Logger.LoggerUtil;
import Pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockPage extends BasePage {
    private final By alarmLocator = By.xpath("//android.widget.TextView[@text=\"Alarm\"]");
    private static final By clockLocator = By.xpath("//android.widget.TextView[@text=\"Clock\"]");
    private By timeLocator(String time) {
        return By.xpath(MessageFormat.format("//android.widget.TextView[contains(@content-desc,\"{0}\")]", time));
    }

    public ClockPage(AndroidDriver driver) {
        super(driver, clockLocator);
    }

    public void getTimeElement(){
        var time = LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm"));

        LoggerUtil.logInfo("Find element with time");
        findElementAssertDisplayed(clockLocator, "Clock button").click();
        findElementAssertDisplayed(timeLocator(time), "Clock time");
    }

    public void clickAddAlarm() {
        findElementAssertDisplayed(alarmLocator, "Alarm").click();
    }
}
