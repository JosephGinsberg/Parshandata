package Level_2;

public class bibleStatistics {

	//returns the average length of the strings in the input array
	public static double aveLength(String[] pasukim){
		double average = 0.0;
		for(String pasuk : pasukim){
			average = average + pasuk.length();
		}
		average = average / pasukim.length;
		return average;
	}	
	
	//returns the average amount of the input character per each string in the input array
	public static double average(String[] house , String thingPer){
		double average = 0.0;
		int[] counter = new int[house.length];
		for(int i = 0; i < house.length; i++){
			for(int j = 0; j <= house[i].length() - thingPer.length(); j++){
				if(house[i].substring(j, j + thingPer.length()).equals(thingPer)){
					counter[i]++;
				}
			}
		}
		for(int p : counter){
			average = average + p;
		}
		average = average / house.length;
		return average;
	}
	
	//fix if needed
	/*public static double stanDev(String[] house , String thingPer){
		double sd = 0.0;
		double variance = 0.0;
		double stanDev = 0.0;
		double average = average(house , thingPer);
		int[] counter = new int[house.length];
		for(int i = 0; i < house.length; i++){
			for(int j = 0; j <= (house[i].length() - thingPer.length()); j++){
				if(house[i].substring(j, j + thingPer.length()).equals(thingPer)){
					counter[i]++;
				}
			}
		}
		for(int i = 0; i < house.length; i++){
			//if(Math.pow((counter[i] - average) , 2) != 0){
				sd = sd + Math.pow((counter[i] - average) , 2);
			//}
		}
		variance = sd / (house.length - 1);
		stanDev = Math.sqrt(variance);
		return stanDev;
	}*/
	
}