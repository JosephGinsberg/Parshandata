package Level_1;

public class otherBibles {
	
	//Removes all the letters from the given input String
	public static String noLetters(String bible){
		String[] nekudot = basicLists.letterList();
		int len = nekudot.length;
		for(int i = 0; i < len; i++) {
			bible = bible.replace(nekudot[i] , "");
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
		String[] nekudot = basicLists.tropList();
		int len = nekudot.length;
		for(int i = 0; i < len; i++) {
			bible = bible.replace(nekudot[i] , "");
		}
		return bible;
	}
	
	//Removes all the other from the given input String
		public static String noOther(String bible){
			String[] nekudot = basicLists.otherList();
			int len = nekudot.length;
			for(int i = 0; i < len; i++) {
				bible = bible.replace(nekudot[i] , "");
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
		bible = noOther(bible);
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
		bible = noOther(bible);
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
		bible = noOther(bible);
		return bible;
	}	
	
	//Keeps only the trop of the given input String
	public static String justTrop(String bible){
		bible = noNekudot(bible);
		bible = noLetters(bible);
		bible = noOther(bible);
		return bible;
	}
	
	public static String choose(String bible, Boolean letter, Boolean nikud, Boolean trop, Boolean other) {
		if(!letter) {
			bible = noLetters(bible);
		}
		if(!nikud) {
			bible = noNekudot(bible);
		}
		if(!trop) {
			bible = noTrop(bible);
		}
		if(!other) {
			bible = noOther(bible);
		}
		return bible;
	}
	
}
