package TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import DBUtils.ArrivalLocationDao;
import DBUtils.DBConnection;
import HolderClass.ArrivalLocationHolder;

public class ArrivalLocationTableModel extends AbstractTableModel  {
	private ArrayList<PreparedStatement> stmtList= new ArrayList<PreparedStatement>();
	private String className = "arrival_location";
	private ArrayList<ArrivalLocationHolder> arrivalLocationList ;
	private String[]columnNames;
	private boolean isEditable;
	
	private Connection conn= null;
	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt1 = null;
	private PreparedStatement updateStmt2 = null;
	private String updateQuery1 = "Update "+className+" Set arrival_location_name=? where arrival_location_id=?";
	private String updateQuery2 = "Update "+className+" Set arrival_location_remark=? where arrival_location_id=?";
	private String insertQuery1 = "Insert into "+className+" (arrival_location_name,arrival_location_remark) values (?,?)";

	public ArrivalLocationTableModel(ArrayList<ArrivalLocationHolder> arrivalLocationList,String[] columnNames){
		this.arrivalLocationList = arrivalLocationList;
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
	
	public void setData(ArrayList<ArrivalLocationHolder> list){
		//shallow copy 
		ArrayList<ArrivalLocationHolder> newList = new ArrayList<ArrivalLocationHolder>();
		
		for(ArrivalLocationHolder holder : list){
			ArrivalLocationHolder newHolder = new ArrivalLocationHolder();
			newHolder.setArrivalLocationId(holder.getArrivalLocationId());
			newHolder.setArrivalLocationName(holder.getArrivalLocationName());
			newHolder.setArrivalLocationRemark(holder.getArrivalLocationRemark());
			newList.add(newHolder);
		}
		arrivalLocationList = newList;
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
		return arrivalLocationList.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder arrivalLocation
		return 3;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		ArrivalLocationHolder arrivalLocationHolder = arrivalLocationList.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex+1;
			break;
		case 1: 
			value = arrivalLocationHolder.getArrivalLocationName();
			break;
		case 2:
			value =  arrivalLocationHolder.getArrivalLocationRemark();
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
		ArrivalLocationHolder arrivalLocationHolder = arrivalLocationList.get(rowIndex);
		switch(columnIndex){
		case 1:
			arrivalLocationHolder.setArrivalLocationName((String)value);
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
			arrivalLocationHolder.setArrivalLocationRemark((String)value);
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
	
	
	public ArrivalLocationHolder getArrivalLocationAt(int rowIndex){
		return arrivalLocationList.get(rowIndex);
	}

	public void addRow(ArrivalLocationHolder arrivalLocationHolder){
		arrivalLocationList.add(arrivalLocationHolder);
		//String s = String.format("Insert into %s (product_arrivalLocation_name,product_arrivalLocation_date,product_arrivalLocation_remark) values ('%s','%s','%s')", arrivalLocationName,arrivalLocationHolder.getArrivalLocationName(),arrivalLocationHolder.getArrivalLocationDate(),arrivalLocationHolder.getArrivalLocationRemark().toString());
		try {
			insertStmt.setString(1, arrivalLocationHolder.getArrivalLocationName());
			insertStmt.setString(2, arrivalLocationHolder.getArrivalLocationRemark().toString());
			insertStmt.addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.fireTableDataChanged();
	}

//	public void removeRow(int row){
//		String name = "";
//		if(row>=0 && row<=arrivalLocationList.size()){
//			name = arrivalLocationList.get(row).getArrivalLocationName().trim();
//			arrivalLocationList.remove(row);
//		}
//		
//		String s = String.format("Delete from %s where product_arrivalLocation_name='%s'", arrivalLocationName,name);
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
		int rowsAffected = ArrivalLocationDao.ExecuteBatch(stmtList);
		closeDB();
		return rowsAffected;
	}
}
