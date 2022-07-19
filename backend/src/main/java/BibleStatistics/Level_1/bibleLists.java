package BibleStatistics.Level_1;

import java.util.ArrayList;

public class bibleLists {
	
	
	//Outputs a String array of all the words in the input String. The array will contain pure words.
	public static String[] words(String bible){
		ArrayList<String> words = new ArrayList<String>();
		int startPos = 0;
		int len = bible.length();
		for(int i = 0; i < len; i++){
			if(bible.substring(i, i + 1).equals(" ") || bible.substring(i, i + 1).equals("־")){
				if(!bible.substring(startPos, i).equals("׀")) {
					words.add(bible.substring(startPos, i));
				}
				startPos = i + 1;
			}
			else if(bible.substring(i, i + 1).equals("׃")) {
				words.add(bible.substring(startPos, i));
				i += 1;
				startPos = i + 1;
			}
		}
		return tools.ArraylistToArray(words);
	}
	
	/*Outputs a String array of all the accentual words in the input string, one accent per word. In this instance, words connected by a makaf (־) will be considered one word, and words at the end of verses will have the verse-end symbol (׃) appended to it, so as to distinguish the sof-pasuk (ֽ ) from a gayah (same).
	If pesik == true, pesiks (׀), which are surrounded on both sides by a space, and would therefore be theoretically counted as their own word, will be appended to the end of the previous trop-word (important for Legarmeh). If pesik == false, pesiks will not be included at all.*/ 
	public static String[] tropWords2(String bible, boolean pesik){
		ArrayList<String> words = new ArrayList<String>();
		int startPos = 1;
		int len = bible.length();
		for(int i = 1; i < len; i++){
			if(pesik) {
				if(bible.substring(i, i + 1).equals(" ")){
					if(!bible.substring(startPos, i).equals("׀")) {
						words.add(bible.substring(startPos, i));
					}
					else {
						words.set(words.size() - 1, words.get(words.size() - 1) + "׀");
					}
					startPos = i + 1;
				}
			}
			else {
				if(bible.substring(i, i + 1).equals(" ")){
					if(!bible.substring(startPos, i).equals("׀")) {
						words.add(bible.substring(startPos, i));
					}
					startPos = i + 1;
				}
			}
			if(bible.substring(i, i + 1).equals("׃")) {
				words.add(bible.substring(startPos, i + 1));
				i += 1;
				startPos = i + 1;
			}
		}
		return tools.ArraylistToArray(words);
	}

	public static String[] tropWords(String bible){
		ArrayList<String> words = new ArrayList<String>();
		int startPos = 0;
		int len = bible.length();
		for(int i = 0; i < len; i++){
			String now = bible.substring(i, i + 1);
			if(now.equals(" ")){
				if(!bible.substring(startPos, i).equals("׀")) {
					words.add(bible.substring(startPos, i));
				}
				else {
					words.set(words.size() - 1, words.get(words.size() - 1) + "׀");
				}
				startPos = i + 1;
			}
			else if(now.equals("׃")) {
				words.add(bible.substring(startPos, i + 1));
				i += 1;
				startPos = i + 1;
			}
			/*else if(now.equals("־")){
				words.add(bible.substring(startPos, i + 1));
				startPos = i + 1;
			}*/
		}
		return tools.ArraylistToArray(words);
	}
	

	/*Outputs a String array containing all the Pesukim in the given input String*/
	public static String[] pasukim(String bible){
		ArrayList<String> pasuk = new ArrayList<String>();
		int StartPos = 0;
		int len = bible.length();
		for(int i = 0; i < len; i++){
			if(bible.substring(i, i + 1).equals("׃")){
				pasuk.add(bible.substring(StartPos, i + 1));
				StartPos = i + 1;
			}
		}
		return tools.ArraylistToArray(pasuk);
	}

	public static String[] numpasukim(String bible){
		ArrayList<String> pasuk = new ArrayList<String>();
		int StartPos = 0;
		int len = bible.length();
		for(int i = 0; i < len; i++){
			if(bible.substring(i, i + 1).equals("׃") && !bible.substring(i - 1, i).equals(" ") && !bible.substring(i - 3, i - 2).equals("1")){
				pasuk.add(bible.substring(StartPos, i + 1));
				StartPos = i + 1;
			}
		}
		return tools.ArraylistToArray(pasuk);
	}

	public static String[][] numseperator(String[] pas, String name){
		int len = pas.length;
		String[][] result = new String[len][4];
		for(int i = 0; i < len; i++){
			String thispas = pas[i];
			int thislen = thispas.length();
			int pos = thispas.indexOf("׃");
			pos = pos - 6;
			//System.out.println(name + " " + pos + " " + thislen);
			result[i][0] = thispas.substring(pos + 11, thislen);
			result[i][1] = name;
			String[] place = thispas.substring(pos + 2, pos + 11).replace(" ", "").split("׃");
			result[i][2] = place[1];
			result[i][3] = place[0];
			//System.out.println(name + " " + place[1] + " " + place[0]);
		}
		return result;
	}
	
	//outputs an array containing each letter with all its extra parts (dagesh, nekudot, trop, makaf, pesik, etc.) seperately.
	public static String[] separateLetters(String str){
		ArrayList<String> sep = new ArrayList<String>();
		str = str.replace(" ", "");
		int startPos = 0;
		int len = str.length();
		for(int i = 1; i < len; i++) {
			//started i at 1, because if it started at 0, it would add "" to the arrayList as the first item as soon as it encountered the first letter, since there is nothing ("") before it.
			//update?
			String now = str.substring(i, i + 1);
			if(basicLists.isaletter(now) /*shin and sin dots*/ && !now.equals("ׂ") && !now.equals("ׁ")){
				sep.add(str.substring(startPos, i));
				startPos = i;
			}
		}
		return tools.ArraylistToArray(sep);
	}
	
}