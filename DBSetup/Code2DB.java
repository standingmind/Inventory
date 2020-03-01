package DBSetup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import DBUtils.DBConnection;
import ExcelImporter.ImportCode;

public class Code2DB {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		int count = 0 ;
		Connection conn = null;
		PreparedStatement prepareStmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			String query = "Insert into code(code_number,codetype_id,code_date) values (?,?,?)";
			prepareStmt = conn.prepareStatement(query);
			
			Calendar calendar = Calendar.getInstance();
			Timestamp t = new Timestamp(calendar.getTimeInMillis());
			
			Set<String> set = (LinkedHashSet<String>)ImportCode.importCode();
			
			//insert with loop
			
			for(String str : set){
				prepareStmt.setString(1, str);
				prepareStmt.setInt(2, 3);
				prepareStmt.setTimestamp(3, t);
				prepareStmt.execute();
				count++;
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				System.out.println(count+" records has been added successfully");
				prepareStmt.close();
				conn.close();
				
			}
			//insert query
		
		
		
		
	}

}
