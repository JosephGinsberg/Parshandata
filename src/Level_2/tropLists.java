package Level_2;

import java.util.ArrayList;

import Level_1.basicLists;
import Level_1.otherBibles;
import Level_1.tools;

public class tropLists {

	//creates a list of all the trop (per word) in the given input String
	public static String[] tropFinder(String[] words/*, String book*/){
		/*Boolean three = false;
		if(book.equals("Psalms") || book.equals("Job") || book.equals("Proverbs")){
			three = true;
		}*/
		ArrayList<String> trope = new ArrayList<String>();
		int wordslen = words.length;
		for(int j = 0; j < wordslen; j++){
			String now = otherBibles.justTrop(words[j]);
			//System.out.println(now);
			if(now.length() == 0) {
				trope.add("none");
			}
			else if(now.length() == 1) {
				trope.add(basicLists.translate(now));
			}
			else if(now.length() == 2) {
				if(now.contains("֨") && now.contains("֜")){
					trope.add("kadmah-veazlah");
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
				/*else if(now.contains("֓") && now.contains("׀")){
	    			trope.add("shalsheles gedolah"); 
	    		}*/
				else if(now.substring(0, 1).equals(now.substring(1, 2))){
					trope.add(basicLists.translate(now.substring(0, 1)));
	    		}
				else if(!now.substring(0, 1).equals("֣") && now.substring(1, 2).equals("׀")){
					trope.add(basicLists.translate(now.substring(0, 1)));
	    		}
				else {
	    			trope.add(basicLists.translate(now.substring(0, 1)) + " " + basicLists.translate(now.substring(1, 2)) + " * ");
	    			//System.out.println(words[j] + " two trop error");
	    		}
				
			}
			else {
				trope.add(words[j] + " error, more than two trop");
			}
		}
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
		return tools.ArraylistToArray(trope);
	}
}