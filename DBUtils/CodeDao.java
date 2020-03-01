package DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CodeDao {
	public static void main(String[] args){
		String[] codeList = getName();
		for(String c:codeList){
			System.out.println(c);
		}
	}
	
	public static String[] getName(){
		String[] codeNames = new String[0];
		int size = 0;
		int count = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			String query = "Select count(DISTINCT product_code) from product";
			String query2 = "Select DISTINCT product_code from product";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				size = rs.getInt(1);
			}
			if(size>0){
				codeNames = new String[size];
				rs = stmt.executeQuery(query2);
				while(rs.next()){
					codeNames[count++] = rs.getString("product_code"); 
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
		
		
		
		return codeNames;
	}
	

}
