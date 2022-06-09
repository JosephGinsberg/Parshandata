package Sandbox;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Bible.maker;
import Level_1.bibleLists;
import Level_1.otherBibles;
import Level_1.searcher;
import Level_1.tools;

public class tester {

	public static void main(String[] args) throws IOException {
		String[] books = maker.book_numbers("all");
		String[] bookNames = maker.bookNames("all");
		//String bible = keriUkesiv.elyon(maker.book("Deuteronomy"));
		String bible = otherBibles.justLetters(maker.all());
		tools.printArray(searcher.searchPhrase(bibleLists.pasukim(bible), "מצרים"));
		/*FileOutputStream out = new FileOutputStream("test2.txt");
		out.write(bible.getBytes());
		out.close();*/
		///System.out.println(otherBibles.removeCheck(bible).substring(0, 1));
		//String x = otherBibles.justLetters(bible);
		//System.out.println(x.substring(x.length() / 2 - 50, x.length() / 2) + " * " + x.substring(x.length() / 2, x.length() / 2 + 50));
		//tools.printArray(searcher.num(otherBibles.removeCheck(bible).substring(0, 1), books, bookNames));
		
		/*String removed = otherBibles.removeCheck(bible);
		System.out.println(removed.length());
		System.out.println(removed);
		for(int i = 0; i < removed.length(); i++) {
			//System.out.println(i);
			String[] b = searcher.searchPhrase(bible, removed.substring(i, i + 1));
			for(int j = 0; j < b.length; j++) {
			System.out.println(b[j]);
				String[] c = searcher.num(b[j], books, bookNames);
				for(int k = 0; k < c.length; k++) {
					System.out.println(c[k]);
				}
			}
		}*/
		/*int len = bible.length();
		String[] letterList = bibleLists.letterList();
		int length = letterList.length;
		for(int i = 0; i < length; i++) {
			String now = letterList[i];
			int count = 0;
			for(int j = 0; j < len; j++) {
				if(bible.substring(j, j + 1).equals(now)) {
					count++;
				}
			}
			System.out.println(now + ": " + count);
		}*/
		
		/*String[] pasukim = bibleLists.pasukim(bible);
		int len = pasukim.length;
		for(int i = 0; i < len; i++) {
			String[] now = tropLists.tropList(pasukim[i]);
			if(mafteach.tier(now[0]) <= 2) {
				int length = now.length;
				for(int j = 0; j < length; j++) {
					if(now[j].equals("esnachta")) {
						for(int k = 1; k < j; k++) {
							if(mafteach.tier(now[k]) == 2 && (!now[k].equals("tipcha"))) {
								tools.printArray(searcher.num(pasukim[i], books, bookNames));
							}
						}
					}
				}
			}
		}*/
		
		
		
	}

}
