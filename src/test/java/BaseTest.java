import Logger.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    protected AndroidDriver driver;

    public BaseTest() {
        LoggerUtil.logInfo("Test is starting");
    }

    @BeforeTest
    public void  beforeSetUp() throws MalformedURLException {
        Map<String, String> dictOfCapabilities = new HashMap<>();
        dictOfCapabilities.put("platformName", "Android");
        dictOfCapabilities.put("platformVersion", "13.0");
        dictOfCapabilities.put("udid", "emulator-5554");
        dictOfCapabilities.put("app",
                "C:\\Users\\a.moskvin\\Documents\\NetBeansProjects\\AppiumProject\\Mastodon_2.4.0_apkcombo.com.apk");
        dictOfCapabilities.put("automationName", "UiAutomator2");


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        dictOfCapabilities.forEach((k, v) -> desiredCapabilities.setCapability(k, v));

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
    }

    @AfterTest
    public void  beforeTearDown(){
        driver.quit();
    }
}
