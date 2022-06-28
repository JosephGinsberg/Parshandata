package Level_1;

import java.util.ArrayList;

public class basicLists {
	private static String[][] chars = {
		{"א", "aleph", "Hebrew", "letter"},
		{"ב", "beis", "Hebrew", "letter"},
		{"ג", "gimmel", "Hebrew", "letter"},
		{"ד", "dalet", "Hebrew", "letter"},
		{"ה", "hei", "Hebrew", "letter"},
		{"ו", "vav", "Hebrew", "letter"},
		{"ז", "zayin", "Hebrew", "letter"},
		{"ח", "ches", "Hebrew", "letter"},
		{"ט", "tes", "Hebrew", "letter"},
		{"י", "yud", "Hebrew", "letter"},
		{"כ", "chaf", "Hebrew", "letter"},
		{"ל", "lamed", "Hebrew", "letter"},
		{"מ", "mem", "Hebrew", "letter"},
		{"נ", "nun", "Hebrew", "letter"},
		{"ס", "samach", "Hebrew", "letter"},
		{"ע", "ayin", "Hebrew", "letter"},
		{"פ", "pei", "Hebrew", "letter"},
		{"צ", "tsadi", "Hebrew", "letter"},
		{"ק", "kuf", "Hebrew", "letter"},
		{"ר", "reish", "Hebrew", "letter"},
		{"ש", "shin", "Hebrew", "letter"},
		{"ת", "tav", "Hebrew", "letter"},
		{"ם", "chaf-sofit", "Hebrew", "letter"},
		{"ן", "mem-sofit", "Hebrew", "letter"},
		{"ץ", "nun-sofit", "Hebrew", "letter"},
		{"ף", "pei-sofit", "Hebrew", "letter"},
		{"ך", "tsadi-sofit", "Hebrew", "letter"},
		{"ָ", "kamatz", "Hebrew", "nekudah"},
		{"ַ", "patach", "Hebrew", "nekudah"},
		{"ֵ", "tsaray", "Hebrew", "nekudah"},
		{"ֶ", "segol", "Hebrew", "nekudah"},
		{"ֹ", "cholam", "Hebrew", "nekudah"},
		{"ֻ", "shuruk", "Hebrew", "nekudah"},
		{"ִ", "chirik", "Hebrew", "nekudah"},
		{"ְ", "shva", "Hebrew", "nekudah"},
		{"ֺ", "cholam chaser for vav", "Hebrew", "nekudah"},
		{"ֳ", "chataf-kamatz", "Hebrew", "nekudah"},
		{"ֲ", "chataf-patach", "Hebrew", "nekudah"},
		{"ֱ", "chataf-segol", "Hebrew", "nekudah"},
		{"ּ", "dagesh", "Hebrew", "other"},
		{"ֿ", "rafeh", "Hebrew", "other"},
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
		{"֕" , "zakaif-gadol", "Hebrew", "trop"},
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
		{"׀", "pesik", "Hebrew", "trop"},
		{"־", "makaf", "Hebrew", "trop"},
		{"ֽ" , "gaya", "Hebrew", "other"},
		{"ׄ", "top dots", "Hebrew", "other"},
		{"ׅ", "bottom dots", "Hebrew", "other"},
		{"*", "keri ukesiv marker", "Hebrew", "other"},
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

	// Search if any char in String exists in a specified group
	public static Boolean contains(String input, String group){
		String[] groupChars = charsByGroup(group);
		for (String s : groupChars) {
			if(input.contains(s)){
				return true;
			}
		}
		return false;
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
}