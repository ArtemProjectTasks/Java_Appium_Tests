import Elements.BaseElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class TestShto extends BaseTest {
    public TestShto(){ }

    @Test
    public void TestWithNoga(){
        Integer variable = 10;
        System.out.println(variable);
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    public void testOne(){

        var element = driver.findElement(
                By.xpath("//android.widget.Button[@resource-id=\"org.joinmastodon.android:id/btn_join_default_server\"]"));
        element.click();

        var locator = "//android.widget.TextView[@resource-id=\"org" +
                ".joinmastodon.android:id/text\" and @text=\"By continuing," +
                " you agree to follow by the following rules set and enforced " +
                "by the mastodon.social moderators.\"]";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement elementWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        assertTrue(elementWait.isDisplayed(), "Element is not visible");

        Dimension size = driver.manage().window().getSize();

        //Get start and end point positions for swipe
        int upX = (int) (size.height * 0.80);
        int lowX = (int) (size.height * 0.20);
        int startY = size.height / 2;

        TouchAction touchAction = new TouchAction(driver);

        //Swipe from right to left
        touchAction.press(PointOption.point(upX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(lowX, startY))
                .release()
                .perform();

        touchAction.press(new ElementOption().withElement(elementWait));
    }
}
