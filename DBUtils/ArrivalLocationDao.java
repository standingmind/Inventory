package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.ArrivalLocationHolder;
import HolderClass.CycleHolder;

public class ArrivalLocationDao {
	public static ArrayList<ArrivalLocationHolder> getAll(){
		ArrayList<ArrivalLocationHolder> arrivalLocationList = new ArrayList<ArrivalLocationHolder> ();
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			String query = "Select arrival_location_id,arrival_location_name,arrival_location_remark from arrival_location";
			stmt =  conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				ArrivalLocationHolder arrivalLocationHolder = new ArrivalLocationHolder();
				arrivalLocationHolder.setArrivalLocationId(rs.getInt("arrival_location_id"));
				arrivalLocationHolder.setArrivalLocationName(rs.getString("arrival_location_name"));
				arrivalLocationHolder.setArrivalLocationRemark(rs.getString("arrival_location_remark"));
				arrivalLocationList.add(arrivalLocationHolder);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		return arrivalLocationList;
	}
	
	
	public static String[] getName(){
		String[] locationName= new String[0];
		int size = 0;
		Connection conn = null;
		ResultSet rs = null;
		String query = "Select count(DISTINCT arrival_location_name) from arrival_location";
		Statement stmt = null;
		try {
			 conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next())
				size = rs.getInt(1);
			
			if(size > 0){
				locationName = new String[size];
				String query2 = "Select DISTINCT arrival_location_name from arrival_location";
				 rs = stmt.executeQuery(query2);
				 
				 int count = 0;
				 while(rs.next()){
					locationName[count++] =  rs.getString("arrival_location_name");
					
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
		
		return locationName;
		
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
	
	public static void main(String[] args){
		ArrayList<ArrivalLocationHolder> arrivalList = getAll();
		System.out.println(arrivalList.toString());
	}
}	
