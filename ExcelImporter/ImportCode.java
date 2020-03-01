package ExcelImporter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ImportCode {
	
	public static Set<String> importCode() throws Exception{
		String[] path = {"D:\\Work\\2018年緬北亞泰S1單申請表-19.1.20.xls","D:\\Work\\2019 年緬北亞泰S1單申請表.xls"};
		String columnName = "料號";
		Set<String> set=(LinkedHashSet<String>)importCode(path,columnName,1);
//		for (String s:set){
//			System.out.println(s);
//		}
//		System.out.println(set.size());
		
		return set;
	}
	
	public static Set<String> importCode(String[] path, String columnName,int start)throws Exception{
		Set<String> set = new LinkedHashSet<String>();
		
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
				int cellNo = getCellNo(sheet,start,columnName);
				if (cellNo == -1 ){
					System.out.println("attribute name not found");
					break;
				}
				
				//iterate row and get cell
				for(int j=startRow; j<=endRow ; j++){
					
					// if row is null continue
					Row r = sheet.getRow(j);
					if (r == null){
						continue;
					}
					Cell cell = r.getCell(cellNo);
					if (cell == null){
						System.out.println("null");
						continue;
					}
						
					
					if (cell.getCellType() == CellType.NUMERIC){
						System.out.println("i work");
						set.add(String.valueOf(cell.getNumericCellValue()));
						//System.out.println((int)cell.getNumericCellValue());
					}else if(cell.getCellType() == CellType.STRING){
						
						set.add(cell.getStringCellValue());
						//System.out.println(cell.getStringCellValue());
					}
				}
				System.out.println("end row :"+(endRow+1));
			
			}
		}
		return set;
	}
	
	public static int getCellNo(Sheet sheet,int start,String columnName) {

		Row row = sheet.getRow(start);
		for(int j=1 ; j<row.getLastCellNum(); j++){
			Cell cell = row.getCell(j);
			if (cell == null)
				continue;
			if(cell.getCellType() == CellType.STRING){
				if(cell.getStringCellValue().equals(columnName)){
					return j;
				};
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
	}

}
