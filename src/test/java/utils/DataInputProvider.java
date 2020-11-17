package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputProvider{
	public static int rowIndex;

	public static String[][] getSheet( String testcaseSheetName, String dataSheetName) {

		String[][] data = null;
		System.out.println(testcaseSheetName);

		try {
			XSSFSheet sheet = null;
			FileInputStream fis = new FileInputStream(new File("./datatable/testData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//get the count of the sheet
			int sheetCount = workbook.getNumberOfSheets();
			for (int i = 0; i< sheetCount; i++){
				String currSheetName = workbook.getSheetName(i);
			if (dataSheetName.equalsIgnoreCase(currSheetName)){			
					sheet = workbook.getSheet(currSheetName);	
			}
			}
			// get the number of rows
			int rowCount = sheet.getLastRowNum();

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];


			// loop through the rows
			for(int i=1; i <rowCount+1; i++){
				rowIndex = i;
				try {
					XSSFRow row = sheet.getRow(i);
					for(int j=0; j <columnCount; j++){ // loop through the columns
						try {
							String cellValue = "";
							try{
								cellValue = row.getCell(j).getStringCellValue();
							}catch(NullPointerException e){

							}

							data[i-1][j]  = cellValue; // add to the data array
						} catch (Exception e) {
 							e.printStackTrace();
						}				
					}

				} catch (Exception e) {
 					e.printStackTrace();
				}
			}
			fis.close();
			workbook.close();
		} catch (Exception e) {
 			e.printStackTrace();
		}

		return data;

	
		
		
	}
	
	

}
