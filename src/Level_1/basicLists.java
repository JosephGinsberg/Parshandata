package Level_1;

public class basicLists {

	public static void main(String[] args){
		String[] test = {"ץ", "ֵ", "֦", "p"};
		for(int i = 0; i < test.length; i++){
			System.out.println(isaletter(test[i]) + " " + isanekudah(test[i]) + " " + isatrop(test[i]));
		}
	}

	public static String[] letterList(){
		String[] letters = {"א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט", "י", "כ", "ל", "מ", "נ", "ס", "ע", "פ", "צ", "ק", "ר", "ש", "ת", "ם", "ן", "ץ", "ף", "ך"};
		return letters;
	}
	
	public static Boolean isaletter(String now) {
		String[] letters = letterList();
		for (String s: letters) {
			if (s.equals(now))
				return true;
		}
		return false;
	}
	
	public static String[] nekudotList(){
		String[] nekudot = {/*sin*/ "ׂ" , /*shin*/ "ׁ" , "ָ" , "ַ" , "ֵ" , "ֶ" , "ֹ" , "ֻ" , "ִ" , "ְ" , "ֳ" , "ֲ" , "ֱ" , /*dagesh*/ "ּ", /*rafeh*/ "ֿ", /*Cholam Chaser for Vav*/ "ֺ"};
		return nekudot;
	}
	
	public static Boolean isanekudah(String now) {
		String[] nekudot = nekudotList();
		for (String s: nekudot) {
			if (s.equals(now))
				return true;
		}
		return false;
	}
	
	
	public static String[] tropList(){
		String[] trop = {"֙" , "֣" , "֮" , "֒" , "֗" , "֤" , "֨" , "֔" , "֕" , "֥" , "֖" , "֭" , "֑" , "֡" , "֩" , "֠" , "֜" , "֞" , "֧" , "֛" , "֚" , "֓" , "֪" , "֟" , "֦" , "ֽ"};
		return trop;
	}
	
	public static Boolean isatrop(String now) {
		String[] trop = tropList();
		for (String s: trop) {
			if (s.equals(now))
				return true;
		}
		return false;
	}
	
	public static String[] otherList(){
		String[] other = {"׃" , "־" , " ׀" , /*dots on top of words*/ "ׄ" , "*" , " "};
		return other;
	}

	//translates the input character to english
	public static String translate(String str){
		String out = "";
		if(str.equals("ֽ")){
			out = "Gaya";
		}
		else if(str.equals("־")){
			out = "Makaf";
		}
		else if(str.equals("׀")){
			out = "Pesik";
		}
		else if(str.equals("ּ")){
			out = "Dagesh";
		}
		else if(str.equals("א")){
			out = "Aleph";
		}
		else if(str.equals("ב")){
			out = "Beis";
		}
		else if(str.equals("ג")){
			out = "Gimmel";
		}
		else if(str.equals("ד")){
			out = "Dalet";
		}
		else if(str.equals("ה")){
			out = "Hei";
		}
		else if(str.equals("ו")){
			out = "Vav";
		}
		else if(str.equals("ז")){
			out = "Zayin";
		}
		else if(str.equals("ח")){
			out = "Ches";
		}
		else if(str.equals("ט")){
			out = "Tes";
		}
		else if(str.equals("י")){
			out = "Yud";
		}
		else if(str.equals("כ")){
			out = "Chaf";
		}
		else if(str.equals("ל")){
			out = "Lamed";
		}
		else if(str.equals("מ")){
			out = "Mem";
		}
		else if(str.equals("נ")){
			out = "Nun";
		}
		else if(str.equals("ס")){
			out = "Samach";
		}
		else if(str.equals("ע")){
			out = "Ayin";
		}
		else if(str.equals("פ")){
			out = "Pei";
		}
		else if(str.equals("צ")){
			out = "Tsadi";
		}
		else if(str.equals("ק")){
			out = "Kuf";
		}
		else if(str.equals("ר")){
			out = "Reish";
		}
		else if(str.equals("ש")){
			out = "shin";
		}
		else if(str.equals("ת")){
			out = "Tav";
		}
		else if(str.equals("ך")){
			out = "Chaf-Sofit";
		}
		else if(str.equals("ם")){
			out = "Mem-Sofit";
		}
		else if(str.equals("ן")){
			out = "Nun-Sofit";
		}
		else if(str.equals("ף")){
			out = "Pei-Sofit";
		}
		else if(str.equals("ץ")){
			out = "Tsadi-Sofit";
		}
		else if(str.equals("ָ")){
			out = "Kamatz";
		}
		else if(str.equals("ַ")){
			out = "Patach";
		}
		else if(str.equals("ֵ")){
			out = "Tsaray";
		}
		else if(str.equals("ֶ")){
			out = "Segol (Nakud)";
		}
		else if(str.equals("ֹ")){
			out = "Cholam";
		}
		else if(str.equals("ֻ")){
			out = "Shuruk";
		}
		else if(str.equals("ִ")){
			out = "Chirik";
		}
		else if(str.equals("ְ")){
			out = "Shva";
		}
		else if(str.equals("ֺ")){
			out = "Cholam Chaser for Vav";
		}
		else if(str.equals("ֳ")){
			out = "Chataf-Kamatz";
		}
		else if(str.equals("ֲ")){
			out = "Chataf-Patach";
		}
		else if(str.equals("ֱ")){
			out = "Chataf-Segol";
		}
		else if(str.equals("׃")){
			out = "sof-pasuk";
		}
		else if(str.equals("֨")){
			out = "kadmah";
		}
		else if(str.equals("֣")){
			out = "munach";
		}
		else if(str.equals("֮")){
			out = "zarka";
		}
		else if(str.equals("֒")){
			out = "segol";
		}
		else if(str.equals("֗")){
			out = "revie";
		}
		else if(str.equals("֤")){
			out = "mahpach";
		}
		else if(str.equals("֙")){
			out = "pashtah";
		}
		else if(str.equals("֔")){
			out = "zakaif";
		}
		else if(str.equals("֕")){
			out = "zakaif-gadol";
		}
		else if(str.equals("֥")){
			out = "mercha";
		}
		else if(str.equals("֖")){
			out = "tipcha";
		}
		else if(str.equals("֑")){
			out = "esnachta";
		}
		else if(str.equals("֡")){
			out = "pazair";
		}
		else if(str.equals("֩")){
			out = "telisha-ketanah";
		}
		else if(str.equals("֠")){
			out = "telisha-gedolah";
		}
		else if(str.equals("֜")){
			out = "azlah-geiresh";
		}
		else if(str.equals("֞")){
			out = "gershayim";
		}
		else if(str.equals("֧")){
			out = "dargah";
		}
		else if(str.equals("֛")){
			out = "tevier";
		}
		else if(str.equals("֚")){
			out = "yesiv";
		}
		else if(str.equals("֓")){
			out = "shalsheles";
		}
		else if(str.equals("֪")){
			out = "yareach-ben-yomo";
		}
		else if(str.equals("֟")){
			out = "karnei-pharah";
		}
		else if(str.equals("֦")){
			out = "mercha-kefula";
		}
		return out;
	}
}