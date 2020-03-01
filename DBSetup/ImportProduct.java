package ExcelImporter;

import java.io.File;
import java.io.FileInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import ExcelHolderClass.ExcelProductHolder;

public class ImportProduct {
	
	
	public static Set<ExcelProductHolder >importProduct() throws Exception{
		String[] path = {"D:\\Work\\2018年緬北亞泰S1單申請表-19.1.20.xls","D:\\Work\\2019 年緬北亞泰S1單申請表.xls"};
		String[] columnName = {"料號","厂商名称","品名","規格型號","單位","未稅16%增值稅單價","擔當"};
		Set<ExcelProductHolder> productHolder=importProduct(path,columnName,1);
		
		return productHolder;	
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
	

	public static Set<ExcelProductHolder> importProduct(String[] path,String[] columnName,int start) throws Exception{
		
		Set<ExcelProductHolder> productList = new HashSet<ExcelProductHolder>();
		
		
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
				
				
				//iterate row and get cell
				for(int j=startRow; j<=endRow ; j++){
					
					// if row is null continue
					Row r = sheet.getRow(j);
					if (r == null){
						continue;
					}
					
					ExcelProductHolder productHolder = new ExcelProductHolder() ;
					
					//show cellNo
					for(int l =0 ; l<cellNo.length ; l++){
						System.out.println(cellNo[l]);
					}
					
					//check whether attribute exists 
					Object[] excelValue = new Object[7];
					for(int k=0 ; k<cellNo.length ; k++){
						if(cellNo[k] == 0){ //if the column doesn't exist
							if (k==5) // if the column is price we replace with 0.0
								excelValue[k] = 0.0;
							else
								excelValue[k] = "无";
						 continue;
						}
						
						//if cell is null
						Cell cell = r.getCell(cellNo[k]);
						if (cell == null){
							if (k==5)
								excelValue[k] = 0.0;
							else
								excelValue[k] = "无";
						}else{
							if(cell.getCellType() == CellType.NUMERIC)
								excelValue[k] = cell.getNumericCellValue();
							else if(cell.getCellType() == CellType.STRING)
								excelValue[k] = cell.getStringCellValue();
							else if(cell.getCellType() == CellType.FORMULA){
								DecimalFormat df = new DecimalFormat("#.####");
								df.setRoundingMode(RoundingMode.CEILING);
								
								if(cell.getCachedFormulaResultType() == CellType.NUMERIC)
									excelValue[k] = df.format(cell.getNumericCellValue());
								else if(cell.getCachedFormulaResultType() == CellType.STRING)
									excelValue[k] = cell.getStringCellValue();
						
							}
								
							else if(cell.getCellType() == CellType.BLANK)
								excelValue[k] = "无" ;
						}
						
						
					}
					
					
				
					//{"料號","厂商名称","品名","規格型號","單位","未稅16%增值稅單價","擔當"}
					// get data from excel
				//	System.out.println((String)excelValue[0]);
					productHolder.setCode((String)excelValue[0]);
			//		System.out.println(excelValue[1]);
					productHolder.setVendor(String.valueOf(excelValue[1]));
					productHolder.setName(String.valueOf(excelValue[2]));
					productHolder.setModel(String.valueOf(excelValue[3]));
					productHolder.setUnit(String.valueOf(excelValue[4]));
					productHolder.setPrice(String.valueOf(excelValue[5]));
					productHolder.setPersonInCharge(String.valueOf(excelValue[6]));
					productList.add(productHolder);
					
				}
				System.out.println("end row :"+(endRow+1));
			
			}
		}
		
		
		return productList;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Set<ExcelProductHolder> productList = importProduct();
		int count =0 ;
		for(ExcelProductHolder p : productList){
			System.out.println(++count +" "+p.toString());
		}
		
	}

}
