import Logger.LoggerUtil;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    protected AndroidDriver driver;

    public BaseTest() {
        LoggerUtil.logInfo("Test is starting");
    }

    @BeforeGroups(groups = "Mastodon")
    public void  setUpMastodon() throws MalformedURLException {
        Map<String, String> hashSet = new HashMap<>();
        hashSet.put("app",
                "C:\\Users\\a.moskvin\\Documents\\NetBeansProjects\\AppiumProject\\Mastodon_2.4.0_apkcombo.com.apk");

        ConfigureDriver(hashSet);
    }

    @BeforeGroups(groups = "Messages")
    public void  setUpMessages() throws MalformedURLException {
        Map<String, String> hashSet = new HashMap<>(); // Adjust if necessary
        hashSet.put("appPackage", "com.google.android.apps.messaging");
        hashSet.put("appActivity", ".ui.ConversationListActivity");
        hashSet.put("noReset", "true");

        ConfigureDriver(hashSet);
    }

    @AfterGroups(groups = { "Messages", "Mastodon" } )
    public void beforeTearDown(){
        driver.terminateApp("com.google.android.apps.messaging");
        driver.quit();
    }

    private void ConfigureDriver(Map<String, String> set) throws MalformedURLException {
        LoggerUtil.logInfo("Configure default capabilities for tests");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        getDefaultCapabilities().forEach((k, v) -> desiredCapabilities.setCapability(k, v));
        set.forEach((k, v) -> desiredCapabilities.setCapability(k, v));

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private static Map<String, String> getDefaultCapabilities() throws KeyAlreadyExistsException {
        Map<String, String> dictOfCapabilities = new HashMap<>();
        dictOfCapabilities.put("platformName", "Android");
        dictOfCapabilities.put("platformVersion", "12.0");
        dictOfCapabilities.put("udid", "emulator-5554");
        dictOfCapabilities.put("automationName", "UiAutomator2");

        return dictOfCapabilities;
    }
}
