package DBSetup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import DBUtils.DBConnection;
import ExcelHolderClass.ExcelProductHolder;
import ExcelImporter.ImportProduct;

public class Product2DB {
	public static void main(String[] args) throws Exception {
		String name ="";
		String model = "";
		String unit = "";
		String code = "";
		String vendor = "";
		String person_in_charge = "";
		String price = "";
		String date ="";
		//get products-
		Set<ExcelProductHolder> productHolder = ImportProduct.importProduct();
		
		
		
		Connection conn = DBConnection.getMySQLConnection();
		Statement stmt = conn.createStatement();
		int recordNo = 0;
		for(ExcelProductHolder holder:productHolder){
			name = holder.getName();
			model = holder.getModel();
			unit = holder.getUnit();
			code = holder.getCode();
			
			Calendar calendar = Calendar.getInstance();
			Timestamp t = new Timestamp(calendar.getTimeInMillis());
			date = t.toString();
			
			vendor = holder.getVendor();
			person_in_charge= holder.getPersonInCharge();
			price = holder.getPrice();
			
			String query = String.format("Insert into product(product_name,product_specification,product_unit,code_id,product_date,vendor_id,product_person_in_charge,product_price,product_class_id) "
					+ "values('%s','%s','%s',(Select code_id from code where code_number='%s'),'%s',(Select vendor_id from vendor where vendor_name='%s'),'%s','%s',"
					+ "'%d')",name,model,unit,code,date,vendor,person_in_charge,price,1);
			System.out.println(query);
			stmt.executeUpdate(query);
			recordNo++;
			
		}
		System.out.println("Inserted "+recordNo+" records successfully");
		
		
		
		// TODO Auto-generated method stub
		
	}

}
