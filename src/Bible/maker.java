package Bible;

import java.io.File;
import java.io.FileNotFoundException;

public class maker {

	// Original file path: /Users/yosefginsberg/Documents/Coding/Bible Text/...
	static File file1 = new File("Bible Text/Genesis.txt");
	static File file2 = new File("Bible Text/Exodus.txt");
	static File file3 = new File("Bible Text/Leviticus.txt");
	static File file4 = new File("Bible Text/Numbers.txt");
	static File file5 = new File("Bible Text/Deuteronomy.txt");
	static File file6 = new File("Bible Text/Joshua.txt");
	static File file7 = new File("Bible Text/Judges.txt");
	static File file8 = new File("Bible Text/Samuel_1.txt");
	static File file9 = new File("Bible Text/Samuel_2.txt");
	static File file10 = new File("Bible Text/Kings_1.txt");
	static File file11 = new File("Bible Text/Kings_2.txt");
	static File file12 = new File("Bible Text/Isaiah.txt");
	static File file13 = new File("Bible Text/Jeremiah.txt");
	static File file14 = new File("Bible Text/Ezekiel.txt");
	static File file15 = new File("Bible Text/Hosea.txt");
	static File file16 = new File("Bible Text/Joel.txt");
	static File file17 = new File("Bible Text/Amos.txt");
	static File file18 = new File("Bible Text/Obadiah.txt");
	static File file19 = new File("Bible Text/Jonah.txt");
	static File file20 = new File("Bible Text/Micah.txt");
	static File file21 = new File("Bible Text/Nahum.txt");
	static File file22 = new File("Bible Text/Habakkuk.txt");
	static File file23 = new File("Bible Text/Zephaniah.txt");
	static File file24 = new File("Bible Text/Haggai.txt");
	static File file25 = new File("Bible Text/Zechariah.txt");
	static File file26 = new File("Bible Text/Malachi.txt");
	static File file27 = new File("Bible Text/Ruth.txt");
	static File file28 = new File("Bible Text/Esther.txt");
	static File file29 = new File("Bible Text/Song_of_Songs.txt");
	static File file30 = new File("Bible Text/Lamentations.txt");
	static File file31 = new File("Bible Text/Ecclesiastes.txt");
	static File file32 = new File("Bible Text/Daniel.txt");
	static File file33 = new File("Bible Text/Ezra.txt");
	static File file34 = new File("Bible Text/Nehemiah.txt");
	static File file35 = new File("Bible Text/Chronicles_1.txt");
	static File file36 = new File("Bible Text/Chronicles_2.txt");
	
	static File file37 = new File("Bible Text/Psalms.txt");
	static File file38 = new File("Bible Text/Job.txt");
	static File file39 = new File("Bible Text/Proverbs.txt");

	public static String book(String book) throws FileNotFoundException {
		File file = new File("Bible Text/" + book + ".txt");
		return leningrad.bibleMaker(file);
	}
	
	public static String torah() throws FileNotFoundException {
		return leningrad.bibleMaker(file1) + leningrad.bibleMaker(file2) + leningrad.bibleMaker(file3) + leningrad.bibleMaker(file4) + leningrad.bibleMaker(file5);
	}
	
	public static String twentyOne() throws FileNotFoundException {
		return leningrad.bibleMaker(file1) + leningrad.bibleMaker(file2) + leningrad.bibleMaker(file3) + leningrad.bibleMaker(file4) + leningrad.bibleMaker(file5) + leningrad.bibleMaker(file6)
		 + leningrad.bibleMaker(file7) + leningrad.bibleMaker(file8) + leningrad.bibleMaker(file9) + leningrad.bibleMaker(file10) + leningrad.bibleMaker(file11) + leningrad.bibleMaker(file12)
		 + leningrad.bibleMaker(file13) + leningrad.bibleMaker(file14) + leningrad.bibleMaker(file15) + leningrad.bibleMaker(file16) + leningrad.bibleMaker(file17) + leningrad.bibleMaker(file18)
		 + leningrad.bibleMaker(file19) + leningrad.bibleMaker(file20) + leningrad.bibleMaker(file21) + leningrad.bibleMaker(file22) + leningrad.bibleMaker(file23) + leningrad.bibleMaker(file24)
		 + leningrad.bibleMaker(file25) + leningrad.bibleMaker(file26) + leningrad.bibleMaker(file27) + leningrad.bibleMaker(file28) + leningrad.bibleMaker(file29) + leningrad.bibleMaker(file30)
		 + leningrad.bibleMaker(file31) + leningrad.bibleMaker(file32) + leningrad.bibleMaker(file33) + leningrad.bibleMaker(file34) + leningrad.bibleMaker(file35) + leningrad.bibleMaker(file36);
	}
	
	public static String three() throws FileNotFoundException {
		return leningrad.bibleMaker(file37) + leningrad.bibleMaker(file38) + leningrad.bibleMaker(file39);
	}
	
