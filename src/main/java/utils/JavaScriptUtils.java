package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private WebDriver driver;

    public JavaScriptUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Click element using JavaScript
    public void click(By locator) {

        WebElement element = driver.findElement(locator);
   ((org.openqa.selenium.JavascriptExecutor) driver) .executeScript("arguments[0].click();", element);
    }

    // Scroll to element
    public void scrollToElement(By locator) {

        WebElement element = driver.findElement(locator);
((org.openqa.selenium.JavascriptExecutor) driver) .executeScript("arguments[0].scrollIntoView(true);", element );
    }

    // Scroll to bottom
    public void scrollToBottom() {

        ((org.openqa.selenium.JavascriptExecutor) driver) .executeScript( "window.scrollTo(0, document.body.scrollHeight);" );
    }

    // Scroll to top
    public void scrollToTop() {

        ((org.openqa.selenium.JavascriptExecutor) driver) .executeScript("window.scrollTo(0, 0);");
    }

    // Execute custom JavaScript
    public Object executeScript(String script) {

        return ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script);
    }

    // Highlight element - useful for debugging
    public void highlightElement(By locator) {

        WebElement element = driver.findElement(locator);
 ((org.openqa.selenium.JavascriptExecutor) driver).executeScript( "arguments[0].style.border='3px solid red';", element );
    }
}