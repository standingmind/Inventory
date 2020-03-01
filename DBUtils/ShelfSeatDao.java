package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.ShelfSeatHolder;

public class ShelfSeatDao {

	public static String[] getName(){
		String[] shelfSeat = new String[0];
		int size = 0;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			String query = "Select count(DISTINCT shelf_seat_name) from shelf_seat";
			rs = stmt.executeQuery(query);
			if(rs.next())
				size = rs.getInt(1);
				
			if(size >0){
				shelfSeat = new String[size];
				query = "Select DISTINCT shelf_seat_name from shelf_seat";
				rs = stmt.executeQuery(query);
				int count = 0;
				while(rs.next()){
					shelfSeat[count++] = rs.getString("shelf_seat_name");
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
		
		return shelfSeat;
		
	}
	
	
	
	public static ArrayList<ShelfSeatHolder> getAll(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ShelfSeatHolder> shelfSeatList = new ArrayList<ShelfSeatHolder>() ;
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			String query = "Select shelf_seat_id,shelf_seat_name,shelf_seat_remark from shelf_seat";
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				ShelfSeatHolder shelfSeatHolder = new ShelfSeatHolder();
				shelfSeatHolder.setShelfSeatId(rs.getInt("shelf_seat_id"));
				shelfSeatHolder.setShelfSeatName(rs.getString("shelf_seat_name"));
				shelfSeatHolder.setShelfSeatRemark(rs.getString("shelf_seat_remark"));
				shelfSeatList.add(shelfSeatHolder);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
	
		return shelfSeatList;
		
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
		String[] shelfSeat = getName();
		for(int i=0 ; i<shelfSeat.length ; i++){
			System.out.println(shelfSeat[i]);
		}
		
		ArrayList<ShelfSeatHolder> shelfList = getAll();
		System.out.println(shelfList.toString());
	}

}
