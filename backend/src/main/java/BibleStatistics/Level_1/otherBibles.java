package BibleStatistics.Level_1;

public class otherBibles {
	
	//Removes all the letters from the given input String
	public static String noLetters(String bible){
		String[] letters = basicLists.letterList();
		int len = letters.length;
		for(int i = 0; i < len; i++) {
			bible = bible.replace(letters[i] , "");
		}
		return bible;
	}
	
	//Removes all the nekudot from the given input String
	public static String noNekudot(String bible){
		String[] nekudot = basicLists.nekudotList();
		int len = nekudot.length;
		for(int i = 0; i < len; i++) {
			bible = bible.replace(nekudot[i] , "");
		}
		return bible;
	}
	
	//Removes all the trop from the given input String
	public static String noTrop(String bible){
		String[] trop = basicLists.tropList();
		int len = trop.length;
		for(int i = 0; i < len; i++) {
			bible = bible.replace(trop[i] , "");
		}
		return bible;
	}
	
	//Removes all the other from the given input String
		public static String noOther(String bible, Boolean space, Boolean makaf){
			String[] other = basicLists.otherList();
			int len = other.length;
			for(int i = 0; i < len; i++) {
				if(other[i].equals("־") && makaf){
					bible = bible.replace(other[i], " ");
				}
				else if(other[i].equals("־") && !makaf){
					bible = bible.replace(other[i], "");
				}
				else if(other[i].equals(" ") && !space){
					bible = bible.replace(other[i], "");
				}
				else{
					bible = bible.replace(other[i], "");
				}
			}
			return bible;
		}
	
	//Removes all the spaces and the verse-end signs from the given input String
	public static String noSpaces(String bible){
		bible = bible.replace( " ", "" );
		bible = bible.replace( "	", "" );
		bible = bible.replace( "׃", "" );
		return bible;
	}
	
	/*Replaces all the sofit letters in the given input String with regular letters*/
	public static String noSofit(String bible){
		bible = bible.replace("ם" , "מ");
		bible = bible.replace("ן" , "נ");
		bible = bible.replace("ץ" , "צ");
		bible = bible.replace("ף" , "פ");
		bible = bible.replace("ך" , "כ");
		return bible;
	}
	
	//checks to see if there are any unknown or superflous characters in the given input String by removing everything else
	public static String removeCheck(String bible){
		bible = noTrop(bible);
		bible = noNekudot(bible);
		bible = noLetters(bible);
		bible = noOther(bible, false, false);
		return bible;
		
		//bible = bible.replace("�" , "");
		
		//bible = bible.replace("(" , "");
		
		//bible = bible.replace(")" , "");
		
		//bible = bible.replace("֯" , "");
	}
	
	//Keeps only the letters of the given input String
	public static String justLetters(String bible){
		bible = noTrop(bible);
		bible = noNekudot(bible);
		bible = noOther(bible, false, false);
		return bible;
	}
	
	public static String lettersAndSpaces(String bible){
		int len = bible.length();
		bible = bible.replace(" ׀" , "");
		bible = bible.replace("־" , " ");
		for(int i = 0; i < len; i++) {
			String now = bible.substring(i, i + 1);
			if(!(basicLists.isaletter(now) || now.equals(" ") || now.equals("׃"))){
				bible = bible.replace(now , "");
				len = bible.length();
			}
		}
		
		return bible;
	}
	
	//Keeps only the nekudot of the given input String
	public static String justNekudot(String bible){
		bible = noTrop(bible);
		bible = noLetters(bible);
		bible = noOther(bible, false, false);
		return bible;
	}	
	
	//Keeps only the trop of the given input String
	public static String justTrop(String bible){
		bible = noNekudot(bible);
		bible = noLetters(bible);
		bible = noOther(bible, false, false);
		return bible;
	}
	
	public static String choose(String bible, Boolean[] conditions) {
		if(!conditions[0]) {
			bible = noLetters(bible);
		}
		if(!conditions[1]) {
			bible = noNekudot(bible);
		}
		if(!conditions[2]) {
			bible = noTrop(bible);
		}
		if(!conditions[3]) {
			bible = noOther(bible, conditions[4], conditions[5]);
		}
		return bible;
	}
	
}
