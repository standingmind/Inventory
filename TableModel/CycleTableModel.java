package TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import DBUtils.CycleDao;
import DBUtils.DBConnection;
import HolderClass.CycleHolder;

public class CycleTableModel extends AbstractTableModel  {
	private ArrayList<PreparedStatement> stmtList= new ArrayList<PreparedStatement>();
	private String className = "cycle";
	private ArrayList<CycleHolder> cycleList ;
	private String[]columnNames;
	private boolean isEditable;
	
	private Connection conn= null;
	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt1 = null;
	private PreparedStatement updateStmt2 = null;
	private PreparedStatement updateStmt3 = null;
	private String updateQuery1 = "Update "+className+" Set cycle_name=? where cycle_id=?";
	private String updateQuery3 = "Update "+className+" Set cycle_remark=? where cycle_id=?";
	private String updateQuery2 = "Update "+className+" Set cycle_day=? where cycle_id=?";
	private String insertQuery1 = "Insert into "+className+" (cycle_name,cycle_day,cycle_date,cycle_remark) values (?,?,?,?)";

	public CycleTableModel(ArrayList<CycleHolder> cycleList,String[] columnNames){
		this.cycleList = cycleList;
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
			updateStmt3 = conn.prepareStatement(updateQuery3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void closeDB(){
		DBConnection.closePreparedStatement(insertStmt);
		DBConnection.closePreparedStatement(updateStmt1);
		DBConnection.closePreparedStatement(updateStmt2);
		DBConnection.closePreparedStatement(updateStmt3);
		DBConnection.closeConnection(conn);
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public String[] getColumnNames(){
		
		return columnNames;
	}
	
	public void setData(ArrayList<CycleHolder> list){
		//shallow copy 
		ArrayList<CycleHolder> newList = new ArrayList<CycleHolder>();
		
		for(CycleHolder holder : list){
			CycleHolder newHolder = new CycleHolder();
			newHolder.setCycleId(holder.getCycleId());
			newHolder.setCycleName(holder.getCycleName());
			newHolder.setCycleDay(holder.getCycleDay());
			newHolder.setCycleRemark(holder.getCycleRemark());
			newList.add(newHolder);
		}
		cycleList = newList;
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
		return cycleList.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder cycle
		return 4;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		CycleHolder cycleHolder = cycleList.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex+1;
			break;
		case 1: 
			value = cycleHolder.getCycleName();
			break;
		case 2:
			value = cycleHolder.getCycleDay();
			break;
		case 3:
			value =  cycleHolder.getCycleRemark();
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
		CycleHolder cycleHolder = cycleList.get(rowIndex);
		switch(columnIndex){
		case 1:
			cycleHolder.setCycleName((String)value);
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
			cycleHolder.setCycleDay(Integer.parseInt((String)value));
			try {
				updateStmt2.setInt(1,Integer.parseInt((String)value));
				updateStmt2.setInt(2, rowIndex+1);
				updateStmt2.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			cycleHolder.setCycleRemark((String)value);
			try {
				updateStmt3.setString(1, (String)value);
				updateStmt3.setInt(2, rowIndex+1);
				updateStmt3.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.fireTableRowsUpdated(rowIndex, columnIndex);
	}
	
	
	public CycleHolder getCycleAt(int rowIndex){
		return cycleList.get(rowIndex);
	}

	public void addRow(CycleHolder cycleHolder){
		cycleList.add(cycleHolder);
	//	String s = String.format("Insert into %s (product_cycle_name,product_cycle_date,product_cycle_remark) values ('%s','%s','%s')", cycleName,cycleHolder.getCycleName(),cycleHolder.getCycleDate(),cycleHolder.getCycleRemark().toString());
		try {
			insertStmt.setString(1, cycleHolder.getCycleName());
			insertStmt.setInt(2, cycleHolder.getCycleDay());
			insertStmt.setTimestamp(3, cycleHolder.getCycleDate());
			insertStmt.setString(4, cycleHolder.getCycleRemark().toString());
			insertStmt.addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.fireTableDataChanged();
	}

//	public void removeRow(int row){
//		String name = "";
//		if(row>=0 && row<=cycleList.size()){
//			name = cycleList.get(row).getCycleName().trim();
//			cycleList.remove(row);
//		}
//		
//		String s = String.format("Delete from %s where product_cycle_name='%s'", cycleName,name);
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
		stmtList.add(updateStmt3);
		int rowsAffected = CycleDao.ExecuteBatch(stmtList);
		closeDB();
		return rowsAffected;
	}
}
