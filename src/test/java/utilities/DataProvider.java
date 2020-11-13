package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProvider {
	private Map<String, String> testData = new HashMap<String, String>();
	
	public void readTestData(String scenarioName, String sheetName) throws IOException {
		String path = "./src/test/resources/TestData.xlsx"; 
		FileInputStream file = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Row Firstrow = sheet.getRow(0);
		for(int i = 1 ; i<=sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);	
			if(row.getCell(0).getStringCellValue().equals(scenarioName)){
				for(int j=0; j<Firstrow.getLastCellNum(); j++) {
					Cell cellHeader = Firstrow.getCell(j);
					Cell cellValue = row.getCell(j);
					testData.put(cellHeader.getStringCellValue(), cellValue.getStringCellValue());					
				}
			}			
		}
		
	}
	
	
	
	public Map<String, String> getTestData(){
		return this.testData;
	}
	
}
