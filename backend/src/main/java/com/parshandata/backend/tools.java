package com.parshandata.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class tools {

	public static String[] ArraylistToArray(ArrayList<String> input){
		String[] result = new String[input.size()];
		int i = 0;
		for(String j : input){
			result[i] = j;
			i++;
		}
		return result;
	}

	public static String FileToString(String filename) throws FileNotFoundException {
		File file = new File(filename.replaceAll("%20", " "));
		Scanner sc = new Scanner(file, "UTF-8");
		String contents = "";
	    while (sc.hasNextLine()){
			contents += sc.nextLine();
		}
		sc.close();
		return contents;
	}

	public static void StringToFile(String data, String filename) throws IOException{
		FileOutputStream file = new FileOutputStream(filename);
		file.write(data.getBytes("UTF-8"));
		file.close();
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
		Scanner sc = new Scanner(file, "UTF-8");
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