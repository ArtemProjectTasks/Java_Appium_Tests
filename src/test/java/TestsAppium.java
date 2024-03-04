import Logger.LoggerUtil;
import com.github.javafaker.Faker;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

@SuppressWarnings("ALL")
public class TestsAppium extends BaseTest {
    public TestsAppium(){
        super();
        LoggerUtil.logInfo("Run google maps tests");
    }

    @Test
    public void openMapsAndFindCity(){
        //Arrange
        var address = new Faker().address();
        var cityName = address.city();
        var streetName = address.streetName();

        LoggerUtil.logInfo(MessageFormat.format("Open maps and find city {0}", cityName));

        //Act
        var searchBox = driver.findElement(
                By.xpath("//android.widget.TextView[contains(@text, 'Search here')]"));
        searchBox.sendKeys(cityName);
        searchBox.submit();

        int waitTime = 8;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.RelativeLayout[@resource-id='com.google.android.apps.maps:id/place_page_view']/android.widget.FrameLayout")
        ));

        //Assert
        assertTrue(descriptionElement.isDisplayed());
        assertTrue(descriptionElement.getText().contains(cityName));
    }

    @Test
    public void openMapsAndSwipeToTheRight(){
        LoggerUtil.logInfo("Open maps, zoom and swipe out of the area");

        //Arrange
        Dimension screenSize = driver.manage().window().getSize();
        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        //Act
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(centerX, centerY));

        var element = driver.findElement(
                By.xpath("//android.widget.Button[@resource-id=\"org.joinmastodon.android:id/btn_join_default_server\"]"));
        element.click();

        var locator = By.xpath("//android.widget.TextView[@resource-id=\"org" +
                ".joinmastodon.android:id/text\" and @text=\"By continuing," +
                " you agree to follow by the following rules set and enforced " +
                "by the mastodon.social moderators.\"]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement elementWait = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        assertTrue(elementWait.isDisplayed(), "Element is not visible");

        //Assert
    }
}
