package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private Workbook workbook; // we create variable to hold the excel workbook /file
    private Sheet sheet; // this holds selected excel sheet
    // method to open the excel using filepath and sheetname as parameters
    public void openExcel(String filePath, String sheetName) throws IOException {

  FileInputStream file = new FileInputStream(filePath);// it opens the file located at this path
          workbook = new XSSFWorkbook(file);// it opens the excel file and creates the workbook object from it
        System.out.println("Excel opened successfully");// workbook represents excel file
        System.out.println("Requested sheet: " + sheetName);
        System.out.println("Available sheets: " + workbook.getNumberOfSheets());// no of sheets in excel workbook/file
// this goes through all excel sheets and gets the sheet name
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            System.out.println("Sheet " + i + ": " + workbook.getSheetName(i));
        }
        sheet = workbook.getSheet(sheetName);// selects the sheet from workbook
        System.out.println("Selected sheet: " + sheet);
    }
 // It tells us how many rows are present in selected Excel sheet.
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
    // Get number of columns in particular row
    public int getColumnCount(int rowNumber) {
          Row row = sheet.getRow(rowNumber);
        return row.getPhysicalNumberOfCells(); // tells u how many cells are there
    }
    // give the value from particular excel cell
    public String getCellData(int rowNumber, int columnNumber) {
        Row row = sheet.getRow(rowNumber);// go to req row
        if (row == null) { // if row doesnt exists return an empty string
            return "";
        }
        Cell cell = row.getCell(columnNumber);//go to req cell/coloumn
        if (cell == null) {
            return "";
        }
        DataFormatter formatter = new DataFormatter();// used to get  excel value as string in readable format
        return formatter.formatCellValue(cell);//take value from cell and return as string
    
    }

    // Close Excel
    public void closeExcel() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
