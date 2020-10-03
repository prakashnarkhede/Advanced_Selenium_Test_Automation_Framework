package reusableComponents;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelOperations {
	
	String filePath;
	Sheet sh;
	
	public ExcelOperations(String sheetName) {
		 try {
			filePath = System.getProperty("user.dir")+PropertiesOperations.getPropertyValueByKey("testDataLocation");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 	//open file - workbook
			File testDataFile = new File(filePath);
			Workbook wb = null;
			try {
				wb = WorkbookFactory.create(testDataFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 sh = wb.getSheet(sheetName);
	}
	
	//get test data from test data sheet in hashmap based on row number
	@SuppressWarnings("deprecation")
	public HashMap<String, String> getTestDataInMap(int rowNum) throws Exception {
				//read data row by row and put in map
		HashMap<String, String> hm = new HashMap<String, String>();
		
		for (int i = 0; i < sh.getRow(0).getLastCellNum(); i++) {
			String value;
			if(sh.getRow(rowNum).getCell(i) != null) {
				sh.getRow(rowNum).getCell(i).setCellType(CellType.STRING);
				value = sh.getRow(rowNum).getCell(i).toString();			
			}
			else {
				value = "";
			}
			hm.put(sh.getRow(0).getCell(i).toString(), value);
		}	
		return hm;
	}
	
	//get row count
	public int getRowCount() {		
		return sh.getLastRowNum();
	}
	
	//ger column count
	public int getColCount() {
		return sh.getRow(0).getLastCellNum();
		
	}
}
