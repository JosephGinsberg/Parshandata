package Level_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tools {

	public static String FileToString(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String output = "";
	    while (sc.hasNextLine()){
			output += sc.nextLine();
		}
		sc.close();
		return output;
	}

	public static void printArray(String[] ar) {
		int len = ar.length;
		for(int i = 0; i < len; i++) {
			System.out.println(ar[i] + " ");
			System.out.println();
		}
	}
	
	public static void printArrayWord(String[] ar, String word) {
		int len = ar.length;
		for(int i = 0; i < len; i++) {
			if(ar[i].equals(word)) {
				System.out.println(ar[i]);
			}
		}
	}
	
	public static void printArrayLimit(String[] ar, int limit) {
		int len = limit;
		for(int i = 0; i <= len; i++) {
			System.out.println(ar[i]);
			System.out.println();
		}
	}
	
	//returns an array of the two parts of the input string divided at the divide point.
	public static String[] clauseDivide(String clause, int dividePoint){
		String[] clauses = {clause.substring(0, dividePoint), clause.substring(dividePoint)};	
		return clauses;
	}
		
	//puts an input character between each character of the input string
	public static String spacer(String phrase, String cha){
		String answer = "";
		int num = phrase.length();
		for(int i = 0; i < num; i++){
			answer += phrase.substring(i, i + 1) + cha;
		}
		return answer;
	}
	
	public static void fileCounter(File file) throws FileNotFoundException {
		int count = 0;
		int count2 = 0;
		String now = "";
		Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()){
	    	now = sc.nextLine();
	    	if(now.contains(":")) {
	    		System.out.println(count + "," + count2);
	    		count++;
	    		count2 = 0;
	    	}
	    	else if(!now.equals("")) {
	    		count2++;
	    	}
	    }
	    sc.close();
	}
	
}
