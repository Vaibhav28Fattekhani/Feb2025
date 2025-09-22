package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static Workbook workbook;

	// Load Excel File (supports .xls and .xlsx)

	public static void loadExcel(String filePath) {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			workbook = WorkbookFactory.create(fis);
		} catch (IOException e) {
			throw new RuntimeException("❌ Failed to load Excel file: " + filePath, e);
		}
	}

	// Get row count (excluding header row)
	public static int getRowCount(String sheetName) {
		// return workbook.getSheet(sheetName).getLastRowNum();
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet.getLastRowNum();
	}

	// Get column count
	public static int getColCount(String sheetName) {
		// return workbook.getSheet(sheetName).getRow(0).getLastCellNum();\
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet.getRow(0).getLastCellNum();
	}

	// Get single cell data
	public static String getData(String sheetName, int row, int col) {
		if (workbook == null) {
			throw new IllegalStateException("⚠️ Excel file not loaded. Call loadExcel() first.");
		}
		Sheet sheet = workbook.getSheet(sheetName);
		Row r = sheet.getRow(row); // 4
		Cell cell = r.getCell(col); // 2

		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(cell);
	}

	// ✅ Read entire sheet into 2D Object array (for DataProvider)
	public static Object[][] getSheetData(String sheetName) {
		if (workbook == null) {
			throw new IllegalStateException("⚠️ Excel file not loaded. Call loadExcel() first.");
		}

		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum(); // total rows (excluding header)
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowCount][colCount];
		DataFormatter formatter = new DataFormatter();

		// Start from row 1 (skip header)
		for (int i = 1; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = formatter.formatCellValue(cell);// data[0][0],data[0][1],data[1][0],data[1][1]
			}
		}
		return data;
	}
}
