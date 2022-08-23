package BibleStatistics.Level_2;

import java.util.ArrayList;

import BibleStatistics.Level_1.basicLists;
import BibleStatistics.Level_1.otherBibles;
import BibleStatistics.Level_1.tools;

public class tropLists {

	//creates a list of all the trop (per word) in the given input String
	public static String[] tropFinder(String[] words, String book, int chapter, int verse){
		Boolean three = false;
		if(book.equals("Psalms") || book.equals("Proverbs")){
			three = true;
		}
		else if(book.equals("Job") && (chapter > 2) && (chapter < 42)){
			three = true;
		}
		else if(book.equals("Job") && (chapter == 42) && (verse > 6)){
			three = true;
		}
		ArrayList<String> trop = new ArrayList<String>();
		String lastdis = "";
		String previous = "";
		int wordslen = words.length;
		if(!three){
			for(int i = wordslen - 1; i >= 0; i--){
				String toadd = "";
				String current = words[i];
				String now = otherBibles.justTrop(current);
				int len = now.length();
				if(len == 0) {
					toadd = "none";
				}
				else if(len == 1) {
					toadd = basicLists.translate(now);
				}
				else if(len > 2) {
					if(now.substring(len - 1, len).equals("׀")){
						String[] thisword = {now.substring(0, len - 1)};
						toadd = tropFinder(thisword, book, chapter, verse)[0] + "*";
					}
					else if(now.substring(1, 2).equals(now.substring(2, 3))){
						toadd = basicLists.translate(now.substring(0, 1)) + "-" + basicLists.translate(now.substring(1, 2)) + "*";
					}
					else{
						toadd = basicLists.translate(now.substring(0, 1)) + "-" + basicLists.translate(now.substring(1, 2)) + "-" + basicLists.translate(now.substring(2, 3)) + "*";
						//trop.add(0, words[i] + " error, more than two trop");
					}
				}
				else{
					if(now.contains("֨") && now.contains("֜")){
						toadd = "kadmah-azlah";
					}
					else if(now.contains("֔") && now.contains("֨")){
						toadd = "mesiga-zakaif";
					}
					else if(now.contains("֔") && now.contains("֣")){
						toadd = "munach-zakaif";
					}
					else if(now.contains("֣") && now.contains("֗")){
						toadd = "munach-revie"; 
					}
					else if(now.contains("֖") && now.contains("׃")){
						toadd = "mayela";
						trop.add(0, toadd);
						toadd = "sof-pasuk";
					}
					else if(now.contains("֖") && now.contains("֑")){
						toadd = "mayela";
						trop.add(0, toadd);
						toadd = "esnachta";
					}
					else if(now.contains("֣") && now.contains("׀")){
						if(lastdis.equals("revie") || lastdis.equals("legarmeh")){
							toadd = "legarmeh";
							if(current.equals("הָאֵ֣ל׀")){
								toadd = "munach"; 
							}
						}
						else{
							toadd = legarmehexcpetions(current);
						}
					}
					else if(now.substring(1, 2).equals("׀")){
						toadd = basicLists.translate(now.substring(0, 1));
					}
					else if(now.substring(0, 1).equals(now.substring(1, 2))){
						toadd = basicLists.translate(now.substring(0, 1));
					}
					else {
						toadd = basicLists.translate(now.substring(0, 1)) + "*" + basicLists.translate(now.substring(1, 2));
						//System.out.println(words[j] + " two trop error" + " \"" + basicLists.translate(now.substring(0, 1)) + " " + basicLists.translate(now.substring(1, 2)) + " *" + "\"");
					}
				}
				trop.add(0, toadd);
				previous = toadd;
				if(basicLists.isdisjunctive(toadd)){
					lastdis = toadd;
				}
			}
		}
		else{
			for(int i = wordslen - 1; i >= 0; i--){
				String toadd = "";
				String current = words[i];
				String now = otherBibles.justTrop(current);
				int len = now.length();
				if(len == 0) {
					trop.add(0, "none");
				}
				else if(len == 1) {
					if(now.equals("֮")){
						toadd = "tsinor";
					}
					else if(now.equals("֓")){
						toadd = "shalsheles-ketanah";
					}
					else if(now.equals("֪")){
						toadd = "esnach-haphuch";
					}
					else if(now.equals("֖")){
						toadd = "tarcha";
					}
					else if(now.equals("֗")){
						toadd = "revie-gadol";
					}
					else if(now.equals("֫")){
						toadd = basicLists.translate(now);
						if(previous.equals("mercha")){
							trop.set(0, "veyoraid");
						}
					}
					else{
						toadd = basicLists.translate(now);
					}
				}
				else if(len > 2) {
					if(now.substring(len - 1, len).equals("׀")){
						String[] thisword = {now.substring(1)};
						toadd = basicLists.translate(now.substring(0, 1)) + "-" + tropFinder(thisword, book, chapter, verse)[0];
					}
					else{
						toadd = basicLists.translate(now.substring(0, 1)) + "-" + basicLists.translate(now.substring(1, 2)) + "-" + basicLists.translate(now.substring(2, 3)) + "*";
						//trop.add(0, words[i] + " error, more than two trop");
					}
				}
				else{
					if(now.contains("֗") && now.contains("֝")){
						toadd = "revie-mugrash";
					}
					else if(now.contains("֥") && now.contains("֫")){
						toadd = "oleh-veyoraid";
						if(previous.equals("revie-gadol")){
							trop.set(0, "revie-katan");
						}
					}
					else if(now.contains("֨") && now.contains("׀")){
						toadd = "azlah-legarmeh";
					}
					else if(now.contains("֤") && now.contains("׀")){
						toadd = "mahpach-legarmeh";
					}
					else if(now.contains("֓") && now.contains("׀")){
						toadd = "shalsheles-gedolah";
					}
					else if(now.substring(0, 1).equals("֘")){
						toadd = basicLists.translate(now.substring(0, 1)) + "-" + basicLists.translate(now.substring(1, 2));
					} 
					else if(now.substring(1, 2).equals("֫") && previous.equals("mercha")){
						toadd = basicLists.translate(now.substring(0, 1)) + "-" + basicLists.translate(now.substring(1, 2));
						trop.set(0, "veyoraid");
					} 
					else if(now.substring(0, 1).equals(now.substring(1, 2))){
						toadd = basicLists.translate(now.substring(0, 1));
					}
					else if(now.substring(1, 2).equals("׀")){
						toadd = basicLists.translate(now.substring(0, 1));
					}
					else {
						toadd = basicLists.translate(now.substring(0, 1)) + "*" + basicLists.translate(now.substring(1, 2));
						//System.out.println(words[j] + " two trop error" + " \"" + basicLists.translate(now.substring(0, 1)) + " " + basicLists.translate(now.substring(1, 2)) + " *" + "\"");
					}
				}
				trop.add(0, toadd);
				previous = toadd;
			}
		}
		String[] results = tools.ArraylistToArray(trop);
		return results;
	}

