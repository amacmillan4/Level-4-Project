package project.android.bellringing.all;

public class Score {

	int total;
	int timesPressed;


	public Score(){
		total = 0;
		timesPressed = 0;
	}

	public int calculateScore(long playTime, long hitTime){

		long ring = 20;

		double score = 11 - Math.ceil(Math.abs((playTime - hitTime) / ring));
		
		if (score == 11)
			score = 10;
		else if(score < 0)
			score = 0;

		total += score;	
		timesPressed++;

		return (int) score; 

	}
	
	public int getAverage(){
		
		if (timesPressed == 0)
			return 0;
		
		return (int) total/timesPressed;
	}
}