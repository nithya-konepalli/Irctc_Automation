package com.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelWriter {
	public static void writeHotelsToExcel(List<WebElement> hotelNames, List<WebElement> hotelLocations, List<WebElement> hotelPrices) throws IOException {
        
		//Creating a Excel sheet
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Hotel Data");
        
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Hotel Name");
        headerRow.createCell(1).setCellValue("Hotel Location");
        headerRow.createCell(2).setCellValue("Hotel Price");
        
        //Entering the values into the Excel sheet
        for (int i = 0; i < hotelNames.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(hotelNames.get(i).getText());
            row.createCell(1).setCellValue(hotelLocations.get(i).getText());
            row.createCell(2).setCellValue(hotelPrices.get(i).getText());
        }
        
        String fileName="C:\\Users\\2403633\\eclipse-workspace\\Project\\src\\test\\resource\\Irctc_HotelData_Report"+timestamp() +" HotelData.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
            System.out.println("Data successfully written to Excel file.");
        } catch (IOException e) {
            System.out.println("Error writing to Excel: " + e.getMessage());
            workbook.close();
        }
    }

	private static String timestamp() {
		// TODO Auto-generated method stub
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

}
