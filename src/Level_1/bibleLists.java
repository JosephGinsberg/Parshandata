package Level_1;

import java.util.ArrayList;

public class bibleLists {
	
	
	//Outputs a String array of all the words in the input String. The array will contain pure words.
	public static String[] words(String bible){
		ArrayList<String> words = new ArrayList<String>();
		int startPos = 1;
		int len = bible.length();
		for(int i = 1; i < len; i++){
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
	public static String[] tropWords(String bible, boolean pesik){
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
	
	//outputs an array containing each letter with all its extra parts (dagesh, nekudot, trop, makaf, pesik, etc.) seperately.
	public static String[] separateLetters(String str){
		ArrayList<String> sep = new ArrayList<String>();
		str = str.replace(" ", "");
		int startPos = 0;
		int len = str.length();
		for(int i = 1; i < len; i++) {
			//started i at 1, because if it started at 0, it would add "" to the arrayList as the first item as soon as it encountered the first letter, since there is nothing ("") before it.
			String now = str.substring(i, i + 1);
			if(basicLists.isaletter(now)){
				sep.add(str.substring(startPos, i));
				startPos = i;
			}
		}
		return tools.ArraylistToArray(sep);
	}
	
}