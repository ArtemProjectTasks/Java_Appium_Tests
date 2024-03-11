package Steps;

import Logger.LoggerUtil;
import Pages.Messages.MessagesPage;
import Pages.Messages.SettingsPage;
import Pages.Messages.TermsAndConditionsPage;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;

import static org.testng.AssertJUnit.assertTrue;

public class MessagesSteps extends BaseSteps {
    @Getter(lazy = true)
    private final MessagesPage messagesPage = new MessagesPage(driver);
    @Getter(lazy = true)
    private final SettingsPage settingsPage = new SettingsPage(driver);
    @Getter(lazy = true)
    private final TermsAndConditionsPage termsAndConditionsPage = new TermsAndConditionsPage(driver);

    public MessagesSteps(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToSettings(){
        LoggerUtil.logInfo("Navigate to the settings");
        LoggerUtil.logInfo("Open more options menu dropdown");
        getMessagesPage().clickMoreOptions();

        LoggerUtil.logInfo("Click settings");
        getMessagesPage().clickSettingsInMoreMenuOptions();
    }

    public void navigateToTermsAndConditions(){
        LoggerUtil.logInfo("Click terms and conditions");
        getSettingsPage().clickTermsAndConditions();

        LoggerUtil.logInfo("Assert that terms and conditions page opened");
        assertTrue(getTermsAndConditionsPage().getTermsAndServicesVersionInfo().isDisplayed());
    }
}
