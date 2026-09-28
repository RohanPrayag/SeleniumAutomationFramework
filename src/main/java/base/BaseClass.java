package base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseClass {
    public WebDriver driver;
    @BeforeMethod
    public void setUp() { // prepare everything before testing
    	// stores the browser created by driver factory 
        driver = DriverFactory.getDriver();
        String url = ConfigReader.getProperty("url"); // it gives url value from config file
        System.out.println("URL = " + url);
        driver.get(url);
    }
    @AfterMethod
    public void tearDown() { // close the driver 
        if (driver != null) { // check if driver exists 
            driver.quit();
        }
    }
}