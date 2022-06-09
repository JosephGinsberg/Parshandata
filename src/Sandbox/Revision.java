package Sandbox;

import java.io.IOException;
import java.util.ArrayList;

import Bible.keriUkesiv;
import Bible.maker;
import Level_1.bibleLists;
import Level_1.otherBibles;
import Level_1.searcher;
import Level_1.tools;
import Level_2.tropLists;

public class Revision {
	public static void main(String[] args) throws IOException {
		
		//String bible = keriUkesiv.keri(keriUkesiv.tachton(maker.twentyOne(), false));
		String bible = maker.twentyOne();
		String[] books = maker.book_numbers("twentyOne");
		String[] names = maker.bookNames("twentyOne");
		//System.out.println(tools.spacer(bible.substring(0, 1000), " "));
		String[] pasukim = Level_1.bibleLists.pasukim(bible);
		int paslen  = pasukim.length;
		
		/*int count = 0;
		for(int i = 0; i < paslen; i++) {
			String now = pasukim[i];
			now = otherBibles.justLetters(now);
			if(!now.substring(0, 1).equals("ו")) {
				count++;
			}
		}
		System.out.println(count);*/
		
		//Find the disntance of pazer from esnachta
		//Doesn't work, will find only the distance of the pazer closest to the esnachta
		/*ArrayList<String> all = new ArrayList<String>();
		for(int i = 0; i < paslen; i++) {
			int paz = 0;
			int esn = 0;
			String words[] = bibleLists.tropWords(pasukim[i], true);
			int wordlen = words.length; 
			for(int j = 0; j < wordlen; j++) {
				String now = words[j];
				if(now.contains("֡")) {
					//paz = j + 1;
					//so it will catch pazer on the first word 
					//System.out.println(j);
					if(j >= 2) {
						if(words[j - 1].contains("֣") && !words[j - 1].contains("׀") && words[j - 2].contains("֣")) {
							if(!pasukim[i].contains("֑")) {
								all.add(pasukim[i]);
							}
							else {
								for(int k = 0; k < wordlen; k++) {
									String now2 = words[k];
									if(now2.contains("֑")) {
										if(k > j) {
											all.add(pasukim[i]);
										}
									}
								}
							}
						}
					}
				}
				
			}
		}
		String[] allarray = new String[all.size()];
		int i = 0;
		for(String j : all){
			allarray[i] = j;
			i++;
		}
		int len = allarray.length;
		for(int j = 0; j < len; j++) {
			tools.printArray(searcher.num(allarray[j], books, names));
		}
		System.out.println(len);*/
		//Level_1.tools.printArray(allarray);
		
		/*String[] words = Level_1.bibleLists.seperate(bible);
		Level_1.tools.printArrayLimit(words, 1000);*/
		
		
		//System.out.print(otherBibles.justTrop(bible.substring(0, 1000)));
	
		String[] answer = searcher.searchPhrase(pasukim, "֟");
		int len = answer.length;
		for(int i = 0; i < len; i++) {
			tools.printArray(searcher.num(answer[i], books, names));
		}
		//"אֵ֡ת"
		//"אֵ֠ת"
		//"אֵ֞ת"
		//"אֵ֜ת"
		
		//String x = "֡";
		//System.out.println(x.replace("\"", ""));
		
		/*String x = "אֵ֡ת";
		System.out.println(x.replace("֡", "֜"));*/
		
		//all the pesukim that contain x trop
		/*for(int i = 0; i < paslen; i++) {
			String[] tropwords = bibleLists.tropWords(pasukim[i], true);
			String[] trop = tropLists.tropFinder(tropwords);
			int troplen = trop.length;
			for(int j = 0; j < troplen; j++) {
				if(trop[j].contains("shalsheles")) {
					tools.printArray(searcher.num(pasukim[i], books, names));
					//System.out.println(tropwords[j]);
					System.out.println(tropwords[j] + " / From start: " + i + " / From end: " + (paslen - i));
				}
			}
		}*/
		
		/*String x = "וַיְהִ֛י בִּנְסֹ֥עַ הָאָרֹ֖ן וַיֹּ֣אמֶר מֹשֶׁ֑ה קוּמָ֣ה ׀ יְהוָ֗ה וְיָפֻ֙צוּ֙ אֹֽיְבֶ֔יךָ וְיָנֻ֥סוּ מְשַׂנְאֶ֖יךָ מִפָּנֶֽיךָ׃ וּבְנֻחֹ֖ה יֹאמַ֑ר שׁוּבָ֣ה יְהוָ֔ה רִֽבְב֖וֹת אַלְפֵ֥י יִשְׂרָאֵֽל";
		x = otherBibles.justLetters(x);
		System.out.println(x);
		System.out.println(x.length());*/
	
		//Find all pasukim that contain a word that has a letter with both a chataf and a gaya
		/*for(int k = 0; k < paslen; k++) {
			String[] words = Level_1.bibleLists.words(pasukim[k]);
			int wordslen = words.length;
			for(int i = 0; i < wordslen; i++) {
				String[] sep = bibleLists.separate(words[i]);
				int seplen = sep.length;
				for(int j = 0; j < seplen; j++) {
					if(sep[j].contains("ֲ") && sep[j].contains("ֽ")) {
						System.out.println(pasukim[k]);
					}
					if(sep[j].contains("ֳ") && sep[j].contains("ֽ")) {
						System.out.println(pasukim[k]);
					}
					if(sep[j].contains("ֱ") && sep[j].contains("ֽ")) {
						System.out.println(pasukim[k]);
					}
				}
			}
		}*/
		
	}
}
