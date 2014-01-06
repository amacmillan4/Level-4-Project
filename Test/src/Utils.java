import java.util.Arrays;

public class Utils {

	
	public static String stageToNumBells(String key){
		
		String[] names = {"Minimus", "Doubles", "Doubles on 6", "Minor", "Triples", "Major", "Caters", "Royal", "Cinques", "Maximus", "Sextuples", "14", "Septuples", "16"};
		String[] numBells = {"4","5","6","6","7","8","9","10","11","12","13","14","15","16"};
		
		return numBells[Arrays.asList(names).indexOf(key)];
	}
	
	public static String bellsToBellNumber(String bell){
		String[] bells = {"4","5","6","7","8","9","10","11","12","13","14","15","16"};
		String[] bellNumber = {"4","5","6","6","7","8","9","0","E","T","A","B","C","D"};
		
		return bellNumber[Arrays.asList(bells).indexOf(bell)];
	}


}
