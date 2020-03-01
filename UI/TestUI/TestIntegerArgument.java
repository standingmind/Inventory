package UI.TestUI;

import java.util.Scanner;

public class TestIntegerArgument {
	public static void main(String[] args){
		  Scanner in = new Scanner(System.in);
	        int N = in.nextInt();
	        String s = String.valueOf(N);
	        String ans = s.substring(s.length()-1, s.length());
	        // Write an action using System.out.println()
	        // To debug: System.err.println("Debug messages...");

	        System.out.println(ans);
		
	}
	
	
}
