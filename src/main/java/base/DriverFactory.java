package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//DriverFactory creates the browser driver and gives it back to BaseClass
//"Why did you create DriverFactory?"
//Say:
//"I created DriverFactory to centralize WebDriver creation, so browser initialization 
//is maintained in one place instead of creating the driver separately in every test class."
public class DriverFactory {

	// this method is used to create driver and it returns  the driver we can call it directly DriverFactory.getDriver();
    public static WebDriver getDriver() {
         // opens the chrome browser 
        WebDriver driver = new ChromeDriver();
            // maximize the browser
        driver.manage().window().maximize();
// returns the browser Give the created browser driver back to whoever called getDriver().
 //For example, BaseClass says:
      //  driver = DriverFactory.getDriver()    
       return driver;
 

       //DriverFactory           BaseClass
           //   ↓                        ↓
      // new ChromeDriver()      DriverFactory.getDriver()
                                       
     //  Chrome opens            new ChromeDriver()
                                       
      // return driver              Chrome opens
                                         
     //  BaseClass gets driver       return driver
                                            
       //                         BaseClass receives driver
    }
}
