package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static LinkedList<Integer> q = new LinkedList<>();
	private static Map<String, String> excelDataValues = new HashMap<String, String>();
	private static HashMap<String, Integer> iteration = new HashMap<>();

	public static void setExcelFile(String sheetName) {
		try {
			StringBuffer xlFilePath = new StringBuffer(Constant.Path_TestData + Constant.File_TestData);
			FileInputStream xlFile = new FileInputStream(new File(xlFilePath.toString()));
			ExcelWBook = new XSSFWorkbook(xlFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getCellData(int rowNum, int colNum) {
		StringBuffer cellData = new StringBuffer();
		try {
			Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
			cellData.append(Cell.getStringCellValue());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cellData.toString();
	}

	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	public static void getTableArray(String testScriptName, String sheetName) {
		setExcelFile(sheetName);
		Row headerRow = ExcelWSheet.getRow(0);
		int rowNum = ExcelWSheet.getLastRowNum();
		int colNum = headerRow.getLastCellNum();
		int startNum = 1;
		for (int rowIterator = 1; rowIterator <= rowNum; rowIterator++) {
			if (getCellData(rowIterator, 0).equalsIgnoreCase("YES")
					&& getCellData(rowIterator, 1).equalsIgnoreCase(testScriptName)) {
				q.add(rowIterator);
			}
		}
		if (!iteration.containsKey(testScriptName)) {
			iteration.put(testScriptName, 1);
		}
		if (iteration.get(testScriptName) <= q.size()) {
		} else {
			iteration.put(testScriptName, 1);
		}
		for (int rowIterator = 1; rowIterator <= q.size(); rowIterator++) {
			int tempIterationValue = iteration.get(testScriptName);
			if (rowIterator == iteration.get(testScriptName)) {
				for (int colIterator = 0; colIterator < colNum; colIterator++) {
					StringBuffer header = new StringBuffer(getCellData(0, colIterator));
					StringBuffer value = new StringBuffer(getCellData(q.get(rowIterator - 1), colIterator));
					excelDataValues.put(header.toString(), value.toString());
				}
				tempIterationValue++;
				iteration.put(testScriptName, tempIterationValue);
				break;
			} else {
				tempIterationValue++;
			}
		}
	}

	public static Map<String, String> getTestData() {
		return excelDataValues;
	}
}
