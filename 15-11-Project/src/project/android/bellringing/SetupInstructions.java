package project.android.bellringing;

public class SetupInstructions {

	private String stage;
	private String composition;
	private String method;
	private String pealTime;
	private boolean handbells, stopAtRounds, handstrokeGap, waitForMe, scoreBlows, scoreSummary,orientationLock;
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
	
	@Override
	public String toString() {
		return stage + ","	+ composition + "," + method + "," + pealTime
				+ "," + handbells + "," + stopAtRounds	+ "," + handstrokeGap + ","
				+ waitForMe + "," + scoreBlows + "," + scoreSummary + "," + orientationLock;
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
