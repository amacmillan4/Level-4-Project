package project.android.bellringing;

public class Score {

	int total;


	public Score(){
		total = 0;
	}

	public int calculateScore(double playTime, double hitTime){



		double ring = 100;

		double score = Math.ceil(Math.abs((playTime - hitTime) / ring));

		score = 11 - score;

		if (score == 11){
			score = 10;
		}

		if(score < 0)
			score = 0;

		total += score;		

		System.out.println("PlayTime: " + playTime/1000 + "    PressTime:: " + hitTime/1000 + " difference: " +  Math.abs((playTime - hitTime)) + "  score  " + score);

		return (int) score; 

	}
}