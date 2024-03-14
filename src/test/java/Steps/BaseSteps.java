package Steps;

import Enums.SwipeSideEnum;
import Logger.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;

public class BaseSteps {
    protected final AndroidDriver driver;

    public BaseSteps(AndroidDriver driver){
        this.driver = driver;
    }

    @Step
    protected void performSwipeOnElement(SwipeSideEnum side, WebElement element) {
        LoggerUtil.logInfo("Hide the panel using swipe");
        HashMap<String, Object> swipeArgs = new HashMap<>();
        swipeArgs.put("direction", side.toString().toLowerCase());
        swipeArgs.put("element", ((RemoteWebElement)element).getId());
        swipeArgs.put("percent", 0.5);

        driver.executeScript("mobile: swipeGesture", swipeArgs);
    }
}
