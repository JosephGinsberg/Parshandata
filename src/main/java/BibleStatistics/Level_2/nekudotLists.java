package BibleStatistics.Level_2;

import java.util.ArrayList;

import BibleStatistics.Level_1.basicLists;
import BibleStatistics.Level_1.tools;

public class nekudotLists {
	//should be updated to match tropLists
	//creates a list of all the nekudos in the input String
	public static String[] nekudotList(String bible){
		ArrayList<String> nekudot = new ArrayList<String>();
		for(int i = 0; i < bible.length(); i++){
			String now = bible.substring(i , i + 1);
    		if(now.equals("ֹ")){
    			if(i < bible.length() - 1) {
    				if(bible.substring(i , i + 2).equals("וֹ")){
    	    			nekudot.add("cholam-gadol");
    	    		}
    				else {
    					nekudot.add("cholam-katan");
    				}
    			}
    			else {
    				nekudot.add("cholam-katan");
    			}
    		}
    		else if(now.equals("ִ")){
    			if(i < bible.length() - 1) {
    				if(bible.substring(i , i + 2).equals("ִי")){
    	    			nekudot.add("chirik-gadol");
    	    		}
    				else {
        				nekudot.add("chirik-katan");
        			}
    			}
    			else {
    				nekudot.add("chirik-katan");
    			}
    		}
			else{
				nekudot.add(basicLists.translate(now));
			}
			if(i < bible.length() - 1) {
	    		if(bible.substring(i , i + 2).equals("וּ")){
	    			nekudot.add("melupim (shuruk-gadol)");
	    		}
			}
		}
		String[] nakud = tools.ArraylistToArray(nekudot);
		return nakud;
	}
	
	//returns the total number of syllables in the given input string
	public static int SyllablesNum(String input){
		int count = 0;
		int len = input.length();
		for(int i = 0; i < len; i++){
			if(input.substring(i, i + 1).equals("ָ")){
				count++;
			}
			else if(input.substring(i , i + 1).equals("ַ")){
				count++;
    		}
    		else if(input.substring(i , i + 1).equals("ֵ")){
    			count++;
    		}
    		else if(input.substring(i , i + 1).equals("ֶ")){
    			count++;
    		}
    		else if(input.substring(i , i + 1).equals("ֹ")){
    			count++;
    		}
    		else if(input.substring(i , i + 1).equals("ֻ")){
    			count++;
    		}
    		else if(input.substring(i , i + 1).equals("ִ")){
    			count++;
    		}
    		if(i != len - 1){
    			if(input.substring(i , i + 2).equals("וּ")){
	    			count++;
	    		}
    		}
		}
		return count;
	}
		
}
