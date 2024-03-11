package Tests;

import Logger.LoggerUtil;
import Steps.MastodonSteps;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class MastodonTests extends BaseTest {
    private final MastodonSteps mastodonSteps;

    public MastodonTests() throws MalformedURLException {
        super();
        LoggerUtil.logInfo("Run Mastodon tests");
        mastodonSteps = new MastodonSteps(driver);
    }

    @Test
    @Description("Mastodon app swipe test")
    public void openAppAndOpenLearnMoreThenClose() {
        mastodonSteps.ClickLearnMore();
        mastodonSteps.FindLearnMoreViewAndClose();
    }
}
