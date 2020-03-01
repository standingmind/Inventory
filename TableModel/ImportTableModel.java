package TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import DBUtils.DBConnection;
import DBUtils.ImportProductDao;
import HolderClass.ImportProductHolder;

public class ImportTableModel extends AbstractTableModel  {
	private ArrayList<PreparedStatement> stmtList= new ArrayList<PreparedStatement>();
	private String className = "import";
	private ArrayList<ImportProductHolder> importList ;
	private String[]columnNames;
	private boolean isEditable;
	
	private Connection conn= null;
	private PreparedStatement insertStmt = null;
	

	public ImportTableModel(ArrayList<ImportProductHolder> importList,String[] columnNames){
		this.importList = importList;
		this.columnNames = columnNames;
		
		//create DBConnection
//		initDB();
		
	}
//	public void initDB(){
//		conn = DBConnection.getMySQLConnection();
//		try {
//			insertStmt = conn.prepareStatement(insertQuery1);
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public void closeDB(){
		DBConnection.closePreparedStatement(insertStmt);

		DBConnection.closeConnection(conn);
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public String[] getColumnNames(){
		
		return columnNames;
	}
	
	public void setData(ArrayList<ImportProductHolder> list){
		//shallow copy 
		ArrayList<ImportProductHolder> newList = new ArrayList<ImportProductHolder>();
		
		for(ImportProductHolder holder : list){
			ImportProductHolder newHolder = new ImportProductHolder();
			newHolder.setProductId(holder.getProductId());
			newHolder.setArriveLocation(holder.getArriveLocation());
			newHolder.setCycle(holder.getCycle());
			newHolder.setDosage(holder.getDosage());
			newHolder.setImageLocation(holder.getImageLocation());
			newHolder.setImportPerson(holder.getImportPerson());
			newHolder.setImportQty(holder.getImportQty());
			newHolder.setLeadTime(holder.getLeadTime());
			newHolder.setPrice(holder.getPrice());
			newHolder.setProductClass(holder.getProductClass());
			newHolder.setProductCode(holder.getProductCode());
			newHolder.setProductName(holder.getProductName());
			newHolder.setProductSpec(holder.getProductSpec());
			newHolder.setRemark(holder.getRemark());
			newHolder.setSafeStock(holder.getSafeStock());
			newHolder.setShelf(holder.getShelf());
			newHolder.setShelfSeat(holder.getShelfSeat());
			newHolder.setStockQty(holder.getStockQty());
			newHolder.setTotalQty(holder.getTotalQty());
			newHolder.setUnit(holder.getUnit());
			newHolder.setVendor(holder.getVendor());
			newHolder.setExistInStock(holder.isExistInStock());
			
			//today date
			Calendar calendar = Calendar.getInstance();
			Timestamp t = new Timestamp(calendar.getTimeInMillis());
			newHolder.setImportDate(t);
			newHolder.setLastUpdated(t);
			newList.add(newHolder);
		}
		importList = newList;
		this.fireTableDataChanged();
	}
	
	public boolean isCellEditable(int row, int column){
		if(isEditable)
			return true;
		return false;
	   
	}
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return importList.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder import
		return 9;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		ImportProductHolder importHolder = importList.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex+1;
			break;
		case 1: 
			value = importHolder.getProductCode();
			break;
		case 2:
			value = importHolder.getProductName();
			break;
		case 3:
			value =  importHolder.getProductSpec();
			break;
		case 4:
			value = importHolder.getProductClass();
			break;
		case 5:
			value = importHolder.getPrice();
			break;
		case 6:
			value = importHolder.getImportQty();
			break;
		case 7:
			value = importHolder.getUnit();
			break;
		case 8:
			value = importHolder.getLeadTime();
			break;
		
		default:
			value =  new Object();
		}
		return value;
	}
	
	
	
	public ImportProductHolder getImportProductAt(int rowIndex){
		return importList.get(rowIndex);
	}
	
	public ArrayList<ImportProductHolder> getProductList(){
		return importList;
	}
	
	public boolean isIdExist(int id){
		for(ImportProductHolder holder : importList){
			if(holder.getProductId() == id)
				return true;
		}
		return false;
	}
	
	public void addRow(ImportProductHolder importHolder){
		importList.add(importHolder);
//		try {
//			insertStmt.setInt(1, importHolder.getProductId());
//			insertStmt.setString(2, importHolder.getImportPerson());
//			insertStmt.setString(3, importHolder.getOperationNo());
//			insertStmt.setInt(4, importHolder.getImportQty());
//			insertStmt.setInt(5, importHolder.getDosage());
//			insertStmt.setString(6, importHolder.getCycle());
//			insertStmt.setInt(7, importHolder.getLeadTime());
//			insertStmt.setString(8, importHolder.getVendor());
//			insertStmt.setTimestamp(9, importHolder.getImportDate());
//			insertStmt.setString(10, importHolder.getRemark());
//			insertStmt.setString(11, importHolder.getArriveLocation());
//			insertStmt.setString(12, importHolder.getPrice());
//			insertStmt.addBatch();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		this.fireTableDataChanged();
	}

	public void removeRow(int row){
		if(row>=0 && row<=importList.size()){
			importList.remove(row);
			//stmtList.remove(row);
		}
		this.fireTableRowsDeleted(row,row);
		
		
	}
	
	public void setEditable(boolean flag){
		isEditable = flag;
	}
	
	public boolean getEditable(){
		return isEditable;
	}

//	public ArrayList<String> getSavedQueries(){
//		return queries;
//	}
	
	public void clearTable(){
		importList.clear();
		this.fireTableDataChanged();
	}
	
	public void clearSavedStmt(){
		stmtList.clear();
	}
	public void executeBatch() throws Exception{
		stmtList.add(insertStmt);
		try {
			ImportProductDao.executeBatch(importList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		closeDB();
		
	}
}
