package Steps;

import Enums.SwipeSideEnum;
import Logger.LoggerUtil;
import Pages.Mastodon.MastodonInitialPage;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class MastodonSteps extends BaseSteps{
    @Getter(lazy = true)
    private final MastodonInitialPage mastodonInitialPage = new MastodonInitialPage(driver,
            By.xpath("//android.widget.FrameLayout[@resource-id=\"org.joinmastodon.android:id/fragment_wrap\"]" +
                    "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ImageView"));

    public MastodonSteps(AndroidDriver driver){
        super(driver);
    }

    public void ClickLearnMore(){
        LoggerUtil.logInfo("Open mastodon and open learn more");
        getMastodonInitialPage().GetLearnMoreButton().click();
    }

    public void FindLearnMoreViewAndClose(){
        LoggerUtil.logInfo("Find opened learn more panel and assert it displayed");
        var panel = getMastodonInitialPage().GetLearnMorePanel();

        LoggerUtil.logInfo("Close panel with swipe");
        performSwipeOnElement(SwipeSideEnum.Down, panel);

        LoggerUtil.logInfo("Wait for panel to became hidden");
        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.invisibilityOf(panel)));
    }
}
