

public class Score {
	
	int total;
	
	
	public Score(){
		total = 0;
	}
	
	public int calculateScore(double playTime, double hitTime, double pealTime){
		
		
		
		double ring = pealTime/10;
		
		double score = Math.ceil(Math.abs((playTime - hitTime) / ring));
		
		score = 10 - score;
		if(score < 0)
			score = 0;
		
		total += score;		
		
		System.out.println(Math.abs((playTime - hitTime)) + "     scote      " + score);
		
		return (int) score; 
		
	}

}
