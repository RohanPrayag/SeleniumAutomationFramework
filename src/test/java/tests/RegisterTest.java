package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.RegisterPage;
import utils.ExcelUtils;
import utils.RandomDataUtils;
import java.util.ArrayList;
import java.util.List;

// Test class contains actual tests and verifies the results
// ConfigReader → reads configuration values like URL
// DriverFactory → creates browser driver
// BaseClass → handles browser setup and cleanup
// Page Class → contains locators and page actions
// ExcelUtils → reads test data from Excel
// DataProvider → supplies test data to the test
// Test Class → executes the test and verifies the result

public class RegisterTest extends BaseClass {

    @DataProvider(name = "registrationData")
    // Provides multiple sets of test data to the test method
    public Object[][] registrationData() {
        // Creates an object of ExcelUtils
        ExcelUtils excel = new ExcelUtils();
        // Opens the Excel file and selects the Registration sheet
        try {
       excel.openExcel( "src/test/resources/testdata/RegisterData.xlsx","Registration" );
        } catch (Exception e) {
            // Stops the test if Excel cannot be opened
            throw new RuntimeException("Unable to open Excel file", e);
        }
        // Gets the total number of rows from Excel
        int rows = 4;
        System.out.println("Excel row count = " + rows);
        // Creates a table with 3 roes and 5 coloumns to store my test data
        Object[][] data = new Object[rows - 1][5];

        // Reads Excel data row by row
        for (int i = 1; i < rows; i++) {

            data[i - 1][0] = excel.getCellData(i, 0);
            // First Name

            data[i - 1][1] = excel.getCellData(i, 1);
            // Last Name

            data[i - 1][2] = RandomDataUtils.generateRandomEmail();
            // Generates a NEW email instead of reading email from Excel

            data[i - 1][3] = excel.getCellData(i, 3);
            // Telephone

            data[i - 1][4] = excel.getCellData(i, 4);
            // Password
        }
        // Closes the Excel workbook
        try {
            excel.closeExcel();
        } catch (Exception e) {
            throw new RuntimeException("Unable to close Excel file", e);
        }
        // Sends Excel data to TestNG DataProvider
        return data;
    }
    @Test(dataProvider = "registrationData")
    // Gets test data from the registrationData DataProvider
 public void verifyUserRegistration(String firstName,String lastName,String email,String telephone,String password) {
        // Creates RegisterPage object and passes browser driver
        RegisterPage registerPage = new RegisterPage(driver);
        // Opens Registration page
        registerPage.openRegisterPage();
        // Registers user using data received from Excel
        registerPage.registerUser(firstName,lastName,email,telephone,password);
        // Gets success message from Registration page
        String actualMessage = registerPage.getSuccessMessage();
        // Expected successful registration message
        String expectedMessage = "Your Account Has Been Created!";
        // Compares actual and expected messages
        Assert.assertEquals(actualMessage, expectedMessage);
        System.out.println("jenkins");
    }
}
    

