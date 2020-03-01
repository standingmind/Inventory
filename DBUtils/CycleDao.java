package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.CycleHolder;

public class CycleDao {

	public static void main(String[] args) throws Exception{
		Connection conn  = DBConnection.getMySQLConnection();
		System.out.println(getIdByName("¶þÖÜ",conn));
	}
	
	public static String[] getName(){
		String[] cycle = new String[0];
		int size = 0;
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select count(DISTINCT cycle_name) from cycle";
		Statement stmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next())
				size = rs.getInt(1);
			
			if(size > 0 ){
				cycle= new String[size];
				String query2 = "Select DISTINCT cycle_name from cycle";
				 rs = stmt.executeQuery(query2);
				 
				 int count = 0;
				 while(rs.next()){
					cycle[count++] =  rs.getString("cycle_name");
					
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
		
		 
		return cycle;
		
	}
	
	public static int getIdByName(String name,Connection conn) throws Exception{
		int id = -1;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "Select cycle_id from cycle where cycle_name = '"+name+"'";
		if(conn != null){
			try {
				conn = DBConnection.getMySQLConnection();
				stmt =  conn.createStatement();
				rs = stmt.executeQuery(query);
				
				if(rs.next()){
					id = rs.getInt(1);
				}
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBConnection.closeResultSet(rs);
				DBConnection.closeStatement(stmt);
				DBConnection.closeConnection(conn);
			}
		}
		
		
		return id;
	}
	
	public static ArrayList<CycleHolder> getAll() {
		ArrayList<CycleHolder> cycleList = new ArrayList<CycleHolder> ();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getMySQLConnection();
			String query = "Select cycle_id,cycle_name,cycle_day,cycle_remark from cycle";
			stmt =  conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				CycleHolder cycleHolder = new CycleHolder();
				cycleHolder.setCycleId(rs.getInt("cycle_id"));
				cycleHolder.setCycleName(rs.getString("cycle_name"));
				cycleHolder.setCycleDay(rs.getInt("cycle_day"));
				cycleHolder.setCycleRemark(rs.getString("cycle_remark"));
				cycleList.add(cycleHolder);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		return cycleList;
		
	}
	
	public static int getDayByName(String cycleName){
		
		int day = -1;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getMySQLConnection();
			String query = "Select cycle_day from cycle where cycle_name='"+cycleName+"'";
			stmt =  conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				day = rs.getInt(1);
			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		return day;
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
	

}
