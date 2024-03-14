package Tests;

import Logger.LoggerUtil;
import Steps.ClockSteps;
import io.qameta.allure.Feature;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Feature(value = "Clock")
public class ClockTests extends BaseTest {
    private final ClockSteps clockSteps;

    public ClockTests() throws MalformedURLException {
        super();
        LoggerUtil.logInfo("Run Clock tests");
        clockSteps = new ClockSteps(driver);
    }

    @Test
    @Description("Clock assertion and alarm set up")
    public void clockTest() {
        clockSteps.openClockPageAndFindTime();

        var timeForAlarm = LocalTime.now()
                .plusHours(new Random().nextInt(12) + 1)
                .format(DateTimeFormatter.ofPattern("h"));

        clockSteps.openAlarmPageAndSetTime(timeForAlarm);

        clockSteps.assertThatAlarmIsSet(timeForAlarm);

        clockSteps.SwipeToTheTimerPage();
    }
}
