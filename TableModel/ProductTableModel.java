package TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import DBUtils.ClassDao;
import DBUtils.DBConnection;
import DBUtils.ProductDao;
import DBUtils.UnitDao;
import DBUtils.VendorDao;
import HolderClass.ProductHolder;

public class ProductTableModel extends AbstractTableModel  {
	private ArrayList<PreparedStatement> stmtList= new ArrayList<PreparedStatement>();
	private String className = "product";
	private ArrayList<ProductHolder> productList ;
	private String[]columnNames;
	private boolean isEditable;
	private JComboBox comboClass;
	private JComboBox comboUnit;
	private JComboBox comboVendor;
	private Timestamp t;
	
	private Connection conn= null;
	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt2 = null;
	private PreparedStatement updateStmt3 = null;
	private PreparedStatement updateStmt4 = null;
	private PreparedStatement updateStmt6 = null;
	private PreparedStatement updateStmt7= null;
	private PreparedStatement updateStmt8 = null;
	private PreparedStatement updateStmt9 = null;
	private PreparedStatement updateStmt10 = null;
	
	
	private String insertQuery1 = "Insert into "+className+" (product_name,product_specification,product_unit,product_code,product_myanmar_name,product_date,product_remark,vendor_id,product_person_in_charge,product_price,product_class_id,product_photo) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery2 = "Update "+className+" set product_name=?,product_last_modified_date=? where product_id=?";
	private String updateQuery3 = "Update "+className+" set product_myanmar_name=?,product_last_modified_date=? where product_id=?";
	private String updateQuery4 = "Update "+className+" set product_specification=?,product_last_modified_date=? where product_id=?";
	private String updateQuery6 = "Update "+className+" set product_class_id=?,product_last_modified_date=? where product_id=?";
	private String updateQuery7 = "Update "+className+" set product_price=?,product_last_modified_date=? where product_id=?";
	private String updateQuery8 = "Update "+className+" set product_unit=?,product_last_modified_date=? where product_id=?";
	private String updateQuery9 = "Update "+className+" set vendor_id=?,product_last_modified_date=? where product_id=?";
	private String updateQuery10 = "Update "+className+" set product_person_in_charge=?,product_last_modified_date=? where product_id=?";
	
	
	
	public ProductTableModel(ArrayList<ProductHolder> productList,String[] columnNames){
		this.productList = productList;
		this.columnNames = columnNames;
		
		//create DBConnection
		initDB();
		
	}
	public void initDB(){
		comboClass = new JComboBox(ClassDao.getNames());
		comboUnit = new JComboBox(UnitDao.getName());
		comboVendor = new JComboBox(VendorDao.getNames());
		
		conn = DBConnection.getMySQLConnection();
		try {
			insertStmt = conn.prepareStatement(insertQuery1);
			updateStmt2 = conn.prepareStatement(updateQuery2);
			updateStmt3 = conn.prepareStatement(updateQuery3);
			updateStmt4 = conn.prepareStatement(updateQuery4);
			updateStmt6 = conn.prepareStatement(updateQuery6);
			updateStmt7 = conn.prepareStatement(updateQuery7);
			updateStmt8 = conn.prepareStatement(updateQuery8);
			updateStmt9 = conn.prepareStatement(updateQuery9);
			updateStmt10 = conn.prepareStatement(updateQuery10);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar calendar = Calendar.getInstance();
		t = new Timestamp(calendar.getTimeInMillis());
	}
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
	
	public void setData(ArrayList<ProductHolder> list){
		//shallow copy 
		ArrayList<ProductHolder> newList = new ArrayList<ProductHolder>();
		
		for(ProductHolder holder : list){
			ProductHolder newHolder = new ProductHolder();
			newHolder.setProductName(holder.getProductName());
			newHolder.setProductSpec(holder.getProductSpec());
			newHolder.setProductUnit(holder.getProductUnit());
			newHolder.setProductCode(holder.getProductCode());
			newHolder.setProductMyanmarName(holder.getProductMyanmarName());
			newHolder.setProductRemark(holder.getProductRemark());
			newHolder.setProductVendor(holder.getProductVendor());
			newHolder.setProductPersonInCharge(holder.getProductPresonInCharge());
			newHolder.setProductPrice(holder.getProductPrice());
			newHolder.setProductClass(holder.getProductClass());
			newHolder.setProductPhoto(holder.getProductPhoto());
			newHolder.setProductClassId(holder.getProductClassId());
			newHolder.setVendorId(holder.getVendorId());
			newList.add(newHolder);
		}
		productList = newList;
		this.fireTableDataChanged();
	}
	
	public boolean isCellEditable(int row, int column){
		if(column == 1 || column == 5)
			return false;
		if(isEditable)
			return true;
		return false;
	   
	}
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return productList.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder product
		return columnNames.length;
	}

