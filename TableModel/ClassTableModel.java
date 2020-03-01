package TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import DBUtils.ClassDao;
import DBUtils.DBConnection;
import HolderClass.ClassHolder;

public class ClassTableModel extends AbstractTableModel  {
	private ArrayList<PreparedStatement> stmtList= new ArrayList<PreparedStatement>();
	private String className = "product_class";
	private ArrayList<ClassHolder> classList ;
	private String[]columnNames;
	private boolean isEditable;
	
	private Connection conn= null;
	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt1 = null;
	private PreparedStatement updateStmt2 = null;
	private String updateQuery1 = "Update "+className+" Set product_class_name=? where product_class_id=?";
	private String updateQuery2 = "Update "+className+" Set product_class_remark=? where product_class_id=?";
	private String insertQuery1 = "Insert into "+className+" (product_class_name,product_class_date,product_class_remark) values (?,?,?)";

	public ClassTableModel(ArrayList<ClassHolder> classList,String[] columnNames){
		this.classList = classList;
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
	
	public void setData(ArrayList<ClassHolder> list){
		//shallow copy 
		ArrayList<ClassHolder> newList = new ArrayList<ClassHolder>();
		
		for(ClassHolder holder : list){
			ClassHolder newHolder = new ClassHolder();
			newHolder.setClassId(holder.getClassId());
			newHolder.setClassName(holder.getClassName());
			newHolder.setClassRemark(holder.getClassRemark());
			newList.add(newHolder);
		}
		classList = newList;
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
		return classList.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder class
		return 3;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		ClassHolder classHolder = classList.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex+1;
			break;
		case 1: 
			value = classHolder.getClassName();
			break;
		case 2:
			value =  classHolder.getClassRemark();
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
		ClassHolder classHolder = classList.get(rowIndex);
		switch(columnIndex){
		case 1:
			classHolder.setClassName((String)value);
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
			classHolder.setClassRemark((String)value);
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
	
	
	public ClassHolder getClassAt(int rowIndex){
		return classList.get(rowIndex);
	}

	public void addRow(ClassHolder classHolder){
		classList.add(classHolder);
		//String s = String.format("Insert into %s (product_class_name,product_class_date,product_class_remark) values ('%s','%s','%s')", className,classHolder.getClassName(),classHolder.getClassDate(),classHolder.getClassRemark().toString());
		try {
			insertStmt.setString(1, classHolder.getClassName());
			insertStmt.setTimestamp(2, classHolder.getClassDate());
			insertStmt.setString(3, classHolder.getClassRemark().toString());
			insertStmt.addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.fireTableDataChanged();
	}

//	public void removeRow(int row){
//		String name = "";
//		if(row>=0 && row<=classList.size()){
//			name = classList.get(row).getClassName().trim();
//			classList.remove(row);
//		}
//		
//		String s = String.format("Delete from %s where product_class_name='%s'", className,name);
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
		int rowsAffected = ClassDao.ExecuteBatch(stmtList);
		closeDB();
		return rowsAffected;
	}
}
