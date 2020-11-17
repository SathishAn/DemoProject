package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataOutputWriter{
	public static int rowIndex;

	public static void setSheet(String dataSheetName,String testcaseSheetName,  int rowInt, String columnName, String value) {

		String[][] data = null;

		try {
			int colInt = 0;
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
					
			
			// to get the colum index
			
				 row = sheet.getRow(0);
				for (int i = 0 ; i < row.getLastCellNum(); i++){
					if (columnName.equalsIgnoreCase(row.getCell(i).getStringCellValue())){
						 colInt = i;
						
					}
				
			}
			//
				try {
					
							row = sheet.getRow(rowInt);
					 
						
							try{
								
								 row.getCell(colInt).setCellValue(value);
							}catch(NullPointerException e){

							}

							
						} catch (Exception e) {
 							e.printStackTrace();
						}				
					

			

			FileOutputStream fos = new FileOutputStream("./data/"+dataSheetName+".xlsx");
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (Exception e) {
 			e.printStackTrace();
		}



	
		
		
	}
	
	

}
