package ExcelImporter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ImportVendor {

	public static LinkedHashMap<String,String> importManufacturer() throws Exception{
		String[] path = {"D:\\Work\\2018年緬北亞泰S1單申請表-19.1.20.xls","D:\\Work\\2019 年緬北亞泰S1單申請表.xls"};
		String[] columnName = {"ERP代码","厂商名称"};
		LinkedHashMap<String,String> map=importManufacturer(path,columnName,1);
		
		return map;	
	}
	
	public static int[] getCellNo(Sheet sheet,int start,String[] columnName) {
		
		int[] columnNo = new int[columnName.length];

		Row row = sheet.getRow(start);
		
		for(int i=0 ; i<columnName.length ; i++){
			for(int j=1 ; j<row.getLastCellNum(); j++){
				Cell cell = row.getCell(j);
				if (cell == null)
					continue;
				if(cell.getCellType() == CellType.STRING){
					if(cell.getStringCellValue().equals(columnName[i])){
						columnNo[i] = j; 
					}
				}else if (cell.getCellType() == CellType.NUMERIC){
					if(cell.getStringCellValue().equals(columnName[i])){
						columnNo[i] = j;
					}
				}
			}
		}
		//if length is less than columnName arr it means can't process
		return columnNo;
	}
	
	
	public static LinkedHashMap<String,String> importManufacturer(String[] path, String[] columnName,int start)throws Exception{
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		
		for(int i=0 ; i<path.length ; i++){
			String p = path[i];
			FileInputStream fis  = new FileInputStream(new File(p));
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			Iterator<Sheet> sheetIterator = workbook.iterator();
			
			//sheet iterator
			while(sheetIterator.hasNext()){
				Sheet sheet = (Sheet)sheetIterator.next();
				System.out.println(sheet.getSheetName());
				int startRow = start+1;
				int endRow = sheet.getLastRowNum();
				System.out.println("end row :"+(endRow+1));
				
				//get cell No
				int[] cellNo = getCellNo(sheet,start,columnName);
				if (cellNo[0] == 0 && cellNo[1] == 0 ){
					System.out.println("some or all attribute names are missing");
					continue;
				}
				
				//iterate row and get cell
				for(int j=startRow; j<=endRow ; j++){
					
					// if row is null continue
					Row r = sheet.getRow(j);
					if (r == null){
						continue;
					}
					
					
					Cell keyCell = r.getCell(cellNo[0]);
					Cell valueCell = r.getCell(cellNo[1]);
					
					//if both cell are null
					if ( keyCell == null && valueCell == null){
						System.out.println("null");
						continue;
					}else if (keyCell.getNumericCellValue() == 0.0){
						System.out.println("zero");
						continue;
					}
					System.out.println(keyCell+"\t"+valueCell);
					
					//insert key and value
					
					map.put(String.valueOf((int)keyCell.getNumericCellValue()),valueCell.getStringCellValue());
					
				}
				System.out.println(map.entrySet());
				System.out.println("end row :"+(endRow+1));
			
			}
		}
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LinkedHashMap<String,String> map = importManufacturer();
		System.out.println("size "+map.size());
		System.out.println(map.entrySet());
	}

}
