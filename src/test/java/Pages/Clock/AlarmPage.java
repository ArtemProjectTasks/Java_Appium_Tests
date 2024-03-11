package Pages.Clock;

import Logger.LoggerUtil;
import Pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.MessageFormat;

public class AlarmPage extends BasePage {
    private final By addAlarmButtonLocator = By.xpath("//android.widget.ImageButton[@content-desc=\"Add alarm\"]");
    private By alarmHourButton(String time) {
        return By.xpath(MessageFormat.format(
                "//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"{0}\"]",
                time));
    }
    private final By alarmMinutes = By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"30\"]");
    private final By alarmOKButton = By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");

    private By alarm(String time) {
        return By.xpath(MessageFormat.format(
                "//android.view.ViewGroup//android.widget.TextView[contains(@content-desc,\"{0}:30\")]",
                time));
    }

    public AlarmPage(AndroidDriver driver) {
        super(driver, By.xpath("//android.support.v7.widget.RecyclerView[@resource-id=\"com.google.android.deskclock:id/alarm_recycler_view\"]"));
    }

    public void setTimeOnTheAlarm(String time){
        findElementAssertDisplayed(alarmHourButton(time),
                MessageFormat.format("{0} hours button", time))
                .click();
    }

    public void setAlarmMinutesTo30(){
        findElementAssertDisplayed(alarmMinutes, "30 minutes button").click();
    }

    public void pressOkOnTheAlarmAddView() {
        findElementAssertDisplayed(alarmOKButton, "Ok button").click();
    }

    public WebElement getTheAlarmByTime(String time){
        LoggerUtil.logInfo("Find newly created alarm with time");
        return findElementAssertDisplayed(alarm(time), "Alarm", 20);
    }

    public void clickAddAlarm(){
        findElementAssertDisplayed(addAlarmButtonLocator, "Add alarm", 20).click();
    }

    public WebElement getToggle(String time){
        return findElementAssertDisplayed(By.xpath(
                        MessageFormat.format(
                                "//android.view.ViewGroup//android.widget.TextView[contains(@content-desc,\"{0}:30\")]",
                                time) + "/parent::android.view.ViewGroup" +
                        "//android.widget.Switch[contains(@resource-id,\"com.google.android.deskclock:id/onoff\")]"),
                "Alarm toggle");
    }
}
