package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputReader{
	

	public static String getSheet(String dataSheetName,String testcaseSheetName,  int rowInt, String columnName) {
		int colInt = 0;
		String data = null;

		try {
			XSSFSheet sheet = null;
			XSSFRow row;
			FileInputStream fis = new FileInputStream(new File("./data/"+dataSheetName+".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//get the count of the sheet
			int sheetCount = workbook.getNumberOfSheets();
			for (int i = 0; i< sheetCount; i++){
				String currSheetName = workbook.getSheetName(i);
			if (testcaseSheetName.equalsIgnoreCase(currSheetName)){			
					sheet = workbook.getSheet(currSheetName);	
			}
			}	
			// loop through the rows
			 row = sheet.getRow(0);
				for (int i = 0 ; i < row.getLastCellNum(); i++){
					//System.out.println(columnName);
					//System.out.println(row.getCell(i).getStringCellValue());
					if (columnName.equalsIgnoreCase(row.getCell(i).getStringCellValue())){						
						 colInt = i;					 
						
					}
				}
					try {
						//System.out.println(rowInt);
						row = sheet.getRow(rowInt);			 
					
						try{
							
							 data = row.getCell(colInt).getStringCellValue();
						}catch(NullPointerException e){

						}

				} catch (Exception e) {
 					e.printStackTrace();
				}
			
			fis.close();
			workbook.close();
		} catch (Exception e) {
 			e.printStackTrace();
		}

		return data;		
		
	}
	
	

}
