package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.StockShelfHolder;

public class StockShelfDao {
	
	
	public static ArrayList<StockShelfHolder> getAll(){
		ArrayList<StockShelfHolder> stsHolder = new ArrayList<StockShelfHolder>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT shelf_name,shelf_seat_name,stock_id"
				+ " FROM shelf as s,shelf_seat as ss,stock_shelf as sts "
				+ "WHERE s.shelf_id = sts.shelf_id and sts.shelf_seat_id = ss.shelf_seat_id";
		
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				StockShelfHolder holder = new StockShelfHolder();
				holder.setShelf(rs.getString("shelf_name"));
				holder.setShelfSeat(rs.getInt("shelf_seat_name"));
				holder.setStockId(rs.getInt("stock_id"));
				stsHolder.add(holder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		return stsHolder;
	}



	public static StockShelfHolder getStockShelfByStockId(int stockId){
		StockShelfHolder stockShelfList = new StockShelfHolder();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT shelf_name,shelf_seat_name,stock_id"
				+ " FROM shelf as s,shelf_seat as ss,stock_shelf as sts "
				+ "WHERE s.shelf_id = sts.shelf_id and sts.shelf_seat_id = ss.shelf_seat_id and stock_id =?";
		
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, stockId);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				stockShelfList.setShelf(rs.getString("shelf_name"));
				stockShelfList.setShelfSeat(rs.getInt("shelf_seat_name"));
				stockShelfList.setStockId(rs.getInt("stock_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		
		return stockShelfList;
	}
	
	public static void main(String[] args) throws SQLException, Exception{
		ArrayList<StockShelfHolder> sts = getAll();
		System.out.println(sts.toString());
	}
}
