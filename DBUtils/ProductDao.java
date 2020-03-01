package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.ProductHolder;

public class ProductDao {
	public static ArrayList<ProductHolder> getWithCode(String code){
		ArrayList<ProductHolder> productList = new ArrayList<ProductHolder>();
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select product_id,product_name,product_specification,unit_name,product_code,product_myanmar_name,"
				+ "product_date,product_remark,vendor_id,product_person_in_charge,product_price,product_class_id,product_photo"
				+ " from product as p,unit as u where p.product_unit=u.unit_id and product_code =? ";
		PreparedStatement prpStmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
			prpStmt.setString(1, code);
			rs =  prpStmt.executeQuery();
			
			while(rs.next()){
				ProductHolder holder = new ProductHolder();
				holder.setProductId(rs.getInt("product_id"));
				holder.setProductName(rs.getString("product_name"));
				holder.setProductSpec(rs.getString("product_specification"));
				holder.setProductUnit(rs.getString("unit_name"));
				holder.setProductCode(rs.getString("product_code"));
				holder.setProductMyanmarName(rs.getString("product_myanmar_name"));
				holder.setProductDate(rs.getTimestamp("product_date"));
				holder.setProductRemark(rs.getString("product_remark"));
				holder.setVendorId(rs.getInt("vendor_id"));
				holder.setProductPersonInCharge(rs.getString("product_person_in_charge"));
				holder.setProductPrice(rs.getString("product_price"));
				holder.setProductClassId(rs.getInt("product_class_id"));
				holder.setProductPhoto(rs.getString("product_photo"));
				productList.add(holder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		return productList;
	}
	
	public static ArrayList<ProductHolder> getAll(){
		ArrayList<ProductHolder> productList = new ArrayList<ProductHolder>();
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select product_id,product_name,product_specification,unit_name,product_code,product_myanmar_name,"
				+ "product_date,product_remark,p.product_person_in_charge,p.vendor_id,product_price,p.product_class_id,product_photo,product_class_name,vendor_name,p.product_unit"
				+ " from product as p,unit as u,vendor as v,product_class as pc where p.product_unit=u.unit_id and p.vendor_id = v.vendor_id and p.product_class_id=pc.product_class_id";
		PreparedStatement prpStmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
			rs =  prpStmt.executeQuery();
			
			while(rs.next()){
				ProductHolder holder = new ProductHolder();
				holder.setProductId(rs.getInt("product_id"));
				holder.setProductName(rs.getString("product_name"));
				holder.setProductSpec(rs.getString("product_specification"));
				holder.setProductUnit(rs.getString("unit_name"));
				holder.setProductCode(rs.getString("product_code"));
				holder.setProductMyanmarName(rs.getString("product_myanmar_name"));
				holder.setProductDate(rs.getTimestamp("product_date"));
				holder.setProductRemark(rs.getString("product_remark"));
				holder.setVendorId(rs.getInt("vendor_id"));
				holder.setProductPersonInCharge(rs.getString("product_person_in_charge"));
				holder.setProductPrice(rs.getString("product_price"));
				holder.setProductClassId(rs.getInt("product_class_id"));
				holder.setProductPhoto(rs.getString("product_photo"));
				holder.setProductClass(rs.getString("product_class_name"));
				holder.setProductVendor(rs.getString("vendor_name"));
				holder.setUnitId(rs.getInt("product_unit"));
				productList.add(holder);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		
		return productList;
	}
	
	public static String[] getPersonInCharge(){
		int size = 0;
		int count = 0 ;
		String[] personInCharge = new String[0];
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getMySQLConnection();
			String query1 = "Select count(Distinct product_person_in_charge) from product";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query1);
			if(rs.next()){
				size = rs.getInt(1);
			}
			if(size >0){
				personInCharge = new String[size];
				String query2 = "Select Distinct product_person_in_charge from product";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query2);
				while(rs.next()){
					personInCharge[count++] = rs.getString("product_person_in_charge");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		return personInCharge;
	}
	
	public static int ExecuteBatch(ArrayList<PreparedStatement> stmtList){
		int[] a;
		System.out.println("stmt List :"+stmtList.size());
		int rowsAffected = 0;
		if(stmtList.size() >0 ){
			for(PreparedStatement prpStmt : stmtList){
				try {
				
					a = prpStmt.executeBatch();
					for(int i=0 ; i<a.length ;i ++){
						System.out.println(a[i]);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
	}
	
	
	public static void main(String[] args){
		ArrayList<ProductHolder> productList = getAll();
		for(ProductHolder h:productList){
			System.out.println(h.toString());
		}
	}
}
