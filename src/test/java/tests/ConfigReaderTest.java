package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import utils.ConfigReader;

public class ConfigReaderTest {

    @Test
    public void verifyConfigReader() throws IOException {

        ConfigReader config = new ConfigReader();

        System.out.println("Browser: " + config.getProperty("browser"));
        System.out.println("URL: " + config.getProperty("url"));
    }
}