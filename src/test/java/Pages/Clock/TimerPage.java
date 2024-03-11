package Pages.Clock;

import Pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class TimerPage extends BasePage {
    public TimerPage(AndroidDriver driver) {
        super(driver, By.xpath(
                "//android.widget.LinearLayout[@resource-id=\"com.google.android.deskclock:id/timer_setup\"]" +
                        "/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout"));
    }
}
