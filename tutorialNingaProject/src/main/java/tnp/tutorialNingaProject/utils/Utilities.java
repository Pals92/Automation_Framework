package tnp.tutorialNingaProject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities{
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGELOAD_TIME=5;
	
	public  static String generateEmailWithTimeStamp() {
		Date date =new Date();

		String timeStamp= date.toString().replace(" ", "_").replace(":", "_");
		return "pallavi.kattimani"+timeStamp+"@gmail.com";
	}
	
	public static Object[][] getDataFromExcel(String sheetName) {
		Workbook workbook = null;
		File filePath= new File(System.getProperty("user.dir")+"//src//main//java//tnp//tutorialNingaProject//testdata//testDataExcel.xlsx");
		/*File filePath = new File(System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                "tnp" + File.separator + "tutorialNingaProject" + File.separator +
                "testdata" + File.separator + "testDataExcel.xlsx");*/
		try {
			FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            fis.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		} 
		
		Sheet sheet = workbook.getSheet(sheetName);
		int rows=sheet.getLastRowNum();
		int cols= sheet.getRow(0).getLastCellNum();
		
		Object data[][]= new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			Row row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++) {
				
				Cell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
		
	}

}
