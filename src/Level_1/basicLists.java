package Level_1;

import java.util.ArrayList;

public class basicLists {
	private static String[][] chars = {
		{"א", "Aleph", "Hebrew", "letter"},
		{"ב", "Beis", "Hebrew", "letter"},
		{"ג", "Gimmel", "Hebrew", "letter"},
		{"ד", "Dalet", "Hebrew", "letter"},
		{"ה", "Hei", "Hebrew", "letter"},
		{"ו", "Vav", "Hebrew", "letter"},
		{"ז", "Zayin", "Hebrew", "letter"},
		{"ח", "Ches", "Hebrew", "letter"},
		{"ט", "Tes", "Hebrew", "letter"},
		{"י", "Yud", "Hebrew", "letter"},
		{"כ", "Chaf", "Hebrew", "letter"},
		{"ל", "Lamed", "Hebrew", "letter"},
		{"מ", "Mem", "Hebrew", "letter"},
		{"נ", "Nun", "Hebrew", "letter"},
		{"ס", "Samach", "Hebrew", "letter"},
		{"ע", "Ayin", "Hebrew", "letter"},
		{"פ", "Pei", "Hebrew", "letter"},
		{"צ", "Tsadi", "Hebrew", "letter"},
		{"ק", "Kuf", "Hebrew", "letter"},
		{"ר", "Reish", "Hebrew", "letter"},
		{"ש", "shin", "Hebrew", "letter"},
		{"ת", "Tav", "Hebrew", "letter"},
		{"ם", "Chaf-Sofit", "Hebrew", "letter"},
		{"ן", "Mem-Sofit", "Hebrew", "letter"},
		{"ץ", "Nun-Sofit", "Hebrew", "letter"},
		{"ף", "Pei-Sofit", "Hebrew", "letter"},
		{"ך", "Tsadi-Sofit", "Hebrew", "letter"},
		{"ָ", "Kamatz", "Hebrew", "nekudah"},
		{"ַ", "Patach", "Hebrew", "nekudah"},
		{"ֵ", "Tsaray", "Hebrew", "nekudah"},
		{"ֶ", "Segol (Nakud)", "Hebrew", "nekudah"},
		{"ֹ", "Cholam", "Hebrew", "nekudah"},
		{"ֻ", "Shuruk", "Hebrew", "nekudah"},
		{"ִ", "Chirik", "Hebrew", "nekudah"},
		{"ְ", "Shva", "Hebrew", "nekudah"},
		{"ֺ", "Cholam Chaser for Vav", "Hebrew", "nekudah"},
		{"ֳ", "Chataf-Kamatz", "Hebrew", "nekudah"},
		{"ֲ", "Chataf-Patach", "Hebrew", "nekudah"},
		{"ֱ", "Chataf-Segol", "Hebrew", "nekudah"},
		{"ּ", "Dagesh", "Hebrew", "other"},
		{"ֿ", "Rafeh", "Hebrew", "other"},
		{"ׂ", "sin-dot", "Hebrew", "other"},
		{"ׁ", "shin-dot", "Hebrew", "other"},
		{"֨" , "kadmah", "Hebrew", "trop"},
		{"֣" , "munach", "Hebrew", "trop"},
		{"֮" , "zarka", "Hebrew", "trop"},
		{"֒" , "segol", "Hebrew", "trop"},
		{"֗" , "revie", "Hebrew", "trop"},
		{"֤" , "mahpach", "Hebrew", "trop"},
		{"֙" , "pashtah", "Hebrew", "trop"},
		{"֔" , "zakaif", "Hebrew", "trop"},
		{"֕" , "zakaif-Gadol", "Hebrew", "trop"},
		{"֥" , "mercha", "Hebrew", "trop"},
		{"֖" , "tipcha", "Hebrew", "trop"},
		{"֑" , "esnachta", "Hebrew", "trop"},
		{"֡" , "pazair", "Hebrew", "trop"},
		{"֩" , "telisha-ketanah", "Hebrew", "trop"},
		{"֠" , "telisha-gedolah", "Hebrew", "trop"},
		{"֜" , "azlah-geiresh", "Hebrew", "trop"},
		{"֞" , "gershayim", "Hebrew", "trop"},
		{"֧" , "dargah", "Hebrew", "trop"},
		{"֛" , "tevier", "Hebrew", "trop"},
		{"֚" , "yesiv", "Hebrew", "trop"},
		{"֓" , "shalsheles", "Hebrew", "trop"},
		{"֪" , "yareach-ben-yomo", "Hebrew", "trop"},
		{"֟" , "karnei-pharah", "Hebrew", "trop"},
		{"֦" , "mercha-kefula", "Hebrew", "trop"},
		{"׃", "sof-pasuk", "Hebrew", "trop"},
		{"֭" , "dechi", "Hebrew", "trop"},
		{"֘", "tsinor", "Hebrew", "trop"},
		{"֬", "elui", "Hebrew", "trop"},
		{"֫", "oleh", "Hebrew", "trop"},
		{"֝", "mugrash", "Hebrew", "trop"},
		{"׀", "Pesik", "Hebrew", "trop"},
		{"־", "Makaf", "Hebrew", "trop"},
		{"ֽ" , "gaya", "Hebrew", "other"},
		{"ׄ", "top dots", "Hebrew", "other"},
		{"ׅ", "bottom dots", "Hebrew", "other"},
		{"*", "Keri ukesiv marker", "Hebrew", "other"},
		{" ", "space", "Hebrew", "other"}
	};

	public static void main(String[] args){
		String[] testChars = {"ץ", "ֵ", "֦", "p"};
		for(int i = 0; i < testChars.length; i++){
			System.out.println(
			 "isaLetter: "	+ isaletter(testChars[i]) +
			" isaNekudah: "	+ isanekudah(testChars[i]) +
			" isaTrop: "	+ isatrop(testChars[i]));
		}
	}

	// Get String[] of chars in a class, i.e. letter, nekudah, trop
	public static String[] charsByGroup(String group){
		ArrayList<String> matchedChars = new ArrayList<String>();
		for (String[] c: chars) {
			if(c[3].equals(group)){
				matchedChars.add(c[0]);
			}
		}
		String[] finalChars = tools.ArraylistToArray(matchedChars);
		return finalChars;
	}

	// Return Boolean whether a char is w/in a specified class
	public static Boolean isCharInGroup(String term, String group){
		String[] charsInGroup = charsByGroup(group);
		for (String s: charsInGroup) {
			if (s.equals(term))
				return true;
		}
		return false;
	}

	// Search if a char in String exists in a specified group
	public static Boolean contains(String searched, String group){
		Boolean has = false;
		int len = searched.length();
		for(int i = 0; i < len; i++){
			if(isCharInGroup(searched.substring(i, i + 1), group)){
				has = true;
				break;
			}
		}
		return has;
	}

	public static String[] letterList(){
		return charsByGroup("letter");
	}

	public static Boolean isaletter(String term) {
		return isCharInGroup(term, "letter");
	}

	public static String[] nekudotList(){
		return charsByGroup("nekudah");
	}

	public static Boolean isanekudah(String term) {
		return isCharInGroup(term, "nekudah");
	}

	public static String[] tropList(){
		return charsByGroup("trop");
	}

	public static Boolean isatrop(String term) {
		return isCharInGroup(term, "trop");
	}

	public static String[] otherList(){
		return charsByGroup("other");
	}

	//translates the input character to english
	public static String translate(String str){
		for (String[] c : chars) {
			if(str.equals(c[0])){
				return c[1];
			}
		}
		return "";
	}
}