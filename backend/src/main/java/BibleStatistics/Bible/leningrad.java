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
		finalB = addTrop(finalB);
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
		finalB = addTrop(finalB);
		finalB = finalB.replaceFirst(" ", "");
	    return finalB;
	}
	
	//removes charachters that indicate textual breaks.
	public static String replaceSpacers(String bible) {
		String x = bible;
		x = x.replace(" פ ", " ");
		x = x.replace(" ס ", " ");
		x = x.replace(" ׆ס", "");
		return x;
	}

	//Adds the correct Trop to words which have none.
	public static String addTrop(String bible){
		String x = bible;
		x = x.replace("מֵאָ֥ז דַּבֶּרְךָ אֶל־עַבְדֶּ֑ךָ", "מֵאָ֥ז דַּבֶּרְךָ֖ אֶל־עַבְדֶּ֑ךָ");
		x = x.replace("יְהוָ֜ה אֶל־מֹשֶׁה נְטֵ֤ה", "יְהֹוָ֜ה אֶל־מֹשֶׁ֗ה נְטֵ֤ה");
		x = x.replace("יַקְרִיבֶ֑נּוּ אֶל־פֶּתַח אֹ֤הֶל", "יַקְרִיבֶ֑נּוּ אֶל־פֶּ֜תַח אֹ֤הֶל");
		x = x.replace("גַּמְלִיאֵ֖ל בֶּן־פְּדָה צֽוּר׃", "גַּמְלִיאֵ֖ל בֶּן־פְּדָה־צֽוּר׃");
		x = x.replace("שְׁלֻֽמִיאֵ֖ל בֶּן־צוּרִֽי שַׁדָּֽי׃", "שְׁלֻֽמִיאֵ֖ל בֶּן־צוּרִֽי־שַׁדָּֽי׃");
		x = x.replace("וַיִּֽחַר אַ֧ף", "וַיִּֽחַר־אַ֧ף");
		x = x.replace("יְהוֹשֻׁ֤עַ בִּן נוּן֙", "יְהוֹשֻׁ֤עַ בִּן־נוּן֙");
		x = x.replace("מֹשֶׁה֙ וּבְנֵֽי יִשְׂרָאֵ֔ל", "מֹשֶׁה֙ וּבְנֵ֣י יִשְׂרָאֵ֔ל");
		x = x.replace("וַיַּֽאֲכִֽלְךָ֤ אֶת הַמָּן֙", "וַיַּאֲכִֽלְךָ֤ אֶת־הַמָּן֙");
		x = x.replace("כִּֽי־תַשֶּׁ֥ה בְרֵֽעֲךָ מַשַּׁ֣את", "כִּֽי־תַשֶּׁ֥ה בְרֵֽעֲךָ֖ מַשַּׁ֣את");
		x = x.replace("אַף־שָׁמָ֖יו יַֽעַרְפוּ טָֽל׃", "אַף־שָׁמָ֖יו יַ֥עַרְפוּ טָֽל׃");
		x = x.replace("מִדְיָ֜ן עָשֽׂוּ לָהֶ֣ם", "מִדְיָ֜ן עָשׂ֥וּ לָהֶ֣ם");
		x = x.replace("וּזְכַרְתֶּ֕ם כִּֽי־עַצְמֵכֶם וּבְשַׂרְכֶ֖ם", "וּזְכַרְתֶּ֕ם כִּֽי־עַצְמְכֶ֥ם וּבְשַׂרְכֶ֖ם");
		x = x.replace("אֶל־הָ֣אֹרֵ֔ב אֲשֶׁר שָׂ֖מוּ", "אֶל־הָ֣אֹרֵ֔ב אֲשֶׁר־שָׂ֖מוּ");
		x = x.replace("פְּלִשְׁתִּ֔ים וְגַם־נִבְאַשׁ יִשְׂרָאֵ֖ל", "פְּלִשְׁתִּ֔ים וְגַם־נִבְאַ֥שׁ יִשְׂרָאֵ֖ל");
		x = x.replace("מֵאֹ֣יְבַ֔י וְלֹֽא טָעַ֥ם", "מֵאֹ֣יְבַ֔י וְלֹא־טָעַ֥ם");
		x = x.replace("בְּחָֽרְפָ֤ם בַּפְּלִשְׁתִּים נֶאֶסְפוּ־שָׁ֣ם", "בְּחָרְפָ֤ם בַּפְּלִשְׁתִּים֙ נֶאֶסְפוּ־שָׁ֣ם");
		x = x.replace("לְךָ֣ שֶֽׁבַע שָׁנִ֣ים", "לְךָ֣ שֶׁבַע־שָׁנִ֣ים");
		x = x.replace("תִּֽדְבַּק־בְּךָ֔ וּֽבְזַרְעֲךָ לְעוֹלָ֑ם", "תִּֽדְבַּק־בְּךָ֔ וּֽבְזַרְעֲךָ֖ לְעוֹלָ֑ם");
		x = x.replace("אֶת *חריהם **צוֹאָתָ֗ם", "אֶת־*חריהם **צוֹאָתָ֗ם");
		x = x.replace("וָֽאֲצַוֶּה֙ אֶת בָּר֔וּךְ", "וָאֲצַוֶּה֙ אֶת־בָּר֔וּךְ");
		x = x.replace("נַפְשָׁ֑ם וּבְיַד חֵ֚יל", "נַפְשָׁ֑ם וּבְיַ֗ד חֵ֚יל");
		x = x.replace("רְבָעֶ֑יהָ וְהַגְּבוּל סָבִ֨יב", "רְבָעֶ֑יהָ וְהַגְּבוּל֩ סָבִ֨יב");
		x = x.replace("ל֗וֹ אֶת הַשַּׁ֙עַר֙", "ל֗וֹ אֶת־הַשַּׁ֙עַר֙");
		x = x.replace("וְדִבַּרְתִּ֖י עַל לִבָּֽהּ׃", "וְדִבַּרְתִּ֖י עַל־לִבָּֽהּ׃");
		x = x.replace("וְנָק֥וּמָה עָלֶיהָ לַמִּלְחָמָֽה׃", "וְנָק֥וּמָה עָלֶ֖יהָ לַמִּלְחָמָֽה׃");
		x = x.replace("כִּכְפִ֖יר בְּעֶדְרֵי צֹ֑אן", "כִּכְפִ֖יר בְּעֶדְרֵי־צֹ֑אן");
		x = x.replace("אֲשֶׁ֧ר אִם עָבַ֛ר", "אֲשֶׁ֧ר אִם־עָבַ֛ר");
		x = x.replace("כִּֽי אַתָּ֤ה", "כִּֽי־אַתָּ֤ה");
		x = x.replace("וּבֹֽא־עִם־הַמֶּ֥לֶךְ אֶל הַמִּשְׁתֶּ֖ה", "וּבֹֽא־עִם־הַמֶּ֥לֶךְ אֶל־הַמִּשְׁתֶּ֖ה");
		x = x.replace("נִרְאֶ֞ה אִם פָּֽרְחָ֤ה", "נִרְאֶ֞ה אִם־פָּֽרְחָ֤ה");
		x = x.replace("גָּדַ֣ע בָּֽחֳרִי אַ֗ף", "גָּדַ֣ע בׇּֽחֳרִי־אַ֗ף");
		x = x.replace("לָ֥א אֲֽרִֽיךְ לַ֖נָא", "לָ֥א אֲֽרִֽיךְ־לַ֖נָא");
		x = x.replace("מִסְפָּ֑ר כִּֽי הֵ֠בִיאוּ", "מִסְפָּ֑ר כִּֽי־הֵ֠בִיאוּ");
		x = x.replace("וַיִּשְׁמַ֨ע בֶּן הֲדַ֜ד", "וַיִּשְׁמַ֨ע בֶּן־הֲדַ֜ד"); 
		return x;
	}
	
	//Fixes the Zarka trop on a Lamed.
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
	
	//Fixes the Trop of a rare formation.
	public static String replaceGeresh(String bible) {
		String x = bible;
		x = x.replace("פֶּ֝תַח", "פֶּ֜תַח");
		x = x.replace("שֻׁ֝֠בוּ", "שֻׁ֜֠בוּ");
		return x;
	}
	
	//Properly deals with Pasukim which accidentally begin at the Esnachta of the previous Pasuk.
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
	
	//Removes several zero-width joiners from the text.
	public static String specialCharacter() {
		String special = "א‍"; //special character (zero-width joiner) is isolated on this aleph.
	    return special.substring(1, 2);
	}
}
