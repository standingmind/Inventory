package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import HolderClass.ImportProductHolder;

public class ImportProductDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void executeBatch(ArrayList<ImportProductHolder> importList)throws Exception{
	
		String insertQuery2 = "Insert into import(product_id,import_person,import_operation_number,import_qty,import_dosage,"
				+ "import_cycle,import_leadtime,import_vendor,import_date,import_remark,import_arrival_location,"
				+ "import_price,import_lastUpdated,import_premeasure) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String updateQuery = "Update stock set stock_qty = stock_qty+? where product_id=?";
		String insertQuery3 = "insert into stock(product_id,stock_qty,stock_dosage,cycle_id,stock_safeNo,stock_premeasure) values(?,?,?,?,?,?)";
		
		Connection conn = null;
		
		PreparedStatement prpStmt2 = null;
		PreparedStatement prpStmt3 = null;
		PreparedStatement prpStmt4 = null;
		try{
			conn = DBConnection.getMySQLConnection();
			prpStmt2 = conn.prepareStatement(insertQuery2);
			prpStmt3 = conn.prepareStatement(updateQuery);
			prpStmt4 = conn.prepareStatement(insertQuery3);
			conn.setAutoCommit(false);
			for(ImportProductHolder importHolder : importList){
				int id = importHolder.getProductId();
				if(StockDao.isIdExistInStock(id)){
					int importQty = importHolder.getImportQty();
					//add item to stock
					int rowsAffected = 0;
					prpStmt3.setInt(1, importQty);
					prpStmt3.setInt(2, id);
					rowsAffected = prpStmt3.executeUpdate();
					System.out.println("existing stock qty added :"+rowsAffected+" affected");
				}else{
					int cycleId = CycleDao.getIdByName(importHolder.getCycle(),conn);
					//insert stock if item is not existed in stock
					int rowsAffected = 0 ;
					prpStmt4.setInt(1,importHolder.getProductId());
					prpStmt4.setInt(2,importHolder.getImportQty());
					prpStmt4.setInt(3, importHolder.getDosage());
					prpStmt4.setInt(4, cycleId);
					prpStmt4.setInt(5, importHolder.getSafeStock());
					prpStmt4.setDouble(6, importHolder.getPremeasure());
					rowsAffected = prpStmt4.executeUpdate();
					System.out.println("new stock added : "+rowsAffected+" affected");
				}
				
				//insert import in batch
				prpStmt2.setInt(1, importHolder.getProductId());
				prpStmt2.setString(2, importHolder.getImportPerson());
				prpStmt2.setString(3, importHolder.getOperationNo());
				prpStmt2.setInt(4, importHolder.getImportQty());
				prpStmt2.setInt(5, importHolder.getDosage());
				prpStmt2.setString(6, importHolder.getCycle());
				prpStmt2.setInt(7, importHolder.getLeadTime());
				prpStmt2.setString(8, importHolder.getVendor());
				prpStmt2.setTimestamp(9, importHolder.getImportDate());
				prpStmt2.setString(10, importHolder.getRemark());
				prpStmt2.setString(11, importHolder.getArriveLocation());
				prpStmt2.setString(12, importHolder.getPrice());
				prpStmt2.setTimestamp(13, importHolder.getLastUpdated());
				prpStmt2.setDouble(14, importHolder.getPremeasure());
				prpStmt2.addBatch();
			}
			prpStmt2.executeBatch();
			conn.commit();
		
		}catch(Exception e){
			System.err.println(e);
			conn.rollback();
			throw e;
		}finally{
			DBConnection.closePreparedStatement(prpStmt2);
			DBConnection.closePreparedStatement(prpStmt3);
			DBConnection.closePreparedStatement(prpStmt4);
			DBConnection.closeConnection(conn);
		}
	
	
	}
	
	public static void insertImportBatch(ImportProductHolder importHolder,Connection con,PreparedStatement prpStmt) throws Exception {
	
		if(con != null && prpStmt != null){
			try {
				 	prpStmt.setInt(1, importHolder.getProductId());
					prpStmt.setString(2, importHolder.getImportPerson());
					prpStmt.setString(3, importHolder.getOperationNo());
					prpStmt.setInt(4, importHolder.getImportQty());
					prpStmt.setInt(5, importHolder.getDosage());
					prpStmt.setString(6, importHolder.getCycle());
					prpStmt.setInt(7, importHolder.getLeadTime());
					prpStmt.setString(8, importHolder.getVendor());
					prpStmt.setTimestamp(9, importHolder.getImportDate());
					prpStmt.setString(10, importHolder.getRemark());
					prpStmt.setString(11, importHolder.getArriveLocation());
					prpStmt.setString(12, importHolder.getPrice());
					prpStmt.setTimestamp(13, importHolder.getLastUpdated());
					prpStmt.setDouble(14, importHolder.getPremeasure());
					prpStmt.addBatch();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}
		
	}

}
