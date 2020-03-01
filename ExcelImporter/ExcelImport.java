package ExcelImporter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelImport {
	static HSSFRow row;
	public static void main(String[] args) throws Exception {
		String path = "D:\\2018ƒÍæí±±ÅÜÃ©S1ÜŒ…Í’à±Ì-19.1.20.xls";
		FileInputStream fis = new FileInputStream(new File(path));
		
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		
		//iterate each sheet 
		Iterator<Sheet> sheetIterator = workbook.iterator();
		while(sheetIterator.hasNext()){
			Sheet s = (Sheet)sheetIterator.next();
			System.out.println(s.getSheetName());
			//iterate each row
			Iterator<Row> rowIterator = s.iterator();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				
				//get and print first cell 
				Cell cell  = row.getCell(1);
				if(cell == null){
					continue;
				}
				switch(cell.getCellType()){
				case NUMERIC:
					System.out.println(cell.getNumericCellValue());
					break;
					
				case STRING:
					System.out.println(cell.getStringCellValue());
					break;
				}
			}
		}
	   }
}
