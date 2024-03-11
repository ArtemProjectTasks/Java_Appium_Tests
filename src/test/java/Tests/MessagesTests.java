package Tests;

import Logger.LoggerUtil;
import Steps.MessagesSteps;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class MessagesTests extends BaseTest {
    private final MessagesSteps messagesSteps;

    public MessagesTests() throws MalformedURLException {
        super();
        LoggerUtil.logInfo("Run Messages tests");
        messagesSteps = new MessagesSteps(driver);
    }

    @Test
    public void openMessagesAndCheckTermsAndConditions(){
        LoggerUtil.logInfo("Open messages app");
        messagesSteps.navigateToSettings();
        messagesSteps.navigateToTermsAndConditions();
    }
}
