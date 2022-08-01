package BibleStatistics.Bible;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class leningrad {

	//turns a Leningrad text file into String with Pesukim numbers () and replaces alternative hebrew fonts with their proper counterparts.
	//(1) אֲשֶׁ֥ר בִּירוּשָׁלִָֽם׃ ‬ ‫ 5  ׃1   וַיָּק֜וּמוּ רָאשֵׁ֣י הָאָב֗וֹת
	public static String standard(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file, "UTF-8");
		String finalB = "";
		String now = "";
		boolean good = true;
	    while (sc.hasNextLine()){
	    	good = true;
	    	now = sc.nextLine();
	    	for(int i = 0; i < now.length(); i++){
	    		if(now.substring(i, i + 1).equals("x")){
	    			good = false;
	    			break;
	    		}
	    	}
	    	if(good){
	    		finalB += " " + now;
	    	}
	    }
	    sc.close();
	    finalB = finalB.replace(specialCharacter(), "");  
	    finalB = replaceTsinor(finalB);
	    finalB = replaceGeresh(finalB); 
	    finalB = replaceSpacers(finalB);
	    finalB = replacePasuk(finalB, 1, true);
		//finalB = finalB.replaceFirst(" ", "");
	    return finalB; 
	}

	//turns a Leningrad text file into String without Pesukim numbers (1 space in between Pesukim) (1) and replaces alternative hebrew fonts with their proper counterparts.
	//(1) אֲשֶׁ֥ר בִּירוּשָׁלִָֽם׃ וַיָּק֜וּמוּ רָאשֵׁ֣י הָאָב֗וֹת
	public static String bibleMaker(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file, "UTF-8");
		String finalB = "";
		String now = "";
		boolean good = true;
	    while (sc.hasNextLine()){
	    	good = true;
	    	now = sc.nextLine();
	    	int len = now.length();
	    	for(int i = 0; i < len; i++){
	    		if(now.substring(i, i + 1).equals("x")){
	    			good = false;
	    			break;
	    		}
	    		if(now.substring(i, i + 1).equals("׃")) {
	    			if(i <= 5) {
	    				now = now.substring(i + 5, len);
	    				len = now.length();
	    			}
	    			if(i > 5) {
	    				now = now.substring(0, i + 1);
	    				len = now.length();
	    			}
	    		}
	    	}
	    	if(good){
	    		finalB += " " + now;
	    	}
	    }
	    sc.close();
	    finalB = finalB.replace(specialCharacter(), "");
	    finalB = replaceTsinor(finalB);
	    finalB = replaceGeresh(finalB);
	    finalB = replaceSpacers(finalB);
	    finalB = replacePasuk(finalB, 1, false);
		finalB = finalB.replaceFirst(" ", "");
	    return finalB;
	}
	
	public static String replaceSpacers(String bible) {
		String x = bible;
		x = x.replace(" פ ", " ");
		x = x.replace(" ס ", " ");
		x = x.replace(" ׆ס", "");
		return x;
	}

	/*public static String addTrop(String bible){
		String x = bible;
		x = x.replace("דַּבֶּרְךָ", "דַּבֶּרְךָ֖");

		return x;
	}*/
	
	public static String replaceTsinor(String bible) {
		String x = bible;
		x = x.replace("וּֽלְיִשְׁמָעֵ֘אל", "וּֽלְיִשְׁמָעֵאל֮");
		x = x.replace("יִשְׂרָאֵ֘ל", "יִשְׂרָאֵל֮");
		x = x.replace("בְּצַלְאֵ֘ל", "בְּצַלְאֵל֮");
		x = x.replace("תוּכַ֘ל", "תוּכַל֮");
		x = x.replace("אָנֹ֘כִי֮", "אָנֹ֮כִי֮");
		x = x.replace("חֲנָ֘נִי֮", "חֲנָ֮נִי֮");
		return x;
	}
	
	public static String replaceGeresh(String bible) {
		String x = bible;
		x = x.replace("פֶּ֝תַח", "פֶּ֜תַח");
		x = x.replace("שֻׁ֝֠בוּ", "שֻׁ֜֠בוּ");
		return x;
	}
	
	public static String replacePasuk(String bible, int num, Boolean header) {
		String x = bible;
		if(num == 1 && header) {
			x = x.replace("19 ׃25  וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה ‬ ‫ 1  ׃26  וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃", "19  ׃25  וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃‬");
		    x = x.replace("וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃", "וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃ "); 
		}
		if(num == 1 && !header) {
			 x = x.replace(" וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה ‬ וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃", " וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃");
		}
		if(num == 2 && header) {
			x = x.replace("וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה", "וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה׃ "); 
		}
		if(num == 2 && !header) {
			x = x.replace(" וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה ‬ וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃", " וַיְהִ֖י אַחֲרֵ֣י הַמַּגֵּפָ֑ה׃ וַיֹּ֤אמֶר יְהוָה֙ אֶל־מֹשֶׁ֔ה וְאֶ֧ל אֶלְעָזָ֛ר בֶּן־אַהֲרֹ֥ן הַכֹּהֵ֖ן לֵאמֹֽר׃");
		}
		return x;
	}
	
	public static String specialCharacter() {
		String special = "א‍"; //special character is isolated on this aleph.
	    return special.substring(1, 2);
	}
}
