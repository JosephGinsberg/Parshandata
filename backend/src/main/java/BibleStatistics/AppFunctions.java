package BibleStatistics;

import java.util.ArrayList;
import org.json.*;
import BibleStatistics.Level_1.*;
import BibleStatistics.Level_2.tropLists;

public class AppFunctions {

	public static ArrayList<Integer> logics(String operation, ArrayList<Integer> one, ArrayList<Integer> two){
		ArrayList<Integer> result = new ArrayList<Integer>();
		Boolean notempty = true;
		int onesize = one.size();
		int twosize = two.size();
		if(operation.equals("and")){
			if(onesize == 0 || twosize == 0){
				notempty = false;
			}
		}
		else if(operation.equals("or")){
			if(onesize == 0 && twosize == 0){
				notempty = false;
			}
		}
		if(notempty){
			result.addAll(one);
			for(Integer s : two){
				if(!result.contains(s)){
					result.add(s);
				}
			}
		}
		return result;
	}

	public static ArrayList<Integer> distance(JSONObject input, ArrayList<Integer> one, ArrayList<Integer> two) throws JSONException{
		String[] values = {"type", "count", "distancetype"};
		AppFunctions.errorChecker(values, input);
		String operation = input.getString(values[0]);
		int distance = input.getInt(values[1]);
		String type = input.getString(values[2]);
		ArrayList<Integer> result = new ArrayList<Integer>();
		int onesize = one.size();
		int twosize = two.size();
			for(int i = 0; i < onesize; i++){
				int thisone = one.get(i);
				for(int j = 0; j < twosize; j++){
					int thistwo = two.get(j);
					if(operation.equals("before") && type.equals("greater")){
						if((thistwo - thisone > distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("before") && type.equals("equal")){
						if((thistwo - thisone == distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("before") && type.equals("less")){
						if((thistwo - thisone < distance) && !(thistwo - thisone < 0)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("after") && type.equals("greater")){
						if((thisone - thistwo > distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("after") && type.equals("equal")){
						if((thisone - thistwo == distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("after") && type.equals("less")){
						if((thisone - thistwo < distance) && !(thistwo - thisone < 0)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
				}
			}
			/*if(!(result.size() == one.size() * two.size())){
				result.clear();
			}*/
		return result;
	}

	public static ArrayList<Integer> opposite(ArrayList<Integer> input){
		ArrayList<Integer> param = input;
		int paramlen = param.size();
		if(paramlen > 0){
			param.clear();
		}
		else{
			param.add(-1);
		}
		return param;
	}

	public static int nextFinder(JSONArray search, int currentindex, String value) throws JSONException{
		JSONObject current = search.getJSONObject(currentindex);
		String[] values = {"level"};
		AppFunctions.errorChecker(values, current);
		int thislevel = current.getInt("level");
		int len = search.length();
		int i = 0;
		for(i = currentindex + 1; i < len; i++){
			JSONObject nextobject = search.getJSONObject(i);
			AppFunctions.errorChecker(values, nextobject);
			int ilevel = nextobject.getInt("level");
			if(thislevel == ilevel && (nextobject.getString("param").equals(value) || value.equals("any"))){
				return i;
			}
		}
		return i;
	}

	public static JSONArray removeSet(String term) throws JSONException{
		JSONArray returning = new JSONArray();
		/*if(term.length() == 1){
			for(int i = 0; i <= 5; i++){
				returning.put(i, true);
			}
			return returning;
		}*/
		for(int i = 0; i <= 5; i++){
			returning.put(i, false);
		}
		int len = term.length();
		for(int i = 0; i < len; i++){
			String now = term.substring(i, i + 1);
			if(returning.get(0).equals(false) && basicLists.isaletter(now)){
				returning.put(0, true);
			}
			else if(returning.get(1).equals(false) && basicLists.isanekudah(now)){
				returning.put(1, true);
			}
			else if(returning.get(2).equals(false) && basicLists.isatrop(now)){
				returning.put(2, true);
			}
			else if(returning.get(3).equals(false) && basicLists.isaother(now)){
				returning.put(3, true);
			}
			else if(returning.get(4).equals(false) && now.equals("space")){
				returning.put(4, true);
			}
			else if(returning.get(5).equals(false) && now.equals("makaf")){
				returning.put(5, true);
			}
		}
		return returning;
	}

	/*public static ArrayList<Integer> abstractFinder(String[] newterms, String val, String matchtype, String[] trop){
		int newlen = newterms.length;
		if(matchtype.equals("contains") || matchtype.equals("does not contain")){
			for(int j = 0; j < newlen; j++){
				String[] newtrop = {nexttrop[j]};
				if(searching(search, index + 1, newterms[j], place, newtrop).size() != 0){
					indexlist.add(j);
				}
			}
			if(matchtype.equals("does not contain")){
				indexlist = opposite(indexlist);
			}
		}
		else if(matchtype.equals("begins")){
			if(term.substring(0, vallen).equals(val)){
				indexlist.add(0);
			}
		}
		else if(matchtype.equals("ends")){
			//System.out.println(basicLists.translate(term.substring(pos, termlen)));
			if(term.substring(pos, termlen).equals(val)){
				indexlist.add(pos);
			}
		}
	}*/

	public static ArrayList<Integer> finder(String term, String val, String type, String matchtype, String[] trop){
		ArrayList<Integer> indexlist = new ArrayList<Integer>();
		int termlen = term.length();
		int vallen = val.length();
		if(!type.equals("trop")){
			int pos = termlen - vallen;
			if(matchtype.equals("contains") || matchtype.equals("does not contain")){
				for(int i = 0; i <= pos; i++){
					if(term.substring(i, i + vallen).equals(val)){
						indexlist.add(i);
					}
				}
				if(matchtype.equals("does not contain")){
					indexlist = opposite(indexlist);
				}
			}
			else if(matchtype.equals("is") || matchtype.equals("is not")){
				if(term.equals(val)){
					indexlist.add(0);
				}
				if(matchtype.equals("is not")){
					indexlist = opposite(indexlist);
				}
			}
			else if(matchtype.equals("begins")){
				if(term.substring(0, vallen).equals(val)){
					indexlist.add(0);
				}
			}
			else if(matchtype.equals("ends")){
				//System.out.println(basicLists.translate(term.substring(pos, termlen)));
				if(term.substring(pos, termlen).equals(val)){
					indexlist.add(pos);
				}
			}
		}
		else{
			int troplen = trop.length;
			if(matchtype.equals("contains") || matchtype.equals("does not contain")){
				for(int i = 0; i < troplen; i++){
					if(trop[i].contains(val)){
						indexlist.add(i);
					}
				}
				if(matchtype.equals("does not contain")){
					indexlist = opposite(indexlist);
				}
			}
			else if(matchtype.equals("is") || matchtype.equals("is not")){
				for(int i = 0; i < troplen; i++){
					if(trop[i].equals(val)){
						indexlist.add(i);
					}
				}
				if(matchtype.equals("is not")){
					indexlist = opposite(indexlist);
				}
			} 
			else if(matchtype.equals("begins")){
				if(trop[0].contains(val)){
					indexlist.add(0);
				}
			}
			else if(matchtype.equals("ends")){
				//System.out.println(basicLists.translate(term.substring(pos, termlen)));
				if(trop[troplen].contains(val)){
					indexlist.add(troplen);
				}
			}	
		}
		return indexlist;
	}

	public static ArrayList<Integer> countchecker(ArrayList<Integer> input, int totalcount, String counttype){
		ArrayList<Integer> output = input;
		int listlen = output.size();
			if(counttype.equals("equal")){
				if(!(listlen == totalcount)){
					output.clear();
				}
			}
			else if(counttype.equals("greater")){
				if(!(listlen > totalcount)){
					output.clear();
				}
			}
			else if(counttype.equals("less")){
				if(!(listlen < totalcount)){
					output.clear();
				}
			}
		return output;
	}

	public static void errorChecker(String[] values, JSONObject json) throws JSONException{
		for(String v : values){
			if(!json.has(v)){
				throw new JSONException("Input JSON is missing the \"" + v + "\" key.");
			}
		}
	}

	public static String[][] splitter(String type, String term, String[] place){
		String[] newterms = {};
		if(type.equals("word")) {
			newterms = bibleLists.words(term);
			String[] next = tropLists.tropFinder(newterms, place[0], Integer.parseInt(place[1]), Integer.parseInt(place[2]));
			String[][] result = {newterms, next};
			return result;
		}
		else if(type.equals("letter")) {
			newterms = bibleLists.separateLetters(term);
			String[] next = tropLists.tropFinder(newterms, place[0], Integer.parseInt(place[1]), Integer.parseInt(place[2]));
			String[][] result = {newterms, next};
			return result;
		}
		else if(type.equals("tropword")) {
			newterms = bibleLists.tropWords(term);
			String[] trop = tropLists.tropFinder(newterms, place[0], Integer.parseInt(place[1]), Integer.parseInt(place[2]));
			String[][] result = {newterms, trop};
			return result;
		}
		else{
			newterms = bibleLists.tropWords(term);
			String[] trop = tropLists.tropFinder(newterms, place[0], Integer.parseInt(place[1]), Integer.parseInt(place[2]));
			String[][] result = {{term}, trop};
			return result;
		}
		/*int count = 0;
		ArrayList<String> nexttrop = new ArrayList<String>();
		Boolean doit = false;
		for(int i = 0; i < newterms.length; i++){
			String thisterm = otherBibles.justTrop(newterms[i]);
			int len = thisterm.length();
			int size = nexttrop.size() - 1;
			String nexttime = "none";
			if(len == 0){
				nexttrop.add("none");
				System.out.println(nexttrop.get(size + 1));
			}
			else if(doit){
				nexttrop.add(nexttime);
				doit = false;
			}
			else if((type.equals("letter") || type.equals("word")) && size > -1){
				String prev = trop[count];
				if(prev.contains("-")){
					System.out.print("found");
					String[] split = prev.split("-");
					nexttrop.add(split[0]);
					nexttime = split[1];
					doit = true;
				}
				else if(prev.contains("*")){
					String[] split = prev.split("*");
					nexttrop.add(split[0]);
					nexttime = split[1];
					doit = true;
				}
				else if(prev.contains("2")){
					String one = prev.substring(0, prev.length() - 1);
					nexttrop.add(one);
					nexttime = one;
					doit = true;
				}
				else{
					String next = trop[count];
					if(next.equals("legarmeh")){
						next = "munach";
					}
					nexttrop.add(next);
					count++;
				}
				System.out.println(nexttrop.get(size + 1));
			}
			else{
				nexttrop.add(trop[count]);
				count++;
				System.out.println(nexttrop.get(size + 1));
			}
		}
		String[][] result = {newterms, tools.ArraylistToArray(nexttrop)};
		return result;*/
	}
}
