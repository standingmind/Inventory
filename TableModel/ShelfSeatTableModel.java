package TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import DBUtils.ShelfSeatDao;
import DBUtils.DBConnection;
import HolderClass.ShelfSeatHolder;

public class ShelfSeatTableModel extends AbstractTableModel  {
	private ArrayList<PreparedStatement> stmtList= new ArrayList<PreparedStatement>();
	private String className = "shelf_seat";
	private ArrayList<ShelfSeatHolder> shelfSeatList ;
	private String[]columnNames;
	private boolean isEditable;
	
	private Connection conn= null;
	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt1 = null;
	private PreparedStatement updateStmt2 = null;
	private String updateQuery1 = "Update "+className+" Set shelf_seat_name=? where shelf_seat_id=?";
	private String updateQuery2 = "Update "+className+" Set shelf_seat_remark=? where shelf_seat_id=?";
	private String insertQuery1 = "Insert into "+className+" (shelf_seat_name,shelf_seat_remark,shelf_seat_date) values (?,?,?)";

	public ShelfSeatTableModel(ArrayList<ShelfSeatHolder> shelfSeatList,String[] columnNames){
		this.shelfSeatList = shelfSeatList;
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
	
	public void setData(ArrayList<ShelfSeatHolder> list){
		//shallow copy 
		ArrayList<ShelfSeatHolder> newList = new ArrayList<ShelfSeatHolder>();
		
		for(ShelfSeatHolder holder : list){
			ShelfSeatHolder newHolder = new ShelfSeatHolder();
			newHolder.setShelfSeatId(holder.getShelfSeatId());
			newHolder.setShelfSeatName(holder.getShelfSeatName());
			newHolder.setShelfSeatRemark(holder.getShelfSeatRemark());
			newList.add(newHolder);
		}
		shelfSeatList = newList;
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
		return shelfSeatList.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder shelfSeat
		return 3;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		ShelfSeatHolder shelfSeatHolder = shelfSeatList.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex+1;
			break;
		case 1: 
			value = shelfSeatHolder.getShelfSeatName();
			break;
		case 2:
			value =  shelfSeatHolder.getShelfSeatRemark();
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
		ShelfSeatHolder shelfSeatHolder = shelfSeatList.get(rowIndex);
		switch(columnIndex){
		case 1:
			shelfSeatHolder.setShelfSeatName((String)value);
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
			shelfSeatHolder.setShelfSeatRemark((String)value);
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
	
	
	public ShelfSeatHolder getShelfSeatAt(int rowIndex){
		return shelfSeatList.get(rowIndex);
	}

	public void addRow(ShelfSeatHolder shelfSeatHolder){
		shelfSeatList.add(shelfSeatHolder);
		//String s = String.format("Insert into %s (product_shelfSeat_name,product_shelfSeat_date,product_shelfSeat_remark) values ('%s','%s','%s')", shelfSeatName,shelfSeatHolder.getShelfSeatName(),shelfSeatHolder.getShelfSeatDate(),shelfSeatHolder.getShelfSeatRemark().toString());
		try {
			insertStmt.setString(1, shelfSeatHolder.getShelfSeatName());
			insertStmt.setString(2, shelfSeatHolder.getShelfSeatRemark().toString());
			insertStmt.setTimestamp(3, shelfSeatHolder.getShelfSeatDate());
			insertStmt.addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.fireTableDataChanged();
	}

//	public void removeRow(int row){
//		String name = "";
//		if(row>=0 && row<=shelfSeatList.size()){
//			name = shelfSeatList.get(row).getShelfSeatName().trim();
//			shelfSeatList.remove(row);
//		}
//		
//		String s = String.format("Delete from %s where product_shelfSeat_name='%s'", shelfSeatName,name);
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
		int rowsAffected = ShelfSeatDao.ExecuteBatch(stmtList);
		closeDB();
		return rowsAffected;
	}
}
