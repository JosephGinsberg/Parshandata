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
		String operation = input.getString("type");
		int distance = input.getInt("distance");
		String type = input.getString("distancetype");
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
		int thislevel = current.getInt("level");
		int len = search.length();
		int i = 0;
		for(i = currentindex + 1; i < len; i++){
			JSONObject nextobject = search.getJSONObject(i);
			int ilevel = nextobject.getInt("level");
			if(thislevel == ilevel && (nextobject.getString("param").equals(value) || value.equals("any"))){
				return i;
			}
		}
		return i;
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

	public static Boolean[] remover(String input){
		Boolean[] conditions = {false, false, false, false, false, false};
		int len = input.length();
		for(int i = 0; i < len; i++){
			if(!conditions[0] && basicLists.isaletter(input.substring(i, i + 1))){
				conditions[0] = true;
			}
			if(!conditions[1] && basicLists.isanekudah(input.substring(i, i + 1))){
				conditions[1] = true;
			}
			if(!conditions[2] && basicLists.isatrop(input.substring(i, i + 1))){
				conditions[2] = true;
			}
			if(!conditions[3] && basicLists.isaother(input.substring(i, i + 1))){
				conditions[3] = true;
			}
		}
		return conditions;
	}
}
