package Steps;

import Enums.SwipeSideEnum;
import Logger.LoggerUtil;
import Pages.Clock.AlarmPage;
import Pages.Clock.ClockPage;
import Pages.Clock.TimerPage;
import io.appium.java_client.android.AndroidDriver;
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

    public void openClockPageAndFindTime(){
        getClockPage().getTimeElement();
    }

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

    public void assertThatAlarmIsSet(String time){
        LoggerUtil.logInfo("Verify that it is toggled ON");
        getAlarmPage().getTheAlarmByTime(time);
        //Chain locator don't work here, for no apparent reason
        var toggle = getAlarmPage().getToggle(time);
        assertEquals("ON", toggle.getText());
    }

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
