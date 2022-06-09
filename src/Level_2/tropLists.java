package Level_2;

import java.util.ArrayList;

import Level_1.basicLists;
import Level_1.otherBibles;
import Level_1.tools;

public class tropLists {

	//creates a list of all the trop (per word) in the given input String
	public static String[] tropFinder(String[] words){
		ArrayList<String> trope = new ArrayList<String>();
		int wordslen = words.length;
		for(int j = 0; j < wordslen; j++){
			String now = otherBibles.justTrop(words[j]);
			//System.out.println(now);
			if(now.length() == 1) {
				trope.add(basicLists.translate(now));
			}
			else if(now.length() == 2) {
				if(now.contains("֨") && now.contains("֜")){
					trope.add("kadmah-veAzlah");
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
				else if(now.substring(0 , 1).equals(now.substring(1 , 2))){
					trope.add(basicLists.translate(now.substring(0 , 1)));
	    		}
				else if(!now.substring(0 , 1).equals("֣") && now.substring(1 , 2).equals("׀")){
					trope.add(basicLists.translate(now.substring(0 , 1)));
	    		}
				else {
	    			trope.add(tools.spacer(now, " ") + " * ");
	    			//System.out.println(words[j] + " two trop error");
	    		}
				
			}
			else if(now.length() == 0) {
				trope.add(words[j] + " error, zero trop");
			}
			else {
				trope.add(words[j] + " error, more than two trop");
			}
		}
		int tropelen = trope.size();
		for(int i = 0; i < tropelen; i++) {
			if(trope.get(i).equals("munach-pesik")) {
				if(trope.get(i + 1).equals("revie") || trope.get(i + 2).equals("revie") || trope.get(i + 1).equals("munach-pesik")) {
					trope.set(i, "legarmeh");
				}
				else {
					trope.set(i, "munach");
				}
			}
		}
		String[] trop = new String[trope.size()];
		int i = 0;
		for(String j : trope){
			trop[i] = j;
			i++;
		}
		return trop;
	}
	
		
}
