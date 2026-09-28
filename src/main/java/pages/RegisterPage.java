package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ConfigReader;
import utils.Utils;
import utils.WaitUtils;

// class keeps resgister page locators and actions in one class
// in this page class we create methods  to perfrom actions on page like enterFirstName and all
// this lets us reuse same action whenever needed instead writing selenium code again and again 
public class RegisterPage {

    private WebDriver driver;
    private Utils utils;
    private WaitUtils waitUtils;

    // Locators // store locators of regestration page
    //Locator for First Name field
 // By = Selenium class used to create locators
 // By.id() = locate element using ID
 // firstName = variable storing the locator
 //private By firstName = By.id("input-firstname"); // stores the locator for firstname field
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By successMessage = By.xpath("//div[@id='content']//h1");
    private By privacyPolicy = By.name("agree");
    private By continueButton =  By.cssSelector("input[type='submit'][value='Continue']");
    private By warningMessage = By.cssSelector("div.alert.alert-danger");
    // Constructor receives the browser driver
    // when test creates the  page RegisterPage registerPage = new RegisterPage(driver);
    //the driver from baseclass is passed into RegisterPage
    public RegisterPage(WebDriver driver) {
        this.driver = driver; // stores received driver inside reg page class
        this.utils = new Utils(driver);//create a utils object and give it the browser driver
        this.waitUtils = new WaitUtils(driver);//create utils object using the same driver
    }

    // Open Registration Page get url from config file add reg path opens url
    public void openRegisterPage() {
        driver.get(ConfigReader.getProperty("url")+ "index.php?route=account/register");
    }
    // Method to enter First Name
 // public = this method can be used from other classes
 // void = method does not return any value
 // String value = receives the name we want to enter

 public void enterFirstName(String value) {
     // Wait until First Name field is visible
     // firstName = locator of First Name field
     waitUtils.waitForVisibility(firstName);
     // Find First Name field using locator
     // Enter the value received by the method
     utils.sendKeys(firstName, value);

 }
    // Enter Last Name
    public void enterLastName(String value) {

        waitUtils.waitForVisibility(lastName);
        utils.sendKeys(lastName, value);
    }

    // Enter Email
    public void enterEmail(String value) {

        waitUtils.waitForVisibility(email);
        utils.sendKeys(email, value);
    }

    // Enter Telephone
    public void enterTelephone(String value) {

        utils.sendKeys(telephone, value);
    }

    // Enter Password
    public void enterPassword(String value) {

        utils.sendKeys(password, value);
    }

    // Confirm Password
    public void enterConfirmPassword(String value) {

        utils.sendKeys(confirmPassword, value);
    }

    // Accept Privacy Policy
    public void acceptPrivacyPolicy() {

        utils.selectCheckbox(privacyPolicy);
    }

    // Click Continue
    public void clickContinue() {

        waitUtils.waitForClickability(continueButton);
        utils.click(continueButton);
    }
    public String getSuccessMessage() {

        waitUtils.waitForVisibility(successMessage);

        return utils.getText(successMessage);
    }
    public String getWarningMessage() {

        waitUtils.waitForVisibility(warningMessage);

        return utils.getText(warningMessage);
    }
    // Complete Registration method  instead if calling diff test methods test can call this one method
 // Complete registration in one method
 // Receives registration test data
 // Calls all required registration actions
 // Finally clicks Continue
public void registerUser(String firstName,String lastName,String email,String telephone,String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterTelephone(telephone);
        enterPassword(password);
        enterConfirmPassword(password);
        acceptPrivacyPolicy();
        clickContinue();
    }
}