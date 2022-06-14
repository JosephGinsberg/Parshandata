package Level_1;

import java.util.ArrayList;

public class searcher {

	//Returns an array of all the members of the input array that contain the given input phrase anywhere within their contents.
    public static String[] searchPhrase(String[] input, String search){
    	ArrayList<String> answer = new ArrayList<String>();
    	int len = input.length;
    	int searchlen = search.length();
        for(int i = 0; i < len; i++){
        	String now = input[i];
        	int nowlen = now.length();
        	for(int j = 0; j <= (nowlen - searchlen); j++){
        		if(now.substring(j , j + searchlen).equals(search)){
        			answer.add(now);
        		}
        	}
        }
        String[] answers = new String[answer.size()];
		int i = 0;
		for(String j : answer){
			answers[i] = j;
			i++;
		}
    	return answers;
    }
    
    //Returns an array of all the members of the input array that contains all the given input phrases anywhere within their contents.
    //could be more efficient, replace with boolean so it breaks immediately on hitting one false
    public static String[] SearchPhrases(String[] input, String[] search){
    	ArrayList<String> answer = new ArrayList<String>();
    	int count = 0;
    	int len = input.length;
    	int searchlen = search.length;
        for(int i = 0; i < len; i++){
        	String now = input[i];
        	int nowlen = now.length();
        	for(int j = 0; j < searchlen; j++){
        		String searching = search[j];
        		int searchinglen = searching.length();
        		int klen = nowlen - searchinglen;
        		for(int k = 0; k <= klen; k++) {
        			if(now.substring(k , k + searchinglen).equals(searching)){
            			count++;
            			break;
            		}
        		}
        	}
        	if(count == searchlen){
        		answer.add(now);
        	}
        	count = 0;	
        }
        String[] answers = new String[answer.size()];
		int i = 0;
		for(String j : answer){
			answers[i] = j;
			i++;
		}
    	return answers;
    }
    
    //Returns an array of all the Pasukim that do not contain the given input phrase anywhere within their contents.
    public static String[] searchNotPhrase(String[] input, String search){
    	ArrayList<String> answer = new ArrayList<String>();
    	boolean has = false;
    	int len = input.length;
    	int searchlen = search.length();
        for(int i = 0; i < len; i++){
        	String now = input[i];
        	int nowlen = now.length();
        	for(int j = 0; j <= (nowlen - searchlen); j++){
        		if(now.substring(j , j + searchlen).equals(search)){
        			has = true;
        		}
        	}
        	if(!has){
        		answer.add(now);
        	}
        	has = false;
        }
        String[] answers = new String[answer.size()];
		int i = 0;
		for(String j : answer){
			answers[i] = j;
			i++;
		}
    	return answers;
    }
    
    //we need a search any, besides for the search all
    
    //All this can just be replaced with going through the first two methods and chooosing the ones where it doesn't work.
	 
    //Returns an array of all the Pasukim that do not contain all the given input phrases anywhere within their contents.
    public static String[] SearchNotPhrases(String bible, String[] search){
    	ArrayList<String> answer = new ArrayList<String>();
    	boolean has = false;
    	String[] Pasukim = bibleLists.pasukim(bible);
    	int len = bibleLists.pasukim(bible).length;
    	int query = search.length;
        for(int i = 0; i < len; i++){
        	String now = Pasukim[i];
        	for(int j = 0; j < query; j++){
        		String searching = search[j];
        		for(int k = 0; k <= (now.length() - searching.length()); k++) {
        			if(now.substring(k , k + searching.length()).equals(searching)){
        				has = true;
        				break;
        			}
        		}
        	}
        	if(!has){
        		answer.add(now);
        	}
        	has = false;
        }
        String[] answers = new String[answer.size()];
		int i = 0;
		for(String j : answer){
			answers[i] = j;
			i++;
		}
    	return answers;
    }
    
    //Starts at x position and skips y amount z times, and outputs an array of the words of the input string with those indexes.
    public static String[] wordAfterPlace(String bible, int start, int skip, int numTimes){
    	ArrayList<String> answer = new ArrayList<String>();
    	String[] words = bibleLists.words(bible);
    	int x = start;
    	for(int i = 0; i < numTimes; i++){
    		answer.add(words[x]);
    		if(x < words.length - skip) {
    			x += skip;
    		}
    		else {
    			break;
    		}
    	}
    	String[] answers = new String[answer.size()];
		int i = 0;
		for(String j : answer){
			answers[i] = j;
			i++;
		}
    	return answers;
    }
    
    //needs to be checked
    //Starts at the index position of the given input word in the array of the words of the input string, skips y amount z times, and outputs an array of the words of the input string with those indexes.
    public static String[] wordAfterWord(String bible, String word, int skip, int numTimes){
    	ArrayList<String> answer = new ArrayList<String>();
    	String[] words = bibleLists.words(bible);
    	int x = 0;
    	for(int i = 0; i < words.length; i++){
    		if(words[i].equals(word)){
    			x = i;
    			for(int j = 0; j < numTimes; j++) {
    				answer.add(words[x]);
    				if(x < words.length - skip) {
    	    			x += skip;
    	    		}
    	    		else {
    	    			break;
    	    		}
    			}
    		}
    	}
    	String[] answers = new String[answer.size()];
		int i = 0;
		for(String j : answer){
			answers[i] = j;
			i++;
		}
    	return answers;
    }
    
    //check and optimize
    //Takes an input of a full Pasuk with no numbering, a list of books, and a list of their corresponding names, and outputs an array containing every instance of that Pasuk with full (Book, Perek, Pasuk) numbering.
  	public static String[] num(String frags, String[] books, String[] bookNames) {
  		int fraglen = frags.length();
  		ArrayList<String> answer = new ArrayList<String>();
  		int len = books.length;
  		for(int i = 0; i < len; i++) {
  			//System.out.println(frags);
  			String book = books[i];
  			int booklen = book.length();
  			int forward = 0;
  			int backward = 0;
  			for(int j = 0; j < booklen - fraglen; j++){
  				if(book.substring(j, j + fraglen).equals(frags/*[i]*/)) {
  					//System.out.println(frags);
  					forward = 0;
  					backward = 0;
  					for(int k = j - 1; k < booklen; k++) {
  						if(book.substring(k, k + 1).equals("׃")) {
  							forward = k + 1;
  							break;
  						}
  					}
  					for(int k = j; k > 0; k--) {
  						if(book.substring(k - 1, k).equals("׃")) {
  							backward = k - 6;
  							break;
  						}
  					}
  					answer.add(bookNames[i] + " " + book.substring(backward + 10, forward) + " |" + book.substring(backward - 1, backward + 10));
  					//answer.add(bookNames[i] + bible.substring(backward - 1, backward + 10));
  				}
  			}
  		}
  		String[] pasukim = new String[answer.size()];
  		int i = 0;
  		for(String j : answer){
  			pasukim[i] = j;
  			i++;
  		}
  		return pasukim;	
  	}
	
}
