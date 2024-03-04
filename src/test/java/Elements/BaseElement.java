package Elements;

import Logger.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;

import java.text.MessageFormat;
import java.util.List;

public class BaseElement implements WebElement {

    private final WebElement element;
    private final String elementName;

    public BaseElement(AndroidDriver driver, String locator, String name){
        element = driver.findElement(By.xpath(locator));
        elementName = name;
    }

    //region Description
    @Override
    public void click() {
        LoggerUtil.logInfo(MessageFormat.format("Click {0}", elementName));
        element.click();
    }

    @Override
    public void submit() {
        LoggerUtil.logInfo(MessageFormat.format("Submit to {0}",  elementName));
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        LoggerUtil.logInfo(MessageFormat.format("Send info: {0}, to {1}",  elementName, keysToSend));
        element.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        LoggerUtil.logInfo("Clear");
        element.clear();
    }

    @Override
    public String getTagName() {
        LoggerUtil.logInfo("Get tag name");
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        LoggerUtil.logInfo(MessageFormat.format("Get attribute: {0}", name));
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return element.getScreenshotAs(target);
    }

    //endregion
}