	public static String all() throws FileNotFoundException {
		return leningrad.bibleMaker(file1) + leningrad.bibleMaker(file2) + leningrad.bibleMaker(file3) + leningrad.bibleMaker(file4) + leningrad.bibleMaker(file5) + leningrad.bibleMaker(file6)
		 + leningrad.bibleMaker(file7) + leningrad.bibleMaker(file8) + leningrad.bibleMaker(file9) + leningrad.bibleMaker(file10) + leningrad.bibleMaker(file11) + leningrad.bibleMaker(file12)
		 + leningrad.bibleMaker(file13) + leningrad.bibleMaker(file14) + leningrad.bibleMaker(file15) + leningrad.bibleMaker(file16) + leningrad.bibleMaker(file17) + leningrad.bibleMaker(file18)
		 + leningrad.bibleMaker(file19) + leningrad.bibleMaker(file20) + leningrad.bibleMaker(file21) + leningrad.bibleMaker(file22) + leningrad.bibleMaker(file23) + leningrad.bibleMaker(file24)
		 + leningrad.bibleMaker(file25) + leningrad.bibleMaker(file26) + leningrad.bibleMaker(file27) + leningrad.bibleMaker(file28) + leningrad.bibleMaker(file29) + leningrad.bibleMaker(file30)
		 + leningrad.bibleMaker(file31) + leningrad.bibleMaker(file32) + leningrad.bibleMaker(file33) + leningrad.bibleMaker(file34) + leningrad.bibleMaker(file35) + leningrad.bibleMaker(file36)
		 + leningrad.bibleMaker(file37) + leningrad.bibleMaker(file38) + leningrad.bibleMaker(file39);
	}
	
	public static String[] bookNames(String type) {
		if(type.equals("torah")) {
			String[] books = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy"};
			return books;
		}
		else if(type.equals("twentyOne")) {
			String[] books = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Samuel_1", "Samuel_2", "Kings_1", "Kings_2",
				"Isaiah", "Jeremiah", "Ezekiel", "Hosea", "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
				"Ruth", "Esther", "Song_of_Songs", "Lamentations", "Ecclesiastes", "Daniel", "Ezra", "Nehemiah", "Chronicles_1", "Chronicles_2"};
			return books;
		}
		else if(type.equals("three")) {
			String[] books = {"Psalms", "Job", "Proverbs"};
			return books;
		}
		else {
			String[] books = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Samuel_1", "Samuel_2", "Kings_1", "Kings_2",
					"Isaiah", "Jeremiah", "Ezekiel", "Hosea", "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
					"Ruth", "Esther", "Song_of_Songs", "Lamentations", "Ecclesiastes", "Daniel", "Ezra", "Nehemiah", "Chronicles_1", "Chronicles_2", "Psalms", "Job", "Proverbs"};
			return books;
		}
	}
	
	public static String[] book_numbers(String type) throws FileNotFoundException {
		if(type.equals("torah")) {
			String[] books = {(leningrad.standard(file1)), (leningrad.standard(file2)), (leningrad.standard(file3)), (leningrad.standard(file4)), (leningrad.standard(file5))};
			return books;
		}
		else if(type.equals("twentyOne")) {
			String[] books = {(leningrad.standard(file1)), (leningrad.standard(file2)), (leningrad.standard(file3)), (leningrad.standard(file4)), (leningrad.standard(file5)), (leningrad.standard(file6)),
					(leningrad.standard(file7)), (leningrad.standard(file8)), (leningrad.standard(file9)), (leningrad.standard(file10)), (leningrad.standard(file11)), (leningrad.standard(file12)),
					(leningrad.standard(file13)), (leningrad.standard(file14)), (leningrad.standard(file15)), (leningrad.standard(file16)), (leningrad.standard(file17)), (leningrad.standard(file18)),
					(leningrad.standard(file19)), (leningrad.standard(file20)), (leningrad.standard(file21)), (leningrad.standard(file22)), (leningrad.standard(file23)), (leningrad.standard(file24)),
					(leningrad.standard(file25)), (leningrad.standard(file26)), (leningrad.standard(file27)), (leningrad.standard(file28)), (leningrad.standard(file29)), (leningrad.standard(file30)),
					(leningrad.standard(file31)), (leningrad.standard(file32)), (leningrad.standard(file33)), (leningrad.standard(file34)), (leningrad.standard(file35)), (leningrad.standard(file36))};
			return books;
		}
		else if(type.equals("three")) {
			String[] books = {(leningrad.standard(file37)), (leningrad.standard(file38)), (leningrad.standard(file39))};
			return books;
		}
		else if(type.equals("all")) {
			String[] books = {(leningrad.standard(file1)), (leningrad.standard(file2)), (leningrad.standard(file3)), (leningrad.standard(file4)), (leningrad.standard(file5)), (leningrad.standard(file6)),
					(leningrad.standard(file7)), (leningrad.standard(file8)), (leningrad.standard(file9)), (leningrad.standard(file10)), (leningrad.standard(file11)), (leningrad.standard(file12)),
					(leningrad.standard(file13)), (leningrad.standard(file14)), (leningrad.standard(file15)), (leningrad.standard(file16)), (leningrad.standard(file17)), (leningrad.standard(file18)),
					(leningrad.standard(file19)), (leningrad.standard(file20)), (leningrad.standard(file21)), (leningrad.standard(file22)), (leningrad.standard(file23)), (leningrad.standard(file24)),
					(leningrad.standard(file25)), (leningrad.standard(file26)), (leningrad.standard(file27)), (leningrad.standard(file28)), (leningrad.standard(file29)), (leningrad.standard(file30)),
					(leningrad.standard(file31)), (leningrad.standard(file32)), (leningrad.standard(file33)), (leningrad.standard(file34)), (leningrad.standard(file35)), (leningrad.standard(file36)),
					(leningrad.standard(file37)), (leningrad.standard(file38)), (leningrad.standard(file39))};
			return books;
		}
		else {
			File file = new File("Bible Text/" + type + ".txt");
			String[] books = {leningrad.standard(file)};
			return books;
		}
	}

	public static String[][] bookswithnames(String[] books) throws FileNotFoundException {
		int bookslen = books.length;
		String[][] returning = new String[bookslen][2];
		for(int i = 0; i < bookslen; i++){
			File file = new File("Bible Text/" + books[i] + ".txt");
			returning[i][0] = leningrad.standard(file);
			returning[i][1] = books[i];
		}
		return returning;
	}
}
