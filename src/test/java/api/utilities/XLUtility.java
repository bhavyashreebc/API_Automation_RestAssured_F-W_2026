package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    // Constructor to pass the Excel file path
    public XLUtility(String path) {
        this.path = path;
    }

    // Get total row count in a sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    // Get total cell (column) count in a specific row
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    // Read cell data as a formatted String
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // Reads all types (Numeric, String, etc.) as String
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }
    
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) { // If file does not exist, then create new file
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(sheetName) == -1) { // If sheet does not exist, then create new Sheet
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) { // If row does not exist, then create new Row
            sheet.createRow(rownum);
        }
        row = sheet.getRow(rownum);

        // --- Continuing the method to write the cell data ---
        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
  
 // not used in API testing, mostly used in Web Automation
 // Fills a specific cell background with Green color (typically used for PASSED test results) 
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);               // Open the Excel file stream for reading
        workbook = new XSSFWorkbook(fi);             // Load the workbook instance
        sheet = workbook.getSheet(sheetName);        // Access the specified worksheet

        row = sheet.getRow(rownum);                  // Get the target row
        cell = row.getCell(colnum);                  // Get the target cell

        style = workbook.createCellStyle();          // Create a new cell style instance

        // Set the background foreground color to Green
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        // Set the fill pattern to solid color
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);                    // Apply the new style to the target cell
        
        fo = new FileOutputStream(path);             // Open file output stream to write changes
        workbook.write(fo);                          // Save/write changes to the file
        
        workbook.close();                            // Close the workbook
        fi.close();                                  // Close the input stream
        fo.close();                                  // Close the output stream
    }

    // Fills a specific cell background with Red color (typically used for FAILED test results)
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);               // Open the Excel file stream for reading
        workbook = new XSSFWorkbook(fi);             // Load the workbook instance
        sheet = workbook.getSheet(sheetName);        // Access the specified worksheet

        row = sheet.getRow(rownum);                  // Get the target row
        cell = row.getCell(colnum);                  // Get the target cell

        style = workbook.createCellStyle();          // Create a new cell style instance

        // Set the background foreground color to Red
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        // Set the fill pattern to solid color
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);                    // Apply the new style to the target cell

        fo = new FileOutputStream(path);             // Open file output stream to write changes
        workbook.write(fo);                          // Save/write changes to the file
        
        workbook.close();                            // Close the workbook
        fi.close();                                  // Close the input stream
        fo.close();                                  // Close the output stream
    }
}