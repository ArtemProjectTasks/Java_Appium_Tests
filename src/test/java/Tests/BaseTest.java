package Tests;

import Logger.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    protected AndroidDriver driver;

    public BaseTest() throws MalformedURLException {
        LoggerUtil.logInfo("Test is starting");

        String className = getClass().getName();
        System.out.println("Current class: " + className);
        Map<String, String> hashSet = new HashMap<>();

        if (className.contains("MastodonTest")) {
            String currentDir = System.getProperty("user.dir");
            Path apkPath = Paths.get(currentDir, "..", "AppiumProject", "Mastodon_2.4.0_apkcombo.com.apk");
            hashSet.put("app", apkPath.toString());
        }
        else if (className.contains("MessagesTests")) {
            hashSet.put("appPackage", "com.google.android.apps.messaging");
            hashSet.put("appActivity", ".ui.ConversationListActivity");
            hashSet.put("noReset", "true");
        }
        else if (className.contains("ClockTests")) {
            hashSet.put("appPackage", "com.google.android.deskclock");
            hashSet.put("appActivity", "com.android.deskclock.DeskClock");
            hashSet.put("noReset", "true");
        }

        ConfigureDriver(hashSet);
    }

    @AfterMethod
    @Step("After method tear down")
    public void beforeTearDown(ITestResult result){
        Map<String, Object> caps = driver.getCapabilities().asMap();

        if (ITestResult.FAILURE == result.getStatus()) {
            captureScreenshot(driver);
        }

        if (driver != null) {
            driver.terminateApp(caps.get("appium:appPackage").toString());
            driver.quit();
        }
    }

    @Attachment(value = "Failure screenshot", type = "image/png")
    private static byte[] captureScreenshot(AndroidDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Configure driver")
    private void ConfigureDriver(Map<String, String> set) throws MalformedURLException {
        LoggerUtil.logInfo("Add all capabilities");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        getDefaultCapabilities().forEach(desiredCapabilities::setCapability);
        set.forEach(desiredCapabilities::setCapability);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Step
    private static Map<String, String> getDefaultCapabilities() throws KeyAlreadyExistsException {
        LoggerUtil.logInfo("Configure default capabilities for tests");
        Map<String, String> dictOfCapabilities = new HashMap<>();
        dictOfCapabilities.put("platformName", "Android");
        dictOfCapabilities.put("platformVersion", "12.0");
        dictOfCapabilities.put("udid", "emulator-5554");
        dictOfCapabilities.put("automationName", "UiAutomator2");

        return dictOfCapabilities;
    }
}
