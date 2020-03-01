package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.ClassHolder;
import HolderClass.UnitHolder;


//don't need unit holder
public class UnitDao {
	
	public static void main(String[] args) throws Exception{
		String[] unit =  getName();
		for(int i=0 ; i<unit.length ; i++){
			System.out.println(unit[i]);
		}
	}
	
	public static ArrayList<UnitHolder> getAll(){
		ArrayList<UnitHolder> unitList  = new ArrayList<UnitHolder>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
			conn = DBConnection.getMySQLConnection();
			String query = "Select unit_id,unit_name,unit_remark from unit";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				UnitHolder holder = new UnitHolder();
				holder.setUnitId(rs.getInt("unit_id"));
				holder.setUnitName(rs.getString("unit_name"));
				holder.setUnitRemark(rs.getString("unit_remark"));
				unitList.add(holder);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		
		return unitList;
	}
	
	public static String[] getName(){
		String[] unit = new String[0];
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "Select count(DISTINCT unit_name) from unit";
		
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			rs.next();
			int size = rs.getInt(1);
			
			System.out.println(size);
			if(size > 0){
				unit = new String[size];
				String query2 = "Select DISTINCT unit_name from unit";
				rs = stmt.executeQuery(query2);
				 
				 int count = 0;
				 while(rs.next()){
					unit[count++] =  rs.getString("unit_name");
					
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
		return unit;
		
	}
	
	public static int getIdByName(String name){
		int id=-1;
		Connection conn = null;
		Statement stmt= null;
		ResultSet rs = null;
		String query = "Select unit_id from unit where unit_name='"+name+"'";
		
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			
			if(rs.next()){
				id = rs.getInt("unit_id");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		return id;
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
