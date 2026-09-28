package utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ScreenshotUtils {
	
    public static String takeScreenshot(WebDriver driver, String testName) {
    	
    	String time = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    	// screenshot path
    	String path = "screenshots/" + testName + "_" + time + ".png";
      // source temp saves screenshot.(TakesScreenshot) driver use driver for taking
        //screenshots  .getScreenshotAs(OutputType.FILE); take scrrenshot and
        //give it to me as file
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File(path)); // it copies screenshot from source to  path
            // new file path creates the file object represent that location
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path; // we must return string listneres receive this path 
    }
}