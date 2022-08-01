package BibleStatistics.Bible;

public class keriUkesiv {
	
	//Replaces all kesiv in the input String with the keri
	public static String keri(String bible){
		int len = bible.length();
		for(int i = 0; i < len; i++) {
			if(bible.substring(i, i + 1).equals("*") && !(bible.substring(i + 1, i + 2).equals("*")) && !(bible.substring(i - 1, i).equals("*"))) {
				for(int j = i; j < i + 50; j++) {
					if(bible.substring(j, j + 1).equals(" ")) {
						bible = bible.substring(0, i) + bible.substring(j + 1);
						i -= (j - i);
						break;
					}
					else if(bible.substring(j, j + 1).equals("־")) {
						if(!bible.substring(j + 1, j + 2).equals(" ")) {
							bible = bible.substring(0, i) + bible.substring(j + 1);
							i -= (j - i);
							break;
						}
						//for אֵ֤י לָזֹאת֙  אֶֽסְלַֽח־לָ֔ךְ בָּנַ֣יִךְ עֲזָב֔וּנִי
						else {
							bible = bible.substring(0, i) + bible.substring(j + 2);
							i -= (j - i);
							break;
						}
					}
				}
			}
			else if(bible.substring(i, i + 1).equals("*") && bible.substring(i + 1, i + 2).equals("*")) {
				bible = bible.substring(0, i) /*+ "+"*/ + bible.substring(i + 2); 
				i -= 2; 
			}
			len = bible.length();
		}
		return bible;
	}

	//Replaces all keri in the input String with the kesiv	
	public static String kesiv(String bible){
		int len = bible.length();
		for(int i = 0; i < len; i++) {
			if(bible.substring(i, i + 1).equals("*") && !(bible.substring(i + 1, i + 2).equals("*")) && !(bible.substring(i - 1, i).equals("*"))) {
				bible = bible.substring(0, i) /*+ "+"*/ + bible.substring(i + 1);
				i -= 1;
			}
			else if(bible.substring(i, i + 1).equals("*") && bible.substring(i + 1, i + 2).equals("*")) {
				for(int j = i; j < i + 50; j++) {
					if(bible.substring(j, j + 1).equals(" ") || bible.substring(j, j + 1).equals("־")) {
						bible = bible.substring(0, i) + bible.substring(j + 1);
						i -= (j - i);
						break;
					}
					else if(bible.substring(j, j + 1).equals("׃")) {
						bible = bible.substring(0, i - 1) + bible.substring(j);
						i -= (j - i);
						break;
					}
				}
			}
			len = bible.length();
		}
		return bible;
	}

	//תַֽעֲשֶׂ֨ה־לְךָ֣ is a problem, does not match, check
		
