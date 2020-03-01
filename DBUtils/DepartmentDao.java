package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HolderClass.DepartmentHolder;

public class DepartmentDao {
	
	
	public static String[] getNames(){
		String[] s= new String[0];
		int size = 0;
		int count = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query1 = "Select count(department_name) from department";
		String query2 = "Select department_name from department";
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query1);
			
			if(rs.next()){
				size = rs.getInt(1);
			}
			
			if(size > 0 ){
				s = new String[size];
				rs = stmt.executeQuery(query2);
				while(rs.next()){
					s[count++] = rs.getString(1);
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
		
		return s;
		
	}
	
	public static int getNameById(String name){
		int ans = -1 ;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement prpStmt = null;
		String query = "Select department_id from department where department_name=?";
		try{
			conn = DBConnection.getMySQLConnection();
			prpStmt = conn.prepareStatement(query);
			prpStmt.setString(1,name);
			rs = prpStmt.executeQuery();
			if(rs.next()){
				ans = rs.getInt(1);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(prpStmt);
			DBConnection.closeConnection(conn);
		}
		System.out.println(ans);
		return ans;
		
	}
	
	
	public static ArrayList<DepartmentHolder> getAll(){
		ArrayList<DepartmentHolder> deptList = new ArrayList<DepartmentHolder>();
		
		Connection conn =null;
		ResultSet rs = null;
		String query =  "Select * from department";
		Statement stmt = null;
		try {
			conn = DBConnection.getMySQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				DepartmentHolder dptHolder = new DepartmentHolder();
				dptHolder.setDepartmentId(rs.getInt("department_id"));
				dptHolder.setDepartmentName(rs.getString("department_name"));
				dptHolder.setDepartmentRemark(rs.getString("department_remark"));
				deptList.add(dptHolder);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(conn);
		}
		
		return deptList;
		
	}
	
	public static void main(String[] args){
		String[] s = getNames();
		for(String str:s){
			System.out.println(str);
		}
		
		ArrayList<DepartmentHolder> dptList = getAll();
		for(DepartmentHolder h:dptList){
			System.out.println(h.toString());
		}
	}
	
}
