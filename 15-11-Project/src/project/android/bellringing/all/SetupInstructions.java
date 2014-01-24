package project.android.bellringing.all;

public class SetupInstructions {

	private String stage;
	private String composition;
	private String method;
	private String pealTime;
	private boolean handbells, stopAtRounds, handstrokeGap, waitForMe, scoreBlows, scoreSummary,orientationLock;
	
	public SetupInstructions(){
		this.stage = "";
		this.composition = "";
		this.method = "";
		this.handbells = false;
		this.pealTime = "1.30";
		this.stopAtRounds = false;
		this.handstrokeGap = false;
		this.waitForMe = false;
		this.scoreBlows = false;
		this.scoreSummary = false;
		this.orientationLock = false;
	}
	
	public SetupInstructions(String stage, String composition, String method, boolean handbells,String pealTime, boolean stopAtRounds, boolean handstrokeGap,
			boolean waitForMe, boolean scoreBlows, boolean scoreSummary,boolean orientationLock) {

		this.stage = stage;
		this.composition = composition;
		this.method = method;
		this.handbells = handbells;
		this.pealTime = pealTime;
		this.stopAtRounds = stopAtRounds;
		this.handstrokeGap = handstrokeGap;
		this.waitForMe = waitForMe;
		this.scoreBlows = scoreBlows;
		this.scoreSummary = scoreSummary;
		this.orientationLock = orientationLock;
	}

	public SetupInstructions(String s){

		this.stage = s.split("\t")[0];
		this.composition = s.split("\t")[1];
		this.method = s.split("\t")[2];
		this.pealTime = s.split("\t")[3];
		this.handbells = Boolean.parseBoolean(s.split("\t")[4]);
		this.stopAtRounds = Boolean.parseBoolean(s.split("\t")[5]);
		this.handstrokeGap = Boolean.parseBoolean(s.split("\t")[6]);
		this.waitForMe = Boolean.parseBoolean(s.split("\t")[7]);
		this.scoreBlows = Boolean.parseBoolean(s.split("\t")[8]);
		this.scoreSummary = Boolean.parseBoolean(s.split("\t")[9]);
		this.orientationLock = Boolean.parseBoolean(s.split("\t")[10]);

	}

	@Override
	public String toString() {
		
		return stage + "\t"	+ composition + "\t" + method + "\t" + pealTime
				+ "\t" + handbells + "\t" + stopAtRounds + "\t" + handstrokeGap + "\t"
				+ waitForMe + "\t" + scoreBlows + "\t" + scoreSummary + "\t" + orientationLock;
	}

	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public boolean getHandbellsOrNot() {
		return handbells;
	}
	public void setHandbellsOrNot(boolean handbells) {
		this.handbells = handbells;
	}
	public String getPealTime() {
		return pealTime;
	}
	public void setPealTime(String pealTime) {
		this.pealTime = pealTime;
	}
	public boolean isStopAtRounds() {
		return stopAtRounds;
	}
	public void setStopAtRounds(boolean stopAtRounds) {
		this.stopAtRounds = stopAtRounds;
	}
	public boolean isHandstrokeGap() {
		return handstrokeGap;
	}
	public void setHandstrokeGap(boolean handstrokeGap) {
		this.handstrokeGap = handstrokeGap;
	}
	public boolean isWaitForMe() {
		return waitForMe;
	}
	public void setWaitForMe(boolean waitForMe) {
		this.waitForMe = waitForMe;
	}
	public boolean isScoreBlows() {
		return scoreBlows;
	}
	public void setScoreBlows(boolean scoreBlows) {
		this.scoreBlows = scoreBlows;
	}
	public boolean isScoreSummary() {
		return scoreSummary;
	}
	public void setScoreSummary(boolean scoreSummary) {
		this.scoreSummary = scoreSummary;
	}
	public boolean isOrientationLock() {
		return orientationLock;
	}
	public void setOrientationLock(boolean orientationLock) {
		this.orientationLock = orientationLock;
	}

}
