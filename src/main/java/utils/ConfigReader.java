package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
// creates empty properties box   Private shared box for storing configuration key-value pairs.
  //Properties = configuration data box.
    private static Properties properties = new Properties();
    static { //static block used for run this code when ConfigReader class is loaded
        try { // try 
        	// it opens config file
     FileInputStream file = new FileInputStream("src/test/resources/config.properties");
        // it reads config file and stores it value
           properties.load(file);
            // close after reading
            file.close();
        }
        // catches exception 
        catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties");
     }   
    }
    //this method can use by other classes  ex-ConfigReader.getProperty("url");
    public static String getProperty(String key) {
// it returns value inside properties box 
        return properties.getProperty(key);
    }
}