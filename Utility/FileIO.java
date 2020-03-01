package Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "./src/TempFiles/temp.txt";
		ArrayList<String> data = new ArrayList<String>();
		data.add("hello");
		data.add("noob");
		WriteFile(data,filePath);
		ReadFile(filePath);
	}
	
	public static ArrayList<String> ReadFile(String filePath){
		String line = null;
		ArrayList<String> queries = new ArrayList<String>();
		try{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				queries.add(line.trim());	
			}
			fr.close();
		
		}catch(Exception e){
			System.err.println("error reading from"+filePath);
		}
		
		return queries;
		
	}
	
	public static void WriteFile(ArrayList<String> queries,String filePath){
		try{
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter bw = new BufferedWriter(fw);
			for(String s : queries){
				bw.write(s);
				bw.newLine();
			}
			bw.close();
		}catch(Exception e){
			System.err.println("error writing to"+filePath);
		}finally{
			
		}
		
	}

}