	public Class<?> getColumnClass(int column){
			
			switch(column){
			case 0 : return Integer.class; 
			case 1 : return String.class;
			case 2 : return String.class;
			case 3 : return String.class;
			case 4 : return String.class;
			case 5 : return String.class;
			case 6 : return	JComboBox.class;
			case 7 : return String.class;
			case 8 : return JComboBox.class;
			case 9 : return JComboBox.class;
			case 10 : return String.class;
			default : return Object.class;
			}
		}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		ProductHolder productHolder = productList.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex+1;
			break;
		case 1: 
			value = productHolder.getProductCode();
			break;
		case 2:
			value = productHolder.getProductName();
			break;
		case 3:
			value = productHolder.getProductMyanmarName();
			break;
		case 4:
			value =  productHolder.getProductSpec();
			break;
		case 5:
			value= productHolder.getProductPhoto();
			break;	
		case 6:
			value = productHolder.getProductClass();	//combo
			break;
		case 7:
			value = productHolder.getProductPrice();
			break;
		case 8:
			value = productHolder.getProductUnit();		//combo
			break;
		case 9:
			value = productHolder.getProductVendor();	//combo
			break;
		case 10:
			value = productHolder.getProductPresonInCharge();
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
		ProductHolder productHolder = productList.get(rowIndex);
		switch(columnIndex){
		case 2:
			productHolder.setProductName((String)value);
			try {
				updateStmt2.setString(1, (String)value);
				updateStmt2.setTimestamp(2, t);
				updateStmt2.setInt(3, rowIndex+1);
				updateStmt2.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			productHolder.setProductMyanmarName((String)value);
			
			try {
				updateStmt3.setString(1, (String)value);
				updateStmt3.setTimestamp(2, t);
				updateStmt3.setInt(3, rowIndex+1);
				updateStmt3.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			productHolder.setProductSpec((String)value);
			try {
				updateStmt4.setString(1, (String)value);
				updateStmt4.setTimestamp(2, t);
				updateStmt4.setInt(3, rowIndex+1);
				updateStmt4.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			productHolder.setProductClass((String)value);
			try {
				updateStmt6.setInt(1, ClassDao.getIdByName((String)value));
				updateStmt6.setTimestamp(2, t);
				updateStmt6.setInt(3, rowIndex+1);
				updateStmt6.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
			productHolder.setProductPrice((String)value);
			try {
				updateStmt7.setString(1, (String)value);
				updateStmt7.setTimestamp(2, t);
				updateStmt7.setInt(3, rowIndex+1);
				updateStmt7.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 8:
			productHolder.setProductUnit((String)value);
			try {
				updateStmt8.setInt(1, UnitDao.getIdByName((String)value));
				updateStmt8.setTimestamp(2, t);
				updateStmt8.setInt(3, rowIndex+1);
				updateStmt8.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 9:
			productHolder.setProductVendor((String)value);
			try {
				updateStmt9.setInt(1, VendorDao.getIdByName((String)value));
				updateStmt9.setTimestamp(2, t);
				updateStmt9.setInt(3, rowIndex+1);
				updateStmt9.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 10:
			productHolder.setProductPersonInCharge((String)value);
			try {
				updateStmt10.setString(1, (String)value);
				updateStmt10.setTimestamp(2, t);
				updateStmt10.setInt(3, rowIndex+1);
				updateStmt10.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		this.fireTableRowsUpdated(rowIndex, columnIndex);
	}
	

	
	public ProductHolder getProductAt(int rowIndex){
		return productList.get(rowIndex);
	}
	
	public ArrayList<ProductHolder> getProductList(){
		return productList;
	}

	public int addRow(ProductHolder productHolder){
		productList.add(productHolder);
		//get vendor Id,productClassId
		int vendorId=  VendorDao.getIdByName(productHolder.getProductVendor());
		int classId = ClassDao.getIdByName(productHolder.getProductClass());
		int unitId = UnitDao.getIdByName(productHolder.getProductUnit());
		System.out.println("unitName :"+productHolder.getProductUnit());
		System.out.println("Vendor Id:"+vendorId+", class Id :"+classId+", unitId :"+unitId);
		if(vendorId != -1 && classId != -1 && unitId != -1){
				try {
					insertStmt.setString(1, productHolder.getProductName());
					insertStmt.setString(2, productHolder.getProductSpec());
					insertStmt.setInt(3, unitId);
					insertStmt.setString(4, productHolder.getProductCode());
					insertStmt.setString(5, productHolder.getProductMyanmarName());
					insertStmt.setTimestamp(6, productHolder.getProductDate());
					insertStmt.setString(7, productHolder.getProductRemark());
					insertStmt.setInt(8, vendorId);
					insertStmt.setString(9, productHolder.getProductPresonInCharge());
					insertStmt.setString(10, productHolder.getProductPrice());
					insertStmt.setInt(11,classId);
					insertStmt.setString(12, productHolder.getProductPhoto());
					
					insertStmt.addBatch();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}else{
			return -1;
		}
	
		this.fireTableDataChanged();
		return 0;
	}

//	public void removeRow(int row){
//		String name = "";
//		if(row>=0 && row<=productList.size()){
//			name = productList.get(row).getProductName().trim();
//			productList.remove(row);
//		}
//		
//		String s = String.format("Delete from %s where product_product_name='%s'", productName,name);
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
		stmtList.add(updateStmt2);
		stmtList.add(updateStmt3);
		stmtList.add(updateStmt4);
		stmtList.add(updateStmt6);
		stmtList.add(updateStmt7);
		stmtList.add(updateStmt8);
		stmtList.add(updateStmt9);
		stmtList.add(updateStmt10);
		int rowsAffected = ProductDao.ExecuteBatch(stmtList);
		closeDB();
		return rowsAffected;
	}
}
