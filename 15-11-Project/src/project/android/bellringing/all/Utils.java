package project.android.bellringing.all;

import java.util.Arrays;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {
	
	public static float dpToPx(float dp, Context c) {
		
	    DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
	    float px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));       
	    return px;
	}
	
	public static String stageToNumBells(String key){
		
		String[] names = {"Minimus", "Doubles", "Doubles on 6", "Minor", "Triples", "Major", "Caters", "Royal", "Cinques", "Maximus", "Sextuples", "14", "Septuples", "16"};
		String[] numBells = {"4","5","6","6","7","8","9","10","11","12","13","14","15","16"};
		
		return numBells[Arrays.asList(names).indexOf(key)];
	}
	
	public static String bellsToBellNumber(String bell){
		String[] bells = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
		String[] bellNumber = {"1","2","3","4","5","6","7","8","9","0","E","T","A","B","C","D"};
		
		return bellNumber[Arrays.asList(bells).indexOf(bell)];
	}
	
	public static Composition getComposition(String s){
		String[] options = {"Plain Course", "Touch with Bobs", "Touch with Singles", "Touch with Bobs & Singles", "Touch of Spliced"};
		Composition[] comps = {Composition.PLAIN_COURSE, Composition.TOUCH_WITH_BOBS, Composition.TOUCH_WITH_SINGLES, Composition.TOUCH_WITH_BOBS_AND_SINGLES, Composition.PLAIN_COURSE};
		
		return comps[Arrays.asList(options).indexOf(s)];
		
	}
	
	public static String typeToFileName(String type){
		String[] types = {"Alliance Methods", "Delight Methods", "Differential Methods", "Half Methods", "Plain Methods", "Principles", "Surprise Methods", "Treble Place Methods", "Treble Bob Methods"};
		String[] fileNames = {"A", "D", "DF", "H", "P", "PR", "S", "TP", "T"};

		return fileNames[Arrays.asList(types).indexOf(type)];
	}
	
	public static String bellNumberToBells(String bellNum){
		String[] bells = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
		String[] bellNumber = {"1","2","3","4","5","6","6","7","8","9","0","E","T","A","B","C","D"};
		
		return bells[Arrays.asList(bellNumber).indexOf(bellNum)];
	}
	
	
}
