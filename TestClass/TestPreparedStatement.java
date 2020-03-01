package TestClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBUtils.DBConnection;

public class TestPreparedStatement {
	static ArrayList<PreparedStatement> stmtList = new ArrayList<PreparedStatement>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getMySQLConnection();
		String query = "Select product_name from product where product_unit=?";
		PreparedStatement prpStmt = conn.prepareStatement(query);
		prpStmt.setString(1, "BX");
		prpStmt.addBatch();
		prpStmt.setString(1, "PR");
		prpStmt.addBatch();
		
		String s = "code_id";
		String query2 = String.format("Select %s from product where product_unit=?",s);
		PreparedStatement prpStmt2 = conn.prepareStatement(query2);
		prpStmt2.setString(1, "BX");
		prpStmt2.addBatch();
		
		stmtList.add(prpStmt);
		stmtList.add(prpStmt2);
		
		executeBatch(stmtList);
		
		
	}
	public static void executeBatch(ArrayList<PreparedStatement> prpStmt) throws SQLException{
		for(PreparedStatement prp:prpStmt){
			ResultSet rs = prp.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			System.out.println("end");
		}
		
	}

}