	//replaces the two-trop system with just the tachton. Must be done BEFORE KeriUkesiv.
	public static String tachton(String bible, boolean header){
		if(!header) {
			bible = bible.replace("וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּ֕ב֙ אֶת־בִּלְהָ֖ה֙ פִּילֶ֣גֶשׁ אָבִ֑֔יו וַיִּשְׁמַ֖ע יִשְׂרָאֵֽ֑ל וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃", "וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּ֕ב אֶת־בִּלְהָ֖ה פִּילֶ֣גֶשׁ אָבִ֑יו וַיִּשְׁמַ֖ע יִשְׂרָאֵֽל׃ וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃");
			bible = bible.replace("אָֽנֹכִ֖י֙ יְהוָ֣ה אֱלֹהֶ֑֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ לֹֽ֣א יִהְיֶֽה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ לֹֽ֣א תַֽעֲשֶׂ֨ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ וְכָל־תְּמוּנָ֡֔ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֡֔עַל וַֽאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתַָּ֑֜חַת וַאֲשֶׁ֥֣ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ לֹֽא־תִשְׁתַּחְוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ וְעֹ֥֤שֶׂה חֶ֖֙סֶד֙ לַאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ זָכ֛וֹר֩ אֶת־י֥֨וֹם הַשַּׁבָּ֖֜ת לְקַדְּשֽׁ֗וֹ ‬ שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּל־מְלַאכְתֶּֽךָ֒ ‬ וְי֙וֹם֙ הַשְּׁבִיעִ֔֜י שַׁבָּ֖֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֑֗יךָ לֹֽ֣א־תַעֲשֶׂ֣֨ה כָל־מְלָאכָ֡֜ה אַתָּ֣ה ׀ וּבִנְךָֽ֣־וּ֠בִתֶּ֗ךָ עַבְדְּךָ֤֨ וַאֲמָֽתְךָ֜֙ וּבְהֶמְתֶּ֔֗ךָ וְגֵרְךָ֖֙ אֲשֶׁ֥֣ר בִּשְׁעָרֶֽ֔יךָ ‬ כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃ כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֥֖א תִּֿרְצָֽ֖ח׃ לֹ֣֖א תִּֿנְאָֽ֑ף׃ לֹ֣֖א תִּֿגְנֹֽ֔ב׃ לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃ לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣ית עֲבָדִ֑ים לֹֽא־יִהְיֶ֥ה לְךָ֛ אֱלֹהִ֥ים אֲחֵרִ֖ים עַל־פָּנָֽי׃ לֹֽא־תַֽעֲשֶׂ֨ה לְךָ֥ פֶ֙סֶל֙ וְכָל־תְּמוּנָ֔ה אֲשֶׁ֤ר בַּשָּׁמַ֙יִם֙ מִמַּ֔עַל וַֽאֲשֶׁ֥ר בָּאָ֖רֶץ מִתָּ֑חַת וַאֲשֶׁ֥ר בַּמַּ֖יִם מִתַּ֥חַת לָאָֽרֶץ׃ לֹֽא־תִשְׁתַּחְוֶ֥ה לָהֶ֖ם וְלֹ֣א תָעָבְדֵ֑ם כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽי׃ וְעֹ֥שֶׂה חֶ֖סֶד לַאֲלָפִ֑ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ זָכ֛וֹר אֶת־י֥וֹם הַשַּׁבָּ֖ת לְקַדְּשֽׁוֹ׃ שֵׁ֤שֶׁת יָמִים֙ תַּֽעֲבֹ֔ד וְעָשִׂ֖יתָ כָּל־מְלַאכְתֶּֽךָ׃ וְיוֹם֙ הַשְּׁבִיעִ֔י שַׁבָּ֖ת לַיהוָ֣ה אֱלֹהֶ֑יךָ לֹֽא־תַעֲשֶׂ֨ה כָל־מְלָאכָ֜ה אַתָּ֣ה ׀ וּבִנְךָ֣ וּבִתֶּ֗ךָ עַבְדְּךָ֤ וַאֲמָֽתְךָ֙ וּבְהֶמְתֶּ֔ךָ וְגֵרְךָ֖ אֲשֶׁ֥ר בִּשְׁעָרֶֽיךָ׃ כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃ כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֥א תִרְצָ֖ח לֹ֣א תִנְאָ֑ף לֹ֣א תִגְנֹ֔ב לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃ לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
			bible = bible.replace("אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ לֹ֣א יִהְיֶ֥ה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ לֹֽ֣א־תַעֲשֶׂ֥ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ כָּל־תְּמוּנָ֔֡ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֔֡עַל וַאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתָּ֑֜חַת וַאֲשֶׁ֥ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ לֹא־תִשְׁתַּחֲוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ וְעֹ֤֥שֶׂה חֶ֖֙סֶד֙ לַֽאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ שָׁמ֣֛וֹר אֶת־י֥וֹם֩ הַשַׁבָּ֖֨ת לְקַדְּשׁ֑֜וֹ כַּאֲשֶׁ֥ר צִוְּךָ֖֣ ׀ יְהוָ֥֣ה אֱלֹהֶֽ֗יךָ ‬ שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּֿל־מְלַאכְתֶּֽךָ֒ ‬ וְי֙וֹם֙ הַשְּׁבִיעִ֜֔י שַׁבָּ֖֣ת ׀ לַיהוָ֖֣ה אֱלֹהֶ֑֗יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽ֑וֹךָ ‬ וְזָכַרְתָּ֞֗ כִּ֣י־עֶ֤֥בֶד הָיִ֣֙יתָ֙ ׀ בְּאֶ֣רֶץ מִצְרַ֔֗יִם וַיֹּצִ֨אֲךָ֜֩ יְהוָ֤֨ה אֱלֹהֶ֤֙יךָ֙ מִשָּׁ֔ם֙ בְּיָ֤֥ד חֲזָקָ֖ה֙ וּבִזְרֹ֣עַ נְטוּיָ֑֔ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃ כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֥֖א תִּֿרְצָֽח׃ וְלֹ֖֣א תִּֿנְאָֽ֑ף׃ וְלֹ֖֣א תִּֿגְנֹֽ֔ב׃ וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃ וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣ית עֲבָדִ֑ים לֹֽא־יִהְיֶ֥ה לְךָ֛ אֱלֹהִ֥ים אֲחֵרִ֖ים עַל־פָּנָֽי׃ לֹֽא־תַֽעֲשֶׂ֨ה לְךָ֥ פֶ֙סֶל֙ כָּל־תְּמוּנָ֔ה אֲשֶׁ֤ר בַּשָּׁמַ֙יִם֙ מִמַּ֔עַל וַֽאֲשֶׁ֥ר בָּאָ֖רֶץ מִתָּ֑חַת וַאֲשֶׁ֥ר בַּמַּ֖יִם מִתַּ֥חַת לָאָֽרֶץ׃ לֹֽא־תִשְׁתַּחְוֶ֥ה לָהֶ֖ם וְלֹ֣א תָעָבְדֵ֑ם כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽי׃ וְעֹ֥שֶׂה חֶ֖סֶד לַאֲלָפִ֑ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ שָׁמ֛וֹר אֶת־י֥וֹם הַשַׁבָּ֖ת לְקַדְּשׁ֑וֹ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֥ה אֱלֹהֶֽיךָ׃ שֵׁ֤שֶׁת יָמִים֙ תַּֽעֲבֹ֔ד וְעָשִׂ֖יתָ כָּל־מְלַאכְתֶּֽךָ׃ וְיוֹם֙ הַשְּׁבִיעִ֔י שַׁבָּ֖ת לַיהוָ֣ה אֱלֹהֶ֑יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽוֹךָ׃ וְזָכַרְתָּ֗ כִּ֣י עֶ֤בֶד הָיִ֙יתָ֙ בְּאֶ֣רֶץ מִצְרַ֔יִם וַיֹּצִ֨אֲךָ֜ יְהוָ֤ה אֱלֹהֶ֙יךָ֙ מִשָּׁ֔ם בְּיָ֥ד חֲזָקָ֖ה וּבִזְרֹ֣עַ נְטוּיָ֑ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃ כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֥א תִרְצָ֖ח וְלֹ֣א תִנְאָ֑ף וְלֹ֣א תִגְנֹ֔ב וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃ וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
		}
		else {
			bible = bible.replace("‫ 22 ׃35  וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּ֕ב֙ אֶת־בִּלְהָ֖ה֙ פִּילֶ֣גֶשׁ אָבִ֑֔יו וַיִּשְׁמַ֖ע יִשְׂרָאֵֽ֑ל וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃ ‬", "‫ 22 ׃35  וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּ֕ב אֶת־בִּלְהָ֖ה פִּילֶ֣גֶשׁ אָבִ֑יו וַיִּשְׁמַ֖ע יִשְׂרָאֵֽל׃ ‬ ‫ 22 ׃35  וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃ ‬");
			bible = bible.replace("אָֽנֹכִ֖י֙ יְהוָ֣ה אֱלֹהֶ֑֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ ‬ ‫ 3  ׃20  לֹֽ֣א יִהְיֶֽה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ ‫ 4  ׃20  לֹֽ֣א תַֽעֲשֶׂ֨ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ וְכָל־תְּמוּנָ֡֔ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֡֔עַל וַֽאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתַָּ֑֜חַת וַאֲשֶׁ֥֣ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ ‫ 5  ׃20  לֹֽא־תִשְׁתַּחְוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ ‬ ‫ 6  ׃20  וְעֹ֥֤שֶׂה חֶ֖֙סֶד֙ לַאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃ ‬ ‫ 7  ׃20  לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ ‬ ‫ 8  ׃20  זָכ֛וֹר֩ אֶת־י֥֨וֹם הַשַּׁבָּ֖֜ת לְקַדְּשֽׁ֗וֹ ‬ ‫ 9  ׃20  שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּל־מְלַאכְתֶּֽךָ֒ ‬ ‫ 10 ׃20  וְי֙וֹם֙ הַשְּׁבִיעִ֔֜י שַׁבָּ֖֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֑֗יךָ לֹֽ֣א־תַעֲשֶׂ֣֨ה כָל־מְלָאכָ֡֜ה אַתָּ֣ה ׀ וּבִנְךָֽ֣־וּ֠בִתֶּ֗ךָ עַבְדְּךָ֤֨ וַאֲמָֽתְךָ֜֙ וּבְהֶמְתֶּ֔֗ךָ וְגֵרְךָ֖֙ אֲשֶׁ֥֣ר בִּשְׁעָרֶֽ֔יךָ ‬ ‫ 11 ׃20  כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃ ‬ ‫ 12 ׃20  כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ ‬ ‫ 13 ׃20  לֹ֥֖א תִּֿרְצָֽ֖ח׃ ‬ ‫ 14 ׃20  לֹ֣֖א תִּֿנְאָֽ֑ף׃ ‬ ‫ 15 ׃20  לֹ֣֖א תִּֿגְנֹֽ֔ב׃ ‬ ‫ 16 ׃20  לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃ ‬ ‫ 17 ׃20  לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣ית עֲבָדִ֑ים לֹֽא־יִהְיֶ֥ה לְךָ֛ אֱלֹהִ֥ים אֲחֵרִ֖ים עַל־פָּנָֽי׃   4  ׃20  לֹֽא־תַֽעֲשֶׂ֨ה לְךָ֥ פֶ֙סֶל֙ וְכָל־תְּמוּנָ֔ה אֲשֶׁ֤ר בַּשָּׁמַ֙יִם֙ מִמַּ֔עַל וַֽאֲשֶׁ֥ר בָּאָ֖רֶץ מִתָּ֑חַת וַאֲשֶׁ֥ר בַּמַּ֖יִם מִתַּ֥חַת לָאָֽרֶץ׃   5  ׃20  לֹֽא־תִשְׁתַּחְוֶ֥ה לָהֶ֖ם וְלֹ֣א תָעָבְדֵ֑ם כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽי׃   6  ׃20  וְעֹ֥שֶׂה חֶ֖סֶד לַאֲלָפִ֑ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃   7  ׃20  לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃   8  ׃20  זָכ֛וֹר אֶת־י֥וֹם הַשַּׁבָּ֖ת לְקַדְּשֽׁוֹ׃   9  ׃20  שֵׁ֤שֶׁת יָמִים֙ תַּֽעֲבֹ֔ד וְעָשִׂ֖יתָ כָּל־מְלַאכְתֶּֽךָ׃   10 ׃20  וְיוֹם֙ הַשְּׁבִיעִ֔י שַׁבָּ֖ת לַיהוָ֣ה אֱלֹהֶ֑יךָ לֹֽא־תַעֲשֶׂ֨ה כָל־מְלָאכָ֜ה אַתָּ֣ה ׀ וּבִנְךָ֣ וּבִתֶּ֗ךָ עַבְדְּךָ֤ וַאֲמָֽתְךָ֙ וּבְהֶמְתֶּ֔ךָ וְגֵרְךָ֖ אֲשֶׁ֥ר בִּשְׁעָרֶֽיךָ׃   11 ׃20  כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃   12 ׃20  כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃   13 ׃20  לֹ֥א תִרְצָ֖ח לֹ֣א תִנְאָ֑ף לֹ֣א תִגְנֹ֔ב לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃   17 ׃20  לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
			bible = bible.replace("אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ ‬ ‫ 7  ׃5   לֹ֣א יִהְיֶ֥ה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ ‫ 8  ׃5   לֹֽ֣א־תַעֲשֶׂ֥ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ כָּל־תְּמוּנָ֔֡ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֔֡עַל וַאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתָּ֑֜חַת וַאֲשֶׁ֥ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ ‫ 9  ׃5   לֹא־תִשְׁתַּחֲוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ ‬ ‫ 10 ׃5   וְעֹ֤֥שֶׂה חֶ֖֙סֶד֙ לַֽאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃ ‬ ‫ 11 ׃5   לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ ‬ ‫ 12 ׃5   שָׁמ֣֛וֹר אֶת־י֥וֹם֩ הַשַׁבָּ֖֨ת לְקַדְּשׁ֑֜וֹ כַּאֲשֶׁ֥ר צִוְּךָ֖֣ ׀ יְהוָ֥֣ה אֱלֹהֶֽ֗יךָ ‬ ‫ 13 ׃5   שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּֿל־מְלַאכְתֶּֽךָ֒ ‬ ‫ 14 ׃5   וְי֙וֹם֙ הַשְּׁבִיעִ֜֔י שַׁבָּ֖֣ת ׀ לַיהוָ֖֣ה אֱלֹהֶ֑֗יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽ֑וֹךָ ‬ ‫ 15 ׃5   וְזָכַרְתָּ֞֗ כִּ֣י־עֶ֤֥בֶד הָיִ֣֙יתָ֙ ׀ בְּאֶ֣רֶץ מִצְרַ֔֗יִם וַיֹּצִ֨אֲךָ֜֩ יְהוָ֤֨ה אֱלֹהֶ֤֙יךָ֙ מִשָּׁ֔ם֙ בְּיָ֤֥ד חֲזָקָ֖ה֙ וּבִזְרֹ֣עַ נְטוּיָ֑֔ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃ ‬ ‫ 16 ׃5   כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ ‬ ‫ 17 ׃5   לֹ֥֖א תִּֿרְצָֽח׃ ‬ ‫ 18 ׃5   וְלֹ֖֣א תִּֿנְאָֽ֑ף׃ ‬ ‫ 19 ׃5   וְלֹ֖֣א תִּֿגְנֹֽ֔ב׃ ‬ ‫ 20 ׃5   וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃ ‬ ‫ 21 ׃5   וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣ית עֲבָדִ֑ים לֹֽא־יִהְיֶ֥ה לְךָ֛ אֱלֹהִ֥ים אֲחֵרִ֖ים עַל־פָּנָֽי׃   8  ׃5   לֹֽא־תַֽעֲשֶׂ֨ה לְךָ֥ פֶ֙סֶל֙ כָּל־תְּמוּנָ֔ה אֲשֶׁ֤ר בַּשָּׁמַ֙יִם֙ מִמַּ֔עַל וַֽאֲשֶׁ֥ר בָּאָ֖רֶץ מִתָּ֑חַת וַאֲשֶׁ֥ר בַּמַּ֖יִם מִתַּ֥חַת לָאָֽרֶץ׃   9  ׃5   לֹֽא־תִשְׁתַּחְוֶ֥ה לָהֶ֖ם וְלֹ֣א תָעָבְדֵ֑ם כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽי׃   10 ׃5   וְעֹ֥שֶׂה חֶ֖סֶד לַאֲלָפִ֑ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃   11 ׃5   לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃   12 ׃5   שָׁמ֛וֹר אֶת־י֥וֹם הַשַׁבָּ֖ת לְקַדְּשׁ֑וֹ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֥ה אֱלֹהֶֽיךָ׃ ‬ ‫ 13 ׃5   שֵׁ֤שֶׁת יָמִים֙ תַּֽעֲבֹ֔ד וְעָשִׂ֖יתָ כָּל־מְלַאכְתֶּֽךָ׃ ‬ ‫ 14 ׃5   וְיוֹם֙ הַשְּׁבִיעִ֔י שַׁבָּ֖ת לַיהוָ֣ה אֱלֹהֶ֑יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽוֹךָ׃   15 ׃5   וְזָכַרְתָּ֗ כִּ֣י עֶ֤בֶד הָיִ֙יתָ֙ בְּאֶ֣רֶץ מִצְרַ֔יִם וַיֹּצִ֨אֲךָ֜ יְהוָ֤ה אֱלֹהֶ֙יךָ֙ מִשָּׁ֔ם בְּיָ֥ד חֲזָקָ֖ה וּבִזְרֹ֣עַ נְטוּיָ֑ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃   16 ׃5   כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃   17 ׃5   לֹ֥א תִרְצָ֖ח וְלֹ֣א תִנְאָ֑ף וְלֹ֣א תִגְנֹ֔ב וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃   21 ׃5   וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
		}
			return bible;
	}
	
	
	//replaces the two-trop system with just the elyon. Must be done BEFORE KeriUkesiv.
	public static String elyon(String bible, boolean header){
		if(!header) {
			bible = bible.replace("וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּ֕ב֙ אֶת־בִּלְהָ֖ה֙ פִּילֶ֣גֶשׁ אָבִ֑֔יו וַיִּשְׁמַ֖ע יִשְׂרָאֵֽ֑ל וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃", "וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּב֙ אֶת־בִּלְהָה֙ פִּילֶ֣גֶשׁ אָבִ֔יו וַיִּשְׁמַ֖ע יִשְׂרָאֵ֑ל וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃");
			bible = bible.replace("אָֽנֹכִ֖י֙ יְהוָ֣ה אֱלֹהֶ֑֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ לֹֽ֣א יִהְיֶֽה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ לֹֽ֣א תַֽעֲשֶׂ֨ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ וְכָל־תְּמוּנָ֡֔ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֡֔עַל וַֽאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתַָּ֑֜חַת וַאֲשֶׁ֥֣ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ לֹֽא־תִשְׁתַּחְוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ וְעֹ֥֤שֶׂה חֶ֖֙סֶד֙ לַאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ זָכ֛וֹר֩ אֶת־י֥֨וֹם הַשַּׁבָּ֖֜ת לְקַדְּשֽׁ֗וֹ ‬ שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּל־מְלַאכְתֶּֽךָ֒ ‬ וְי֙וֹם֙ הַשְּׁבִיעִ֔֜י שַׁבָּ֖֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֑֗יךָ לֹֽ֣א־תַעֲשֶׂ֣֨ה כָל־מְלָאכָ֡֜ה אַתָּ֣ה ׀ וּבִנְךָֽ֣־וּ֠בִתֶּ֗ךָ עַבְדְּךָ֤֨ וַאֲמָֽתְךָ֜֙ וּבְהֶמְתֶּ֔֗ךָ וְגֵרְךָ֖֙ אֲשֶׁ֥֣ר בִּשְׁעָרֶֽ֔יךָ ‬ כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃ כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֥֖א תִּֿרְצָֽ֖ח׃ לֹ֣֖א תִּֿנְאָֽ֑ף׃ לֹ֣֖א תִּֿגְנֹֽ֔ב׃ לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃ לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִ֖י יְהוָ֣ה אֱלֹהֶ֑יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֥ית עֲבָדִֽים׃ לֹ֣א יִהְיֶֽה־לְךָ֩ אֱלֹהִ֨ים אֲחֵרִ֜ים עַל־פָּנַ֗י לֹ֣א תַֽעֲשֶׂה־לְךָ֣ פֶ֣סֶל ׀ וְכָל־תְּמוּנָ֡ה אֲשֶׁ֣ר בַּשָּׁמַ֣יִם֙ ׀ מִמַּ֡עַל וַֽאֲשֶׁר֩ בָּאָ֨רֶץ מִתַּ֜חַת וַאֲשֶׁ֣ר בַּמַּ֣יִם ׀ מִתַּ֣חַת לָאָ֗רֶץ לֹֽא־תִשְׁתַּחְוֶ֣ה לָהֶם֮ וְלֹ֣א תָעָבְדֵם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָ֑י וְעֹ֤שֶׂה חֶ֙סֶד֙ לַאֲלָפִ֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ זָכוֹר֩ אֶת־י֨וֹם הַשַּׁבָּ֜ת לְקַדְּשׁ֗וֹ שֵׁ֣שֶׁת יָמִ֣ים תַּֽעֲבֹ֔ד֮ וְעָשִׂ֣יתָ כָל־מְלַאכְתֶּךָ֒ וְי֙וֹם הַשְּׁבִיעִ֜י שַׁבָּ֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֗יךָ לֹ֣א־תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּ֠בִתֶּךָ עַבְדְּךָ֨ וַאֲמָֽתְךָ֜ וּבְהֶמְתֶּ֗ךָ וְגֵרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃ כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֖א תִּרְצָֽח׃ לֹ֖א תִּנְאָֽף׃ לֹ֖א תִּגְנֹֽב׃ לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃ לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
			bible = bible.replace("אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ לֹ֣א יִהְיֶ֥ה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ לֹֽ֣א־תַעֲשֶׂ֥ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ כָּל־תְּמוּנָ֔֡ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֔֡עַל וַאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתָּ֑֜חַת וַאֲשֶׁ֥ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ לֹא־תִשְׁתַּחֲוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ וְעֹ֤֥שֶׂה חֶ֖֙סֶד֙ לַֽאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ שָׁמ֣֛וֹר אֶת־י֥וֹם֩ הַשַׁבָּ֖֨ת לְקַדְּשׁ֑֜וֹ כַּאֲשֶׁ֥ר צִוְּךָ֖֣ ׀ יְהוָ֥֣ה אֱלֹהֶֽ֗יךָ ‬ שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּֿל־מְלַאכְתֶּֽךָ֒ ‬ וְי֙וֹם֙ הַשְּׁבִיעִ֜֔י שַׁבָּ֖֣ת ׀ לַיהוָ֖֣ה אֱלֹהֶ֑֗יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽ֑וֹךָ ‬ וְזָכַרְתָּ֞֗ כִּ֣י־עֶ֤֥בֶד הָיִ֣֙יתָ֙ ׀ בְּאֶ֣רֶץ מִצְרַ֔֗יִם וַיֹּצִ֨אֲךָ֜֩ יְהוָ֤֨ה אֱלֹהֶ֤֙יךָ֙ מִשָּׁ֔ם֙ בְּיָ֤֥ד חֲזָקָ֖ה֙ וּבִזְרֹ֣עַ נְטוּיָ֑֔ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃ כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֥֖א תִּֿרְצָֽח׃ וְלֹ֖֣א תִּֿנְאָֽ֑ף׃ וְלֹ֖֣א תִּֿגְנֹֽ֔ב׃ וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃ וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִ֖י יְהוָ֣ה אֱלֹהֶ֑יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֥ית עֲבָדִֽים׃ לֹ֣א יִהְיֶֽה־לְךָ֩ אֱלֹהִ֨ים אֲחֵרִ֜ים עַל־פָּנַ֗י לֹ֣א תַֽעֲשֶׂה־לְךָ֣ פֶ֣סֶל ׀ כָּל־תְּמוּנָ֡ה אֲשֶׁ֣ר בַּשָּׁמַ֣יִם֙ ׀ מִמַּ֡עַל וַֽאֲשֶׁר֩ בָּאָ֨רֶץ מִתַּ֜חַת וַאֲשֶׁ֣ר בַּמַּ֣יִם ׀ מִתַּ֣חַת לָאָ֗רֶץ לֹֽא־תִשְׁתַּחְוֶ֣ה לָהֶם֮ וְלֹ֣א תָעָבְדֵם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָ֑י וְעֹ֤שֶׂה חֶ֙סֶד֙ לַאֲלָפִ֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃ לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ שָׁמ֣וֹר אֶת־יוֹם֩ הַשַׁבָּ֨ת לְקַדְּשׁ֜וֹ כַּאֲשֶׁ֥ר צִוְּךָ֣ ׀ יְהוָ֣ה אֱלֹהֶ֗יךָ שֵׁ֣שֶׁת יָמִ֣ים תַּֽעֲבֹד֮ וְעָשִׂ֣יתָ כָל־מְלַאכְתֶּךָ֒ וְי֙וֹם הַשְּׁבִיעִ֜י שַׁבָּ֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֗יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽ֑וֹךָ וְזָכַרְתָּ֞ כִּ֣י־עֶ֥בֶד הָיִ֣יתָ ׀ בְּאֶ֣רֶץ מִצְרַ֗יִם וַיֹּצִאֲךָ֩ יְהוָ֨ה אֱלֹהֶ֤יךָ מִשָּׁם֙ בְּיָ֤ד חֲזָקָה֙ וּבִזְרֹ֣עַ נְטוּיָ֔ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃ כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ לֹ֖א תִּרְצָֽח׃ וְלֹ֖א תִּנְאָֽף׃ וְלֹ֖א תִּגְנֹֽב׃ וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃ וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
		}
		else {
			bible = bible.replace("‫ 22 ׃35  וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּ֕ב֙ אֶת־בִּלְהָ֖ה֙ פִּילֶ֣גֶשׁ אָבִ֑֔יו וַיִּשְׁמַ֖ע יִשְׂרָאֵֽ֑ל וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃ ‬", "‫ 22 ׃35  וַיְהִ֗י בִּשְׁכֹּ֤ן יִשְׂרָאֵל֙ בָּאָ֣רֶץ הַהִ֔וא וַיֵּ֣לֶךְ רְאוּבֵ֔ן וַיִּשְׁכַּב֙ אֶת־בִּלְהָה֙ פִּילֶ֣גֶשׁ אָבִ֔יו וַיִּשְׁמַ֖ע יִשְׂרָאֵ֑ל וַיִּֽהְי֥וּ בְנֵֽי־יַעֲקֹ֖ב שְׁנֵ֥ים עָשָֽׂר׃ ‬");
			bible = bible.replace("אָֽנֹכִ֖י֙ יְהוָ֣ה אֱלֹהֶ֑֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ ‬ ‫ 3  ׃20  לֹֽ֣א יִהְיֶֽה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ ‫ 4  ׃20  לֹֽ֣א תַֽעֲשֶׂ֨ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ וְכָל־תְּמוּנָ֡֔ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֡֔עַל וַֽאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתַָּ֑֜חַת וַאֲשֶׁ֥֣ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ ‫ 5  ׃20  לֹֽא־תִשְׁתַּחְוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ ‬ ‫ 6  ׃20  וְעֹ֥֤שֶׂה חֶ֖֙סֶד֙ לַאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃ ‬ ‫ 7  ׃20  לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ ‬ ‫ 8  ׃20  זָכ֛וֹר֩ אֶת־י֥֨וֹם הַשַּׁבָּ֖֜ת לְקַדְּשֽׁ֗וֹ ‬ ‫ 9  ׃20  שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּל־מְלַאכְתֶּֽךָ֒ ‬ ‫ 10 ׃20  וְי֙וֹם֙ הַשְּׁבִיעִ֔֜י שַׁבָּ֖֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֑֗יךָ לֹֽ֣א־תַעֲשֶׂ֣֨ה כָל־מְלָאכָ֡֜ה אַתָּ֣ה ׀ וּבִנְךָֽ֣־וּ֠בִתֶּ֗ךָ עַבְדְּךָ֤֨ וַאֲמָֽתְךָ֜֙ וּבְהֶמְתֶּ֔֗ךָ וְגֵרְךָ֖֙ אֲשֶׁ֥֣ר בִּשְׁעָרֶֽ֔יךָ ‬ ‫ 11 ׃20  כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃ ‬ ‫ 12 ׃20  כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ ‬ ‫ 13 ׃20  לֹ֥֖א תִּֿרְצָֽ֖ח׃ ‬ ‫ 14 ׃20  לֹ֣֖א תִּֿנְאָֽ֑ף׃ ‬ ‫ 15 ׃20  לֹ֣֖א תִּֿגְנֹֽ֔ב׃ ‬ ‫ 16 ׃20  לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃ ‬ ‫ 17 ׃20  לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִ֖י יְהוָ֣ה אֱלֹהֶ֑יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֥ית עֲבָדִֽים׃   3  ׃20  לֹ֣א יִהְיֶֽה־לְךָ֩ אֱלֹהִ֨ים אֲחֵרִ֜ים עַל־פָּנַ֗י לֹ֣א תַֽעֲשֶׂה־לְךָ֣ פֶ֣סֶל ׀ וְכָל־תְּמוּנָ֡ה אֲשֶׁ֣ר בַּשָּׁמַ֣יִם֙ ׀ מִמַּ֡עַל וַֽאֲשֶׁר֩ בָּאָ֨רֶץ מִתַּ֜חַת וַאֲשֶׁ֣ר בַּמַּ֣יִם ׀ מִתַּ֣חַת לָאָ֗רֶץ לֹֽא־תִשְׁתַּחְוֶ֣ה לָהֶם֮ וְלֹ֣א תָעָבְדֵם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָבֹ֧ת עַל־בָּנִ֛ים עַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָ֑י וְעֹ֤שֶׂה חֶ֙סֶד֙ לַאֲלָפִ֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י מִצְוֺתָֽי׃   7  ׃20  לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃   8  ׃20  זָכוֹר֩ אֶת־י֨וֹם הַשַּׁבָּ֜ת לְקַדְּשׁ֗וֹ שֵׁ֣שֶׁת יָמִ֣ים תַּֽעֲבֹ֔ד֮ וְעָשִׂ֣יתָ כָל־מְלַאכְתֶּךָ֒ וְי֙וֹם הַשְּׁבִיעִ֜י שַׁבָּ֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֗יךָ לֹ֣א־תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּ֠בִתֶּךָ עַבְדְּךָ֨ וַאֲמָֽתְךָ֜ וּבְהֶמְתֶּ֗ךָ וְגֵרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ כִּ֣י שֵֽׁשֶׁת־יָמִים֩ עָשָׂ֨ה יְהוָ֜ה אֶת־הַשָּׁמַ֣יִם וְאֶת־הָאָ֗רֶץ אֶת־הַיָּם֙ וְאֶת־כָּל־אֲשֶׁר־בָּ֔ם וַיָּ֖נַח בַּיּ֣וֹם הַשְּׁבִיעִ֑י עַל־כֵּ֗ן בֵּרַ֧ךְ יְהוָ֛ה אֶת־י֥וֹם הַשַּׁבָּ֖ת וַֽיְקַדְּשֵֽׁהוּ׃   12 ׃20  כַּבֵּ֥ד אֶת־אָבִ֖יךָ וְאֶת־אִמֶּ֑ךָ לְמַ֙עַן֙ יַאֲרִכ֣וּן יָמֶ֔יךָ עַ֚ל הָאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃   13 ׃20  לֹ֖א תִּרְצָֽח׃   14 ׃20  לֹ֖א תִּנְאָֽף׃   15 ׃20  לֹ֖א תִּגְנֹֽב׃   16 ׃20  לֹֽא־תַעֲנֶ֥ה בְרֵעֲךָ֖ עֵ֥ד שָֽׁקֶר׃   17 ׃20  לֹ֥א תַחְמֹ֖ד בֵּ֣ית רֵעֶ֑ךָ לֹֽא־תַחְמֹ֞ד אֵ֣שֶׁת רֵעֶ֗ךָ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ וְשׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
			bible = bible.replace("אָֽנֹכִי֙ יְהוָ֣ה אֱלֹהֶ֔יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֣֥ית עֲבָדִֽ֑ים׃ ‬ ‫ 7  ׃5   לֹ֣א יִהְיֶ֥ה־לְךָ֛֩ אֱלֹהִ֥֨ים אֲחֵרִ֖֜ים עַל־פָּנָֽ֗יַ ‬ ‫ 8  ׃5   לֹֽ֣א־תַעֲשֶׂ֥ה־לְךָ֥֣ פֶ֣֙סֶל֙ ׀ כָּל־תְּמוּנָ֔֡ה אֲשֶׁ֤֣ר בַּשָּׁמַ֣֙יִם֙ ׀ מִמַּ֔֡עַל וַאֲשֶׁ֥ר֩ בָּאָ֖֨רֶץ מִתָּ֑֜חַת וַאֲשֶׁ֥ר בַּמַּ֖֣יִם ׀ מִתַּ֥֣חַת לָאָֽ֗רֶץ ‬ ‫ 9  ׃5   לֹא־תִשְׁתַּחֲוֶ֥֣ה לָהֶ֖ם֮ וְלֹ֣א תָעָבְדֵ֑ם֒ כִּ֣י אָנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָֽ֑י׃ ‬ ‫ 10 ׃5   וְעֹ֤֥שֶׂה חֶ֖֙סֶד֙ לַֽאֲלָפִ֑֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃ ‬ ‫ 11 ׃5   לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃ ‬ ‫ 12 ׃5   שָׁמ֣֛וֹר אֶת־י֥וֹם֩ הַשַׁבָּ֖֨ת לְקַדְּשׁ֑֜וֹ כַּאֲשֶׁ֥ר צִוְּךָ֖֣ ׀ יְהוָ֥֣ה אֱלֹהֶֽ֗יךָ ‬ ‫ 13 ׃5   שֵׁ֤֣שֶׁת יָמִ֣ים֙ תַּֽעֲבֹ֔ד֮ וְעָשִׂ֖֣יתָ כָּֿל־מְלַאכְתֶּֽךָ֒ ‬ ‫ 14 ׃5   וְי֙וֹם֙ הַשְּׁבִיעִ֜֔י שַׁבָּ֖֣ת ׀ לַיהוָ֖֣ה אֱלֹהֶ֑֗יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽ֑וֹךָ ‬ ‫ 15 ׃5   וְזָכַרְתָּ֞֗ כִּ֣י־עֶ֤֥בֶד הָיִ֣֙יתָ֙ ׀ בְּאֶ֣רֶץ מִצְרַ֔֗יִם וַיֹּצִ֨אֲךָ֜֩ יְהוָ֤֨ה אֱלֹהֶ֤֙יךָ֙ מִשָּׁ֔ם֙ בְּיָ֤֥ד חֲזָקָ֖ה֙ וּבִזְרֹ֣עַ נְטוּיָ֑֔ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃ ‬ ‫ 16 ׃5   כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃ ‬ ‫ 17 ׃5   לֹ֥֖א תִּֿרְצָֽח׃ ‬ ‫ 18 ׃5   וְלֹ֖֣א תִּֿנְאָֽ֑ף׃ ‬ ‫ 19 ׃5   וְלֹ֖֣א תִּֿגְנֹֽ֔ב׃ ‬ ‫ 20 ׃5   וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃ ‬ ‫ 21 ׃5   וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃", "אָֽנֹכִ֖י יְהוָ֣ה אֱלֹהֶ֑יךָ אֲשֶׁ֧ר הוֹצֵאתִ֛יךָ מֵאֶ֥רֶץ מִצְרַ֖יִם מִבֵּ֥ית עֲבָדִֽים׃   7  ׃5   לֹ֣א יִהְיֶֽה־לְךָ֩ אֱלֹהִ֨ים אֲחֵרִ֜ים עַל־פָּנַ֗י לֹ֣א תַֽעֲשֶׂה־לְךָ֣ פֶ֣סֶל ׀ כָּל־תְּמוּנָ֡ה אֲשֶׁ֣ר בַּשָּׁמַ֣יִם֙ ׀ מִמַּ֡עַל וַֽאֲשֶׁר֩ בָּאָ֨רֶץ מִתַּ֜חַת וַאֲשֶׁ֣ר בַּמַּ֣יִם ׀ מִתַּ֣חַת לָאָ֗רֶץ לֹֽא־תִשְׁתַּחְוֶ֣ה לָהֶם֮ וְלֹ֣א תָעָבְדֵם֒ כִּ֣י אָֽנֹכִ֞י יְהוָ֤ה אֱלֹהֶ֙יךָ֙ אֵ֣ל קַנָּ֔א פֹּ֠קֵד עֲוֺ֨ן אָב֧וֹת עַל־בָּנִ֛ים וְעַל־שִׁלֵּשִׁ֥ים וְעַל־רִבֵּעִ֖ים לְשֹׂנְאָ֑י וְעֹ֤שֶׂה חֶ֙סֶד֙ לַאֲלָפִ֔ים לְאֹהֲבַ֖י וּלְשֹׁמְרֵ֥י *מצותו **מִצְוֺתָֽי׃   11 ׃5   לֹ֥א תִשָּׂ֛א אֶת־שֵֽׁם־יְהוָ֥ה אֱלֹהֶ֖יךָ לַשָּׁ֑וְא כִּ֣י לֹ֤א יְנַקֶּה֙ יְהוָ֔ה אֵ֛ת אֲשֶׁר־יִשָּׂ֥א אֶת־שְׁמ֖וֹ לַשָּֽׁוְא׃   12 ׃5   שָׁמ֣וֹר אֶת־יוֹם֩ הַשַׁבָּ֨ת לְקַדְּשׁ֜וֹ כַּאֲשֶׁ֥ר צִוְּךָ֣ ׀ יְהוָ֣ה אֱלֹהֶ֗יךָ שֵׁ֣שֶׁת יָמִ֣ים תַּֽעֲבֹד֮ וְעָשִׂ֣יתָ כָל־מְלַאכְתֶּךָ֒ וְי֙וֹם הַשְּׁבִיעִ֜י שַׁבָּ֣ת ׀ לַיהוָ֣ה אֱלֹהֶ֗יךָ לֹ֣א תַעֲשֶׂ֣ה כָל־מְלָאכָ֡ה אַתָּ֣ה וּבִנְךָֽ־וּבִתֶּ֣ךָ וְעַבְדְּךָֽ־וַ֠אֲמָתֶךָ וְשׁוֹרְךָ֨ וַחֲמֹֽרְךָ֜ וְכָל־בְּהֶמְתֶּ֗ךָ וְגֵֽרְךָ֙ אֲשֶׁ֣ר בִּשְׁעָרֶ֔יךָ לְמַ֗עַן יָנ֛וּחַ עַבְדְּךָ֥ וַאֲמָתְךָ֖ כָּמֽ֑וֹךָ וְזָכַרְתָּ֞ כִּ֣י־עֶ֥בֶד הָיִ֣יתָ ׀ בְּאֶ֣רֶץ מִצְרַ֗יִם וַיֹּצִאֲךָ֩ יְהוָ֨ה אֱלֹהֶ֤יךָ מִשָּׁם֙ בְּיָ֤ד חֲזָקָה֙ וּבִזְרֹ֣עַ נְטוּיָ֔ה עַל־כֵּ֗ן צִוְּךָ֙ יְהוָ֣ה אֱלֹהֶ֔יךָ לַעֲשׂ֖וֹת אֶת־י֥וֹם הַשַׁבָּֽת׃   16 ׃5   כַּבֵּ֤ד אֶת־אָבִ֙יךָ֙ וְאֶת־אִמֶּ֔ךָ כַּאֲשֶׁ֥ר צִוְּךָ֖ יְהוָ֣ה אֱלֹהֶ֑יךָ לְמַ֣עַן ׀ יַאֲרִיכֻ֣ן יָמֶ֗יךָ וּלְמַ֙עַן֙ יִ֣יטַב לָ֔ךְ עַ֚ל הָֽאֲדָמָ֔ה אֲשֶׁר־יְהוָ֥ה אֱלֹהֶ֖יךָ נֹתֵ֥ן לָֽךְ׃   17 ׃5  לֹ֖א תִּרְצָֽח׃   18 ׃5   וְלֹ֖א תִּנְאָֽף׃   19 ׃5   וְלֹ֖א תִּגְנֹֽב׃   20 ׃5   וְלֹֽא־תַעֲנֶ֥ה בְרֵֽעֲךָ֖ עֵ֥ד שָֽׁוְא׃   21 ׃5   וְלֹ֥א תַחְמֹ֖ד אֵ֣שֶׁת רֵעֶ֑ךָ וְלֹ֨א תִתְאַוֶּ֜ה בֵּ֣ית רֵעֶ֗ךָ שָׂדֵ֜הוּ וְעַבְדּ֤וֹ וַאֲמָתוֹ֙ שׁוֹר֣וֹ וַחֲמֹר֔וֹ וְכֹ֖ל אֲשֶׁ֥ר לְרֵעֶֽךָ׃");
		}
		return bible;
	}
			
}