	public static String legarmehexcpetions(String word){
		String toadd = "munach";
		if(word.equals("אֶֽת־מָחֲלַ֣ת׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("אִיכָב֣וֹד׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("בְּמוֹאָ֣ב׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("יוֹנָדָ֣ב׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("וְאֶת־רַב־סָרִ֣יס׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("וְאֶת־רַב־סָרִ֣יס׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("מֵעַ֣י׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("וַיִּקַּ֣ח׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("אֲשֶׁר־בְּמוֹאָ֣ב׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("בָּאִ֣ים׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("הֵ֣ן׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("וַיַּ֣עַשׂ׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("אַל־תִּפְרָ֣עוּ׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("עַל־רֹאשׁ֣וֹ׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("שְׁנֵֽי־בָנָ֣יו׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("מֶֽלֶךְ־אַשּׁ֣וּר׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("לְמִכְנַ֣שׁ׀")){
			toadd = "legarmeh";
		}
		else if(word.equals("וְשֵׁרֵ֥בְיָ֣ה ׀")){
			toadd = "mercha*legarmeh";
		}
		return toadd;
	}

	/*public static String[] tropFinder(String[] words, String book, int chapter, int verse){
		Boolean three = false;
		if(book.equals("Psalms") || book.equals("Proverbs")){
			three = true;
		}
		else if(book.equals("Job") && (chapter > 2) && (chapter < 42)){
			three = true;
		}
		else if(book.equals("Job") && (chapter == 42) && (verse > 6)){
			three = true;
		}
		ArrayList<String> trope = new ArrayList<String>();
		int wordslen = words.length;
		for(int j = 0; j < wordslen; j++){
			String now = otherBibles.justTrop(words[j]);
			//System.out.println(now);
			if(!three){
				if(now.length() == 0) {
					trope.add("none");
				}
				else if(now.length() == 1) {
					trope.add(basicLists.translate(now));
				}
				else if(now.length() == 2) {
					if(now.contains("֨") && now.contains("֜")){
						trope.add("kadmah-azlah");
					}
					else if(now.contains("֔") && now.contains("֨")){
						trope.add("mesiga-zakaif");
					}
					else if(now.contains("֔") && now.contains("֣")){
						trope.add("munach-zakaif");
					}
					else if(now.contains("֖") && now.contains("׃")){
						trope.add("mayela");
						trope.add("sof-pasuk");
					}
					else if(now.contains("֖") && now.contains("֑")){
						trope.add("mayela");
						trope.add("esnachta");
					}
					else if(now.contains("֣") && now.contains("׀")){
						trope.add("munach-pesik"); 
					}
					else if(now.contains("֣") && now.contains("֗")){
						trope.add("munach-revie"); 
					}
					else if(now.substring(0, 1).equals(now.substring(1, 2))){
						trope.add(basicLists.translate(now.substring(0, 1)) + "2");
					}
					else if(!now.substring(0, 1).equals("֣") && now.substring(1, 2).equals("׀")){
						trope.add(basicLists.translate(now.substring(0, 1)));
					}
					else {
						trope.add(basicLists.translate(now.substring(0, 1)) + "*" + basicLists.translate(now.substring(1, 2)));
						//System.out.println(words[j] + " two trop error" + " \"" + basicLists.translate(now.substring(0, 1)) + " " + basicLists.translate(now.substring(1, 2)) + " *" + "\"");
					}
					
				}
				else {
					trope.add(words[j] + " error, more than two trop");
				}
			}
			else{
				
			}
		}
		if(!three){
			int tropelen = trope.size();
			for(int i = 0; i < tropelen; i++) {
				if(trope.get(i).equals("munach-pesik")) {
					//System.out.println(trope.get(i - 1) + " " + trope.get(i) + " " + trope.get(i + 1));
					if(i < tropelen - 2){
						if(trope.get(i + 1).contains("revie") || trope.get(i + 2).equals("revie") || (trope.get(i + 1).equals("munach-pesik") && (trope.get(i + 1).contains("revie") || trope.get(i + 2).equals("revie") || trope.get(i + 3).contains("revie"))) || (trope.get(i + 3).equals("revie") && trope.get(i + 1).equals("dargah"))) {
							trope.set(i, "legarmeh");
						}
						else{
							trope.set(i, "munach");
						}
					}
					else{
						trope.set(i, "munach");
					}
				}
			}
		}
		return tools.ArraylistToArray(trope);
	}
 */

}