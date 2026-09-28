package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait( driver, Duration.ofSeconds(10));
    }

    // Wait for element visibility
    public WebElement waitForVisibility(By locator) {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator) );
    }

    // Wait for element presence in DOM
    public WebElement waitForPresence(By locator) {

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to be clickable
    public WebElement waitForClickability(By locator) {

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for element to become invisible
    public boolean waitForInvisibility(By locator) {

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait until element is selected
    public boolean waitForSelected(By locator) {

        return wait.until( ExpectedConditions.elementToBeSelected(locator)
        );
    }

    // Wait until title exactly matches
    public boolean waitForTitle(String title) {

        return wait.until(ExpectedConditions.titleIs(title) );
    }

    // Wait until title contains text
    public boolean waitForTitleContains(String title) {

        return wait.until(ExpectedConditions.titleContains(title) );
    }

    // Wait until URL exactly matches
    public boolean waitForUrl(String url) {

        return wait.until(ExpectedConditions.urlToBe(url));
    }

    // Wait until URL contains text
    public boolean waitForUrlContains(String url) {

        return wait.until(ExpectedConditions.urlContains(url)
        );
    }

    // Wait for alert
    public void waitForAlert() {

        wait.until(ExpectedConditions.alertIsPresent()
        );
    }

    // Wait for frame and switch to it
    public WebDriver waitForFrameAndSwitch(By locator) {

        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator)
        );
    }

    // Wait for number of windows
    public boolean waitForNumberOfWindows(int number) {

        return wait.until(ExpectedConditions.numberOfWindowsToBe(number)
        );
    }

    // Wait for page load
    public void waitForPageLoad() {

        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(  "return document.readyState" ).equals("complete") );
    }
}
