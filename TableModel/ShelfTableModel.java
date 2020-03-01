package TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import DBUtils.ShelfDao;
import DBUtils.DBConnection;
import HolderClass.ShelfHolder;

public class ShelfTableModel extends AbstractTableModel  {
	private ArrayList<PreparedStatement> stmtList= new ArrayList<PreparedStatement>();
	private String className = "shelf";
	private ArrayList<ShelfHolder> shelfList ;
	private String[]columnNames;
	private boolean isEditable;
	
	private Connection conn= null;
	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt1 = null;
	private PreparedStatement updateStmt2 = null;
	private String updateQuery1 = "Update "+className+" Set shelf_name=? where shelf_id=?";
	private String updateQuery2 = "Update "+className+" Set shelf_remark=? where shelf_id=?";
	private String insertQuery1 = "Insert into "+className+" (shelf_name,shelf_remark,shelf_date) values (?,?,?)";

	public ShelfTableModel(ArrayList<ShelfHolder> shelfList,String[] columnNames){
		this.shelfList = shelfList;
		this.columnNames = columnNames;
		
		//create DBConnection
		initDB();
		
	}
	public void initDB(){
		conn = DBConnection.getMySQLConnection();
		try {
			insertStmt = conn.prepareStatement(insertQuery1);
			updateStmt1 = conn.prepareStatement(updateQuery1);
			updateStmt2 = conn.prepareStatement(updateQuery2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void closeDB(){
		DBConnection.closePreparedStatement(insertStmt);
		DBConnection.closePreparedStatement(updateStmt1);
		DBConnection.closePreparedStatement(updateStmt2);
		DBConnection.closeConnection(conn);
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public String[] getColumnNames(){
		
		return columnNames;
	}
	
	public void setData(ArrayList<ShelfHolder> list){
		//shallow copy 
		ArrayList<ShelfHolder> newList = new ArrayList<ShelfHolder>();
		
		for(ShelfHolder holder : list){
			ShelfHolder newHolder = new ShelfHolder();
			newHolder.setShelfId(holder.getShelfId());
			newHolder.setShelfName(holder.getShelfName());
			newHolder.setShelfRemark(holder.getShelfRemark());
			newList.add(newHolder);
		}
		shelfList = newList;
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
		return shelfList.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder shelf
		return 3;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		ShelfHolder shelfHolder = shelfList.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex+1;
			break;
		case 1: 
			value = shelfHolder.getShelfName();
			break;
		case 2:
			value =  shelfHolder.getShelfRemark();
			break;
		default:
			value =  new Object();
		}
		return value;
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex){
		if(rowIndex < 0 || rowIndex >= getRowCount()){
			JOptionPane.showMessageDialog(null, "invalid index");
		}
		if(columnIndex < 0 || columnIndex >= getColumnCount()){
			JOptionPane.showMessageDialog(null, "invalid index");
		}
		ShelfHolder shelfHolder = shelfList.get(rowIndex);
		switch(columnIndex){
		case 1:
			shelfHolder.setShelfName((String)value);
			try {
				updateStmt1.setString(1, (String)value);
				updateStmt1.setInt(2, rowIndex+1);
				updateStmt1.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			shelfHolder.setShelfRemark((String)value);
			try {
				updateStmt2.setString(1, (String)value);
				updateStmt2.setInt(2, rowIndex+1);
				updateStmt2.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.fireTableRowsUpdated(rowIndex, columnIndex);
	}
	
	
	public ShelfHolder getShelfAt(int rowIndex){
		return shelfList.get(rowIndex);
	}

	public void addRow(ShelfHolder shelfHolder){
		shelfList.add(shelfHolder);
		//String s = String.format("Insert into %s (product_shelf_name,product_shelf_date,product_shelf_remark) values ('%s','%s','%s')", shelfName,shelfHolder.getShelfName(),shelfHolder.getShelfDate(),shelfHolder.getShelfRemark().toString());
		try {
			insertStmt.setString(1, shelfHolder.getShelfName());
			insertStmt.setString(2, shelfHolder.getShelfRemark().toString());
			insertStmt.setTimestamp(3, shelfHolder.getShelfDate());
			insertStmt.addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.fireTableDataChanged();
	}

//	public void removeRow(int row){
//		String name = "";
//		if(row>=0 && row<=shelfList.size()){
//			name = shelfList.get(row).getShelfName().trim();
//			shelfList.remove(row);
//		}
//		
//		String s = String.format("Delete from %s where product_shelf_name='%s'", shelfName,name);
//		queries.add(s);
//		this.fireTableDataChanged();
//		
//	}
	
	public void setEditable(boolean flag){
		isEditable = flag;
	}
	
	public boolean getEditable(){
		return isEditable;
	}

//	public ArrayList<String> getSavedQueries(){
//		return queries;
//	}
	public void clearSavedStmt(){
		stmtList.clear();
	}
	public int executeBatch(){
		stmtList.add(insertStmt);
		stmtList.add(updateStmt1);
		stmtList.add(updateStmt2);
		int rowsAffected = ShelfDao.ExecuteBatch(stmtList);
		closeDB();
		return rowsAffected;
	}
}
