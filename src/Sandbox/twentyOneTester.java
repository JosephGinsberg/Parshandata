package Sandbox;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Bible.keriUkesiv;
import Bible.leningrad;
import Bible.maker;
import Level_1.basicLists;
import Level_1.bibleLists;
import Level_1.otherBibles;
import Level_1.tools;
import Level_2.tropLists;


public class twentyOneTester {
	
	
	
	public static void main(String[] args) throws IOException{

		//String l = leningrad.specialCharacter();
		/*String[] x = bibleLists.tropWords("תְּנוּ־לָ֭הּ מִפְּרִ֣י יָדֶ֑יהָ וִֽיהַלְל֖וּהָ בַשְּׁעָרִ֣ים מַעֲשֶֽׂיהָ׃");
		tools.printArray(Level_2.tropLists.tropFinder(x));*/
		String book = maker.book("Numbers");
		book = keriUkesiv.tachton(book, true);
		book = keriUkesiv.keri(book);
		//String[][] x = bibleLists.numseperator(bibleLists.numpasukim(book), "Numbers");
		//String s = book;
		String[] p = bibleLists.pasukim(book);
		String s = "";
		for(int j = 0; j < p.length; j++){
			String[] v = tropLists.tropFinder(bibleLists.tropWords(p[j]));
			for(int i = 0; i < v.length - 1; i++){
				if(v[i].equals("legarmeh") /*&& v[i + 1].equals("revie")*/){
					s += p[j] + "\n";
				}
			}
		}
		//book = book.replace("‬", "").replaceAll("‫", "");
		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(s.getBytes("UTF-8"));
		out.close();
		/*String[][] x = bibleLists.numseperator(bibleLists.numpasukim(maker.book_numbers("Chronicles_2")[0]));
		String l = "";
		for(int i = 0; i < x.length; i++){
			l += "verse:" + x[i][0] + "perek:" + x[i][1] + "verse:" + x[i][2] + "\n";
		}*/
		/*FileOutputStream out = new FileOutputStream("output.txt");
		out.write(l.getBytes("UTF-8"));
		out.close();*/
		
		
		//System.out.println(tropLists.tropList(" וְכַאֲשֶׁ֤ר נָשָׂ֙אתָה֙ לָעָ֣ם הַזֶּ֔ה מִמִּצְרַ֖יִם וְעַד־הֵֽנָּה׃")[0]);
		/*String[] books = maker.book_numbers("all");
		String[] bookNames = maker.bookNames("all");
		String all = keriUkesiv.keri(maker.all());*/
		//String y = otherBibles.removeCheck(all);
		//String x = "";
		/*for(int i = 0; i < y.length(); i++){
			String now = y.substring(i, i + 1);
			x += "\"" + now + "\"" + "\n";
			y = y.replaceAll(now, "");
		}*/
		//x = tools.spacer(x," ");
		//x = otherBibles.noSpaces(" ֘");
		/*String[] ser = Level_1.searcher.searchPhrase(bibleLists.pasukim(all), "‬");
		for(int i = 0; i < ser.length; i++){
			x += ser[i] + "\n";
		}
		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(x.getBytes("UTF-8"));
		out.close();*/



		
		/*System.out.println(twentyOne.substring(300000, 310000));
		String[] ser = searcher.searchPhrase(twentyOne, "שארות ופ");
		tools.printArray(ser);*/
		/*String[] trop = bibleLists.tropWords(twentyOne, false);
		int len = trop.length;
		for(int i = 0; i < len; i++){
			String now = trop[i];
			if(now.contains("֨") && now.contains("֔") && now.contains("וּ")) {
				System.out.println(now);
			}
		}*/
		
		
		
		//File file28 = new File("/Users/josephginsberg/Documents/Torah/Bible Text/Deuteronomy.txt");
		//System.out.print(keriUkesiv.keri(keriUkesiv.tachton(leningrad.standard(file28), true)).substring(0, 12));
		/*File file28 = new File("/Users/josephginsberg/Documents/mt-st-ss.txt.txt");
		String bible = keriUkesiv.keri(keriUkesiv.tachton(leningrad.standard(file28), true));
		tools.printArray(bibleLists.tropWords(" וְכַאֲשֶׁ֤ר נָשָׂ֙אתָה֙ לָעָ֣ם הַזֶּ֔ה מִמִּצְרַ֖יִם וְעַד־הֵֽנָּה׃", true));
		tools.printArray(tropLists.tropList(" וְכַאֲשֶׁ֤ר נָשָׂ֙אתָה֙ לָעָ֣ם הַזֶּ֔ה מִמִּצְרַ֖יִם וְעַד־הֵֽנָּה׃"));
		tools.printArray(tropLists.tropList("וְעַד־הֵֽנָּה׃"));
		
		FileOutputStream out = new FileOutputStream("test3.txt");
		out.write(bible.getBytes());
		out.close();*/
		
		/*File file28 = new File("/Users/josephginsberg/Documents/Samaritan.txt");
		Scanner sc = new Scanner(file28);
		
	    ArrayList<String> Final = new ArrayList<String>();
	    String z = "";
	    while (sc.hasNextLine()){
	    	String here = sc.nextLine();
	    	here = " " + here + "׃";
	    	String[] words = bibleLists.words(here);
	    	int len = words.length;
	    	String x = "";
	    	for(int i = 0; i < len; i++) {
	    		String now = words[i]; 
	    		if(!now.equals("") && !now.equals(" ")) {
		    		if(now.substring(0, 0 + 1).equals("[")) {
						x += now.substring(1, now.length() - 1) + " ";
	    			}
		    		else if(!now.substring(0, 0 + 1).equals("<")) { 
	    				if(i != len - 2) {
	    					//System.out.println(i + " " + now);
	    					if(!words[i + 1].substring(0, 0 + 1).equals("[")) {
	    						x += now + " ";
	    					}
	    				}
	    				else {
	    					x += now + " ";
    					}
	    			}
	    		}
	    	}
	    	if(x.length() != 0) {
	    	x = x.substring(0, x.length() - 1);
	    	x += "׃";
	    	x += " ";
	    	//Final.add(x);
	    		z += x;
	    	}
	    	/*int len = here.length();
	    	String Pasuk = "";
	    	for(int i = 0; i < len; i++) {
	    		String now = here.substring(i, i + 1);
	    		if((now.equals("א") || now.equals("ב") || now.equals("ג") || now.equals("ד") || now.equals("ה")
				|| now.equals("ו") || now.equals("ז") || now.equals("ח") || now.equals("ט") || now.equals("י")
				|| now.equals("כ") || now.equals("ל") || now.equals("מ") || now.equals("נ") || now.equals("ס")
				|| now.equals("ע") || now.equals("פ") || now.equals("צ") || now.equals("ק") || now.equals("ר")
				|| now.equals("ש") || now.equals("ת") || now.equals("ם") || now.equals("ן") || now.equals("ץ")
				|| now.equals("ף") || now.equals("ך"))){
	    			Pasuk += now;
	    		}
	    		if(i != len - 1) {
		    		if(here.substring(i, i + 2).equals("T>")){
		    			Pasuk += " ";
		    		}
		    		if(here.substring(i, i + 2).equals("Q>")){
		    			Pasuk += " <";
		    		}
		    		if(here.substring(i, i + 2).equals("q>")){
		    			Pasuk += "> ";
		    		}
		    		if(here.substring(i, i + 2).equals("M>")){
		    			Pasuk += " [";
		    		}
		    		if(here.substring(i, i + 2).equals("m>")){
		    			Pasuk += "] ";
		    		}
	    		}
	    	}
	    	int len2 = Pasuk.length();
	    	for(int i = 0; i < len2 - 1; i++) {
	    		if(Pasuk.substring(i, i + 1).equals(" ") && Pasuk.substring(i + 1, i + 2).equals(" ")) {
	    			Pasuk = Pasuk.substring(0, i + 1) + Pasuk.substring(i + 2);
	    		}
	    	}
	    	Final.add(Pasuk);*/
	    /*}
		sc.close();
		String[] words2 = bibleLists.pasukim(z);
    	int lenz = words2.length;
    	System.out.println(lenz);
    	for(int i = (lenz / 2) - 10; i < (lenz / 2) + 10; i++) {
    		if(i == lenz / 2) {
    			System.out.println();
    			System.out.println(words2[i] + " ");
    		}
    		else {
    			System.out.print(words2[i] + " ");
    		}
    	}*/
		/*String Data = "";
		for(int i = 0; i < Final.size(); i++) {
			Data += Final.get(i);
			Data += "\n";
		}
		sc.close();
		FileOutputStream out = new FileOutputStream("Samaritan2.txt");
		out.write(Data.getBytes());
		out.close();*/
		/*String[] words = bibleLists.words(twentyOne);
		int len = words.length;
		for(int i = 0; i < len - 1; i++) {
			words[i] = words[i] + " ";
			if(words[i].substring(0, 1).equals("מ")) {
				//System.out.println(words[i]);
				//tools.printArray(tropLists.tropList(words[i]));
				if(tropLists.tropList(words[i]).length > 0) {
					if(tropLists.tropList(words[i])[0].equals("munach")) {
						//System.out.println(words[i]);
						words[i + 1] = words[i + 1] + " ";
						if(words[i + 1].substring(0, 1).equals("ע")) {
							if(tropLists.tropList(words[i + 1]).length > 0) {
								if(tropLists.tropList(words[i + 1])[0].equals("zakaif")) {
									System.out.println(words[i] + " " + words[i + 1]);
								}
							}
						}
						
					}
				}
			}
		}*/
		/*String answer = "";
		int[] list = new int[173];
		String[] list2 = new String[173];
		int count = 0;
		String[] words = bibleLists.words(twentyOne);
		int len = words.length;
		for(int i = 0; i < 173; i++) {
			int border = i * 1766;
			list2[i] = words[border + 2] + " " + words[border + 3] + " " + words[border + 4];
			for(int j = border; j < (border + 1766); j++) {
					if(words[j].contains("*")) {
						list[i]++;
				}
			}
		}
		for(int i = 0; i < 173; i++) {
			answer += list[i] + " | " + i + " >";
			String[] all = 	searcher.num(list2[i], books, bookNames);
			for(int j = 0; j < all.length; j++) {		
				answer += " " + all[j];
			}
			answer += "\n";
			answer += "\n";
		}*/
		
		/*FileOutputStream out = new FileOutputStream("test3.txt");
		out.write(answer.getBytes());
		out.close();*/
		
		
		/*String[] books = maker.books("twentyOne");
		String bible = "";
		int len = books.length;
		for(int i = 0; i < len; i++) {
			bible += books[i];
		}
		String[] ser = searcher.searchPhrase(bible, "יוֹתָ֛ם אָחָ֥ז יְחִזְקִיָּ֖ה");
		printArray(ser);*/
		//tools.printArray(searcher.num("וַיְדַבֵּ֥ר", books, bookNames));
		/*for(int i = 0; i < ser.length; i++) {
			String[] p = numFinder.nums(otherBibles.justLetters(ser[i]), books, bookNames);
			for(int j = 0; j < p.length; j++) {
				System.out.println(p[j]);
			}
		}*/
		
		//testing search phrase
		/*String[] query = ciphers.organs()[0];
		String[] searchNot = searcher.SearchNotPhrases(twentyOne, query);
		for(int i = 0; i < searchNot.length; i++) {
			String[] f = searcher.num(searchNot[i], books, bookNames);
			for(int j = 0; j < f.length; j++) {
				System.out.println(f[j]);
			}
		}*/
		
		//String[] stats = nekudotLists.nekudotList(twentyOne);
		
		
		
		//average test
		//System.out.println(bibleStatistics.average(bibleLists.pasukim(twentyOne), "֗"));
		
		
	}
}
