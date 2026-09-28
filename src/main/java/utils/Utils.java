package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class Utils {

    private WebDriver driver;
    private Actions actions;

    public Utils(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }
    // =========================================================
    // ELEMENT UTILITIES
    // =========================================================
    public void click(By locator) {
        driver.findElement(locator).click();
    }
    public void sendKeys(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

    public void clear(By locator) {
        driver.findElement(locator).clear();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public boolean isEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    public boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    public int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }

    // =========================================================
    // DROPDOWN UTILITIES
    // =========================================================

    private Select getSelect(By locator) {
        return new Select(driver.findElement(locator));
    }

    public void selectByText(By locator, String text) {
        getSelect(locator).selectByVisibleText(text);
    }

    public void selectByValue(By locator, String value) {
        getSelect(locator).selectByValue(value);
    }

    public void selectByIndex(By locator, int index) {
        getSelect(locator).selectByIndex(index);
    }

    public String getSelectedOption(By locator) {
        return getSelect(locator)
                .getFirstSelectedOption()
                .getText();
    }

    public int getOptionsCount(By locator) {
        return getSelect(locator)
                .getOptions()
                .size();
    }

    public void printAllOptions(By locator) {

        List<WebElement> options = getSelect(locator).getOptions();
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
    }

    public boolean isMultiSelect(By locator) {
        return getSelect(locator).isMultiple();
    }

    public void deselectByText(By locator, String text) {
        getSelect(locator).deselectByVisibleText(text);
    }

    public void deselectByValue(By locator, String value) {
        getSelect(locator).deselectByValue(value);
    }

    public void deselectByIndex(By locator, int index) {
        getSelect(locator).deselectByIndex(index);
    }

    public void deselectAll(By locator) {
        getSelect(locator).deselectAll();
    }

    // =========================================================
    // RADIO BUTTON UTILITIES
    // =========================================================

    public void selectRadioButton(By locator) {

        WebElement radio = driver.findElement(locator);

        if (!radio.isSelected()) {
            radio.click();
        }
    }

    public boolean isRadioButtonSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    // =========================================================
    // CHECKBOX UTILITIES
    // =========================================================

    public void selectCheckbox(By locator) {

        WebElement checkbox = driver.findElement(locator);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void unselectCheckbox(By locator) {

        WebElement checkbox = driver.findElement(locator);

        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isCheckboxSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    public void selectCheckboxByIndex(By locator, int index) {

        List<WebElement> checkboxes = driver.findElements(locator);

        if (index < 0 || index >= checkboxes.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid checkbox index: " + index);
        }

        WebElement checkbox = checkboxes.get(index);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectAllCheckboxes(By locator) {

        List<WebElement> checkboxes = driver.findElements(locator);

        for (WebElement checkbox : checkboxes) {

            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void unselectAllCheckboxes(By locator) {

        List<WebElement> checkboxes = driver.findElements(locator);

        for (WebElement checkbox : checkboxes) {

            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    // =========================================================
    // ALERT UTILITIES
    // =========================================================

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public boolean isAlertPresent() {

        try {
            driver.switchTo().alert();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // =========================================================
    // ACTIONS UTILITIES
    // =========================================================

    public void clickAction(By locator) {

        actions.click(driver.findElement(locator)) .perform();
    }

    public void doubleClick(By locator) {

        actions.doubleClick(driver.findElement(locator))  .perform();
    }

    public void rightClick(By locator) {

        actions.contextClick(driver.findElement(locator)) .perform();
    }

    public void mouseHover(By locator) {

        actions.moveToElement(driver.findElement(locator)) .perform();
    }

    public void dragAndDrop(By source, By target) {

      actions.dragAndDrop( driver.findElement(source),driver.findElement(target)).perform();
    }

    public void pressEnter(By locator) {

        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    // =========================================================
    // WINDOW / TAB UTILITIES
    // =========================================================

    public String getCurrentWindowId() {
        return driver.getWindowHandle();
    }

    public Set<String> getAllWindowIds() {
        return driver.getWindowHandles();
    }

    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }

    public void switchToChildWindow() {

        String parentWindow = driver.getWindowHandle();

        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            if (!window.equals(parentWindow)) {
            	
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String title) {

        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(title)) {
                return;
            }
        }

        throw new RuntimeException("Window with title not found: " + title);
    }

    public void switchToWindowByUrl(String url) {

        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            driver.switchTo().window(window);

            if (driver.getCurrentUrl().contains(url)) {
                return;
            }
        }

        throw new RuntimeException(
                "Window with URL not found: " + url);
    }

    public void printAllWindowIds() {

        for (String window : driver.getWindowHandles()) {
            System.out.println(window);
        }
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    // =========================================================
    // FRAME UTILITIES
    // =========================================================

    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    public void switchToFrame(By locator) {

        WebElement frame = driver.findElement(locator);

        driver.switchTo().frame(frame);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // =========================================================
    // SCREENSHOT UTILITIES
    // =========================================================

    public String captureScreenshot(String testName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        String directory =
                System.getProperty("user.dir")
                        + File.separator
                        + "screenshots";

        File folder = new File(directory);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath =
                directory
                        + File.separator
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        try {

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            File destination =
                    new File(filePath);

            FileHandler.copy(source, destination);

            System.out.println(
                    "Screenshot saved: " + filePath);

            return filePath;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to capture screenshot", e);
        }
    }

    public String takeElementScreenshot(
            By locator,
            String fileName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        String directory =
                System.getProperty("user.dir")
                        + File.separator
                        + "screenshots";

        File folder = new File(directory);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath =
                directory
                        + File.separator
                        + fileName
                        + "_"
                        + timestamp
                        + ".png";

        try {

            File source =
                    driver.findElement(locator)
                            .getScreenshotAs(OutputType.FILE);

            File destination =
                    new File(filePath);

            FileHandler.copy(source, destination);

            return filePath;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to capture element screenshot", e);
        }
    }

    // =========================================================
    // BROWSER UTILITIES
    // =========================================================

    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    public void minimizeBrowser() {
        driver.manage().window().minimize();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}