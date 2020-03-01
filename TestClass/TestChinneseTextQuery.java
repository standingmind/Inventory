package TestClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DBUtils.DBConnection;
import ExcelHolderClass.ExcelProductHolder;
import ExcelImporter.ImportProduct;

public class TestChinneseTextQuery {
	public static void main(String[] args) throws Exception{
		Connection conn = DBConnection.getMySQLConnection();
		PreparedStatement stmt = conn.prepareStatement("Select manufacturer_id from manufacturer where manufacturer_name=?");
		ArrayList<ExcelProductHolder> holder = ImportProduct.importProduct();
		
		for(ExcelProductHolder h: holder){
			String manufacturerName = h.getManufacturer();
			stmt.setString(1, manufacturerName);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			System.out.print(manufacturerName +":");
			System.out.println(rs.getInt(1));
		}
	}
}
