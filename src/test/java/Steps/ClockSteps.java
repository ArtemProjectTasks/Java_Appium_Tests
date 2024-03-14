package Steps;

import Enums.SwipeSideEnum;
import Logger.LoggerUtil;
import Pages.Clock.AlarmPage;
import Pages.Clock.ClockPage;
import Pages.Clock.TimerPage;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import java.text.MessageFormat;

import static org.testng.AssertJUnit.assertEquals;

public class ClockSteps extends BaseSteps{
    @Getter(lazy = true)
    private final ClockPage clockPage = new ClockPage(driver);
    @Getter(lazy = true)
    private final AlarmPage alarmPage = new AlarmPage(driver);
    @Getter(lazy = true)
    private final TimerPage timerPage = new TimerPage(driver);

    public ClockSteps(AndroidDriver driver) {
        super(driver);
    }

    @Step
    public void openClockPageAndFindTime(){
        getClockPage().getTimeElement();
    }

    @Step
    public void openAlarmPageAndSetTime(String time){
        LoggerUtil.logInfo("Find add alarm button and click");
        getClockPage().clickAddAlarm();

        getAlarmPage().clickAddAlarm();

        LoggerUtil.logInfo(MessageFormat.format("Set alarm to {0} AM/PM", time));
        getAlarmPage().setTimeOnTheAlarm(time);

        LoggerUtil.logInfo("Set alarm to 30 minutes");
        getAlarmPage().setAlarmMinutesTo30();

        LoggerUtil.logInfo("Press ok");
        getAlarmPage().pressOkOnTheAlarmAddView();
    }

    @Step
    public void assertThatAlarmIsSet(String time){
        LoggerUtil.logInfo("Verify that it is toggled ON");
        getAlarmPage().getTheAlarmByTime(time);
        //Chain locator don't work here, for no apparent reason
        var toggle = getAlarmPage().getToggle(time);
        assertEquals("ON", toggle.getText());
    }

    @Step
    public void SwipeToTheTimerPage(){
        LoggerUtil.logInfo("Swipe to the right, to the Timer");
        var screenElement = driver.findElement(By.xpath(
                        "/hierarchy/android.widget.FrameLayout"));
        performSwipeOnElement(SwipeSideEnum.Left, screenElement);
        performSwipeOnElement(SwipeSideEnum.Left, screenElement);

        LoggerUtil.logInfo("Verify that timer element is present");
        var timerPage = getTimerPage();
    }
}
