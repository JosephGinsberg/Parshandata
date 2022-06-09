package Bible;

public class randomBible {
	
	//Creates a random String of hebrew letters to compare to.
	public static String randBible(int num){
		String[] letters = {"א","ב","ג","ד","ה","ו","ז","ח","ט","י","כ","ל","מ","נ","ס","ע","פ","צ","ק","ר","ש","ת","ם","ן","ץ","ף","ך"};
		String newb = "";
		for(int i = 0; i < num; i++){
			int x = (int) (Math.random() * 27);
			newb += letters[x];
		}
		return newb;
	}
	
}