package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import HolderClass.ClassHolder;

public class ClassDao {
	
	public static void main(String[] args) throws Exception{
		String[] productClass =  getNames();
		for(int i=0 ; i<productClass.length ; i++){
			System.out.println(productClass[i]);
		}
	}
	
	public static String[] getNames(){
		String[] productClass = new String[0];
		int size = 0;
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select count(DISTINCT product_class_name) from product_class";
		Statement stmt = null;
		try {
			 conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next())
				size = rs.getInt(1);
			
			if(size > 0){
				productClass= new String[size];
				String query2 = "Select DISTINCT product_class_name from product_class";
				 rs = stmt.executeQuery(query2);
				 
				 int count = 0;
				 while(rs.next()){
					productClass[count++] =  rs.getString("product_class_name");
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		return productClass;
		
	}
	
	public static ArrayList<ClassHolder> getAll(){
		ArrayList<ClassHolder> classList  = new ArrayList<ClassHolder>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
			conn = DBConnection.getMySQLConnection();
			String query = "Select product_class_id,product_class_name,product_class_remark from product_class";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ClassHolder holder = new ClassHolder();
				holder.setClassId(rs.getInt("product_class_id"));
				holder.setClassName(rs.getString("product_class_name"));
				holder.setClassRemark(rs.getString("product_class_remark"));
				classList.add(holder);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		
		return classList;
		
	}
	
	public static int ExecuteBatch(ArrayList<PreparedStatement> stmtList){
		int rowsAffected = 0;
		if(stmtList.size() >0 ){
			for(PreparedStatement prpStmt : stmtList){
				try {
					prpStmt.executeBatch();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
	}
	
	public static String getNameById(int id){
		String name= "";
		Connection conn = null;
		String query = "Select product_class_name from product_class where product_class_id = ?";
		PreparedStatement prpStmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
		
			prpStmt.setInt(1, id);
			rs = prpStmt.executeQuery();
			if(rs.next()){
				name = rs.getString("product_class_name");
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		return name;
	}
	
	public static int getIdByName(String name){
		int ans= -1;
		Connection conn = null;
		String query = "Select product_class_id from product_class where product_class_name= ?";
		PreparedStatement prpStmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
		
			prpStmt.setString(1, name);
			rs = prpStmt.executeQuery();
			if(rs.next()){
				ans = rs.getInt("product_class_id");
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
}
