package DBUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;


public class DBConnection {
	public static Connection getMySQLConnection() {
		String hostName = "localhost";
		String dbName = "Inventory";
		String userName = "root";
		String password = "root";
		
		return getMySQLConnection(hostName,dbName,userName,password);
	}
	
	public static Connection getMySQLConnection(String hostName,String dbName,String userName,String password) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://"+hostName+":3306/"+dbName+"?characterSetResults=UTF-8&&characterEncoding=UTF-8&&useUnicode=yes";
			
			 conn = DriverManager.getConnection(connectionURL,userName,password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn) throws Exception{
	if(conn.isValid(0)){
			System.out.println("connection is not valid");
		
		if (conn == null){
			System.out.println("connection is null");
		
		}else{
			conn.close();
			//System.out.println("connection is not null");
			//conn.close();
		}
	}
	}
	
	public static void main(String[] args)throws Exception{
		Connection con = getMySQLConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from vendor");
		while(rs.next()){
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDate(4)+" "+rs.getString(5));
		}
//		
//		//get today date instance
//		Calendar calendar = Calendar.getInstance();
//		
//		//get date
//		Date date = new Date(calendar.getTime().getTime());
//		
//		//first way of get both date time
//		java.sql.Timestamp t = new java.sql.Timestamp(calendar.getTimeInMillis());
//		
//		
//		
//		//insert 
//		PreparedStatement ps = con.prepareStatement("Insert into product (vendor_name,vendor_contact,vendor_date,vendor_remark) values(?,?,?,?) ");
//		
//		ps.setString(1, "ÀÖ¼Î");
//		ps.setString(2, "09797272827");
//		ps.setTimestamp(3, t);
//		ps.setString(4,"×¼±¸");
//		
//		int record = ps.executeUpdate();
//		System.out.println(record+" record successfully inserted!");

		
	}
	
	public static void closeConnection(Connection con){
		if(con!=null){
			try{
				con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
			
		}
	}
	public static void closeStatement(Statement con){
		if(con!=null){
			try{
				con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
			
		}
	}
	public static void closeResultSet(ResultSet con){
		if(con!=null){
			try{
				con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
			
		}
	}
	
	public static void closePreparedStatement(PreparedStatement con){
		if(con!=null){
			try{
				con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
			
		}
	}
	
	
}
