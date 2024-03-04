import Logger.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import javax.management.openmbean.KeyAlreadyExistsException;
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
    public void  beforeSetUp() throws MalformedURLException, KeyAlreadyExistsException {
        LoggerUtil.logInfo("Configure capabilities");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        getCapabilities().forEach((k, v) -> desiredCapabilities.setCapability(k, v));

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
    }

    @AfterTest
    public void beforeTearDown(){
        driver.quit();
    }

    private static Map<String, String> getCapabilities() {
        Map<String, String> dictOfCapabilities = new HashMap<>();
        dictOfCapabilities.put("platformName", "Android");
        dictOfCapabilities.put("platformVersion", "13.0");
        dictOfCapabilities.put("udid", "emulator-5554");
        dictOfCapabilities.put("automationName", "UiAutomator2"); // Replace with the emulator's Android version
        dictOfCapabilities.put("appPackage", "com.google.android.apps.maps");
        dictOfCapabilities.put("appActivity", "com.google.android.maps.MapsActivity");

        return dictOfCapabilities;
    }
}
