package Pages;

import Logger.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class BasePage {
    protected AndroidDriver driver;

    public BasePage(AndroidDriver driver, By locator){
        this.driver = driver;
        assertThatPageDisplayed(locator);
    }

    protected WebElement findElementAssertDisplayed(By locator, String elementName){
        return getWebElement(elementName, Duration.ofSeconds(8), locator);
    }

    protected WebElement findElementAssertDisplayed(By locator, String elementName, long customExtendedWait){
        return getWebElement(elementName, Duration.ofSeconds(customExtendedWait), locator);
    }

    protected WebElement getWebElement(String elementName, Duration customExtendedWait, By locator) {
        LoggerUtil.logInfo(MessageFormat.format("Find element: {0} and assert visibility", elementName));
        WebDriverWait wait = new WebDriverWait(driver, customExtendedWait);
        var element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        assertTrue(element.isDisplayed());

        return element;
    }

    private void assertThatPageDisplayed(By locator){
        LoggerUtil.logInfo("Assert that page opened");
        findElementAssertDisplayed(locator, "Page");
    }
}
