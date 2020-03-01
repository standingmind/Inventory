package TestClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd hh:MM:ss");
		String s= sdf.format(today);
		System.out.println(s);
	}

}
