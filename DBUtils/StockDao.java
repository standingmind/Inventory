package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.StockHolder;

public class StockDao {
	
	public static ArrayList<StockHolder> getAll(){
		Connection conn = null;
		ResultSet rs = null;
		
		ArrayList<StockHolder> stkHolder = new ArrayList<StockHolder>();
		String query = "Select Distinct stock_id,product_code,product_name,product_myanmar_name,product_specification,product_price,unit_name,stock_qty,stock_dosage,cycle_name,stock_safeNo,vendor_name,product_person_in_charge,product_photo\n"+
				"From stock as s, product as p,vendor as v,product_class as pc, codetype as ct, cycle as cy,unit as u\n"+
				"Where p.vendor_id = v.vendor_id and p.product_id = s.product_id and s.cycle_id = cy.cycle_id and p.product_unit=u.unit_id"; 

		PreparedStatement prpStmt =null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
			rs = prpStmt.executeQuery();
			
			
			while(rs.next()){
				StockHolder stk = new StockHolder();
				stk.setStockId(rs.getInt("stock_id"));
				stk.setProductCode(rs.getString("product_code"));
				stk.setProductName(rs.getString("product_name"));
				stk.setProductMyanmarName(rs.getString("product_myanmar_name"));
				stk.setProductSpec(rs.getString("product_specification"));
				stk.setProductPrice(rs.getString("product_price"));
				stk.setProductUnit(rs.getString("unit_name"));
				stk.setStockQty(rs.getInt("stock_qty"));
				stk.setStockDosage(rs.getInt("stock_dosage"));
				stk.setCycle(rs.getString("cycle_name"));
				stk.setProductVendor(rs.getString("vendor_name"));
				stk.setProductPersonInCharge(rs.getString("product_person_in_charge"));
				stk.setProductImage(rs.getString("product_photo"));
				stk.setStockSafeNo(rs.getInt("stock_safeNo"));
				stkHolder.add(stk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		
		return stkHolder;
	}
	
	public static ArrayList<Integer> getAllStockId(){
		ArrayList<Integer> idList = new ArrayList<Integer>();
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select stock_id from stock";
		PreparedStatement prpStmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt  = conn.prepareStatement(query);
			rs = prpStmt .executeQuery();
			while(rs.next()){
				int id = rs.getInt("stock_id");
				idList.add(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		return idList;
	}
	
	
	public static StockHolder getAllById(int id) {
		StockHolder stk = new StockHolder();
		Connection conn = null;
		PreparedStatement prpStmt = null;
		ResultSet rs = null;
		String query = "Select Distinct stock_id,product_code,product_name,product_myanmar_name,product_specification,product_price,unit_name,stock_qty,stock_dosage,cycle_name,vendor_name,stock_safeNo,product_person_in_charge,product_photo\n"+
				"From stock as s, product as p,vendor as v,product_class as pc, codetype as ct, cycle as cy,unit as u\n"+
				"Where p.vendor_id = v.vendor_id and p.product_id = s.product_id and s.cycle_id = cy.cycle_id and p.product_unit=u.unit_id and s.stock_id = ?"; 

		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
			prpStmt.setInt(1,id);
			rs = prpStmt.executeQuery();
			
				rs.next();
				
				stk.setStockId(rs.getInt("stock_id"));
				stk.setProductCode(rs.getString("product_code"));
				stk.setProductName(rs.getString("product_name"));
				stk.setProductMyanmarName(rs.getString("product_myanmar_name"));
				stk.setProductSpec(rs.getString("product_specification"));
				stk.setProductPrice(rs.getString("product_price"));
				stk.setProductUnit(rs.getString("unit_name"));
				stk.setStockQty(rs.getInt("stock_qty"));
				stk.setStockDosage(rs.getInt("stock_dosage"));
				stk.setCycle(rs.getString("cycle_name"));
				stk.setProductVendor(rs.getString("vendor_name"));
				stk.setProductPersonInCharge(rs.getString("product_person_in_charge"));
				stk.setProductImage(rs.getString("product_photo"));
				stk.setStockSafeNo(rs.getInt("stock_safeNo"));
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		
		return stk;
	}
	
	public static boolean isIdExistInStock(int id){
		int ans =0;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String query = "Select Exists(Select * from stock where product_id='"+id+"')";
		try{
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs  = stmt.executeQuery(query);
			if(rs.next()){
				ans = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);			
		}
		
		return ans == 1?true:false;
	}
	
	
	public static int getStockIdByProductId(int productId){
		int id = -1;
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select count(stock_id) from stock where product_id = ?";
		PreparedStatement prpStmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt  = conn.prepareStatement(query);
			prpStmt .setInt(1, productId);
			rs = prpStmt .executeQuery();
			if(rs.next()){
				System.out.println("output is :"+rs.getInt(1));
				if(rs.getInt(1) == 0)
					return id;
			}
			
			query = "Select stock_id from stock where product_id = ?";
			prpStmt  = conn.prepareStatement(query);
			prpStmt .setInt(1, productId);
			rs = prpStmt .executeQuery();
			if(rs.next()){
				id = rs.getInt("stock_id");
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		return id;
		
	}
	
	public static int addStockQty(int productId, int quantity,Connection conn)throws Exception{
		int rowsAffected = 0;
		PreparedStatement prpStmt = null;
		if(conn!=null){
			String updateQuery = "Update stock set stock_qty = stock_qty+? where product_id=?";
			
			try{
				conn =DBConnection.getMySQLConnection();
				prpStmt = conn.prepareStatement(updateQuery);
				prpStmt.setInt(1, quantity);
				prpStmt.setInt(2, productId);
				rowsAffected = prpStmt.executeUpdate();
				
			}catch(Exception e){
				throw e;
			}finally{
				DBConnection.closePreparedStatement(prpStmt);
				DBConnection.closeConnection(conn);
			}
		}
		
		
	return rowsAffected;
	}
	
	public static int insertStock(int productId,int stockQty,int stockDosage,int cycleId,int stockSafeNo){
		int rowAffected = 0;
		Connection conn = null;
		String query = "insert into stock(product_id,stock_qty,stock_dosage,cycle_id,stock_safeNo) values(?,?,?,?,?)";
		PreparedStatement prpStmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
			prpStmt.setInt(1,productId);
			prpStmt.setInt(2,stockQty);
			prpStmt.setInt(3, stockDosage);
			prpStmt.setInt(4, cycleId);
			prpStmt.setInt(5, stockSafeNo);
			rowAffected = prpStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		return rowAffected;
	}
	
	public static int getStockQtyById(int id){
		int ans =-1;
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select stock_qty from stock where stock_id = ? ";
		PreparedStatement prpStmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
			prpStmt.setInt(1,id);
			rs = prpStmt.executeQuery();
			if(rs.next()){
				ans = rs.getInt(1);
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		return ans;
		
		
	}
	
	public static void main(String[] args) throws Exception{
		ArrayList<StockHolder> holder = getAll();
		for(StockHolder h : holder){
			System.out.println(h.toString());
		}
		StockHolder holder2 = getAllById(1);
		System.out.println(holder2.toString());
		
	}
}
