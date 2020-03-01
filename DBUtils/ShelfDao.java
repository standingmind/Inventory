package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.ShelfHolder;

public class ShelfDao {

	
	public static String[] getName(){
		int size = 0;
		String[] shelf = new String[0];
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			String query = "Select count(DISTINCT shelf_name) from shelf";
			rs = stmt.executeQuery(query);
			if(rs.next())
				size = rs.getInt(1);
			if(size>0){
				shelf = new String[size];
				query = "Select DISTINCT shelf_name from shelf";
				rs = stmt.executeQuery(query);
				int count = 0;
				while(rs.next()){
					shelf[count++] = rs.getString("shelf_name");
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
		
		return shelf;
		
	}
	
	
	public static ArrayList<ShelfHolder> getAll(){
		ArrayList<ShelfHolder> shelfList = new ArrayList<ShelfHolder> ();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			String query = "Select shelf_id,shelf_name,shelf_remark from shelf";
			rs = stmt.executeQuery(query);
				while(rs.next()){
					ShelfHolder shelfHolder = new ShelfHolder();
					shelfHolder.setShelfId(rs.getInt("shelf_id"));
					shelfHolder.setShelfName(rs.getString("shelf_name"));
					shelfHolder.setShelfRemark(rs.getString("shelf_remark"));
					shelfList.add(shelfHolder);
					
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		return shelfList;
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
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ShelfHolder> shelfList = getAll();
		System.out.println(shelfList.toString());
	}

}
