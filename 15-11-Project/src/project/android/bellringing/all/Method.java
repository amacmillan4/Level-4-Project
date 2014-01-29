package project.android.bellringing.all;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import project.android.bellringing.utilities.Composition;
import project.android.bellringing.utilities.Utils;

public class Method{
	
	private final char[] possibleBellNumbering = {'1','2','3','4','5','6','7','8','9','0','E','T','A','B','C','D'};
	
	//Constructor Variables
	private String methodName;
	private String methodType;
	private String wholeMethod;
	private String method;
	private String leadEnd;
	private int bells;
	
	//Initialization variables
	private int playingOnBells;
	private Composition composition;

	//Display Variable
	private String line = "";
	
	//Method Changes Variables
	private ArrayList<String> bellNumbering;

	private ArrayList<String> methodChanges = new ArrayList<String>();
	private ArrayList<String> methodLeadEnd = new ArrayList<String>();
	private ArrayList<String> methodBob= new ArrayList<String>();
	private ArrayList<String> methodSingle= new ArrayList<String>();
	
	private String bob = "";
	private String single = "";
	private String compositionStatus = "";
	
	//Variables used while method playing
	private String currentLine;
	private int currentMethodSection;
	private int currentOperationSection = 0;
	private int currentStart = 0;
	private boolean handStroke = true;
	private boolean rounds = false;

	public Method(String methodName, String methodType, String wholeMethod, String bells) {
		this.methodName = methodName;
		this.methodType = methodType;
		this.bells = Integer.parseInt(bells);
		this.wholeMethod = wholeMethod;

		if (wholeMethod.charAt(0) == '&'){
			this.method = wholeMethod.split(",")[0].substring(1,wholeMethod.split(",")[0].length() );
			this.leadEnd = wholeMethod.split(",")[1];
		}
		else if (wholeMethod.charAt(0) == '+'){
			this.method = wholeMethod.substring(1, wholeMethod.length());
			this.leadEnd = "";
		}
	}

	public Method(String line) {	
		this.methodName = line.split("\t")[0];
		this.methodType = line.split("\t")[1];
		this.bells = Integer.parseInt(line.split("\t")[3]);
		this.wholeMethod = line.split("\t")[2];

		if (wholeMethod.charAt(0) == '&'){
			this.method = wholeMethod.split(",")[0].substring(1,wholeMethod.split(",")[0].length() );
			this.leadEnd = wholeMethod.split(",")[1];
		}
		else if (wholeMethod.charAt(0) == '+'){
			this.method = wholeMethod.substring(1, wholeMethod.length());
			this.leadEnd = "";
		}
	} 
	
	public Method(Method m){
		this.methodName = m.getMethodName();
		this.methodType = m.getType();
		this.bells = m.getBells();
		this.wholeMethod = m.getWholeMethod();

		if (wholeMethod.charAt(0) == '&'){
			this.method = wholeMethod.split(",")[0].substring(1,wholeMethod.split(",")[0].length() );
			this.leadEnd = wholeMethod.split(",")[1];
		}
		else if (wholeMethod.charAt(0) == '+'){
			this.method = wholeMethod.substring(1, wholeMethod.length());
			this.leadEnd = "";
		}
	}
	
	public void initialize(int playingOnBells, Composition composition){

		this.composition = composition;
		this.playingOnBells = playingOnBells;

		//Fill arrayList with correct number of bells
		bellNumbering = new ArrayList<String>(playingOnBells);

		for(int i = 0; i < playingOnBells; i++)
			bellNumbering.add(possibleBellNumbering[i] + "");

		//Calculate Bobs and Singles
		getBobSingle();

		//First set always plain course
		ArrayList<String> mMethod = implementChanges(method);
		ArrayList<String> mLeadEnd = implementChanges(leadEnd);
		ArrayList<String> mBob = implementChanges(bob);
		ArrayList<String> mSingle = implementChanges(single);

		//Reverse method if synchronous
		if (wholeMethod.charAt(0) == '&'){
			mMethod = reverseMethod(mMethod);
			mLeadEnd = reverseMethod(mLeadEnd);
			mBob = reverseMethod(mBob);
			mSingle = reverseMethod(mSingle);
		}
		
		//Lead End Changes
		methodLeadEnd.addAll(mMethod);
		methodLeadEnd.addAll(mLeadEnd);

		//Bob Changes
		methodBob.addAll(mMethod);
		methodBob.addAll(mLeadEnd);

		for(int i = 0; i < mBob.size(); i++)
			methodBob.remove(methodBob.size() - 1);

		methodBob.addAll(mBob);

		//Single Changes
		methodSingle.addAll(mMethod);
		methodSingle.addAll(mLeadEnd);

		for(int i = 0; i < mBob.size(); i++)
			methodSingle.remove(methodSingle.size() - 1);

		methodSingle.addAll(mSingle);

		//Decide whether next is Plain Course, Bob or Single
		calculateNextBobSingle();

		//Variables for calculating the next bell
		currentLine = "";
		currentMethodSection = 0;
		currentOperationSection = 0;


		for(int i = 0; i < playingOnBells; i++)
			currentLine += bellNumbering.get(i);

	}


	private ArrayList<String> reverseMethod(ArrayList<String> changes){

		//If it is of size 1 or less no reversing needs done
		if (changes.size() <= 1)
			return changes;

		//Reverse method (apart from last) and add to previous ArrayList
		ArrayList<String> reverse = new ArrayList<String>(changes);
		Collections.reverse(reverse);
		reverse.remove(0);
		changes.addAll(reverse);
		return changes;
	}

	private ArrayList<String> implementChanges(String methodString){

		ArrayList<String> unreversedMethodChanges = new ArrayList<String>();

		System.out.println(methodString);
		//If it is an asynchronous method this will be empty
		if(methodString.equals(""))
			return unreversedMethodChanges;

		char[] stringToCharArray = methodString.toCharArray();
		String changes = "";

		//Changes that are required
		for(char a: stringToCharArray){

			//Dot ends the section of changes - add old changes and ready string for new
			if (a == '.'){
				unreversedMethodChanges.add(changes);
				changes = "";
			}
			else if (a == '-'){

				//Only add if there has been some change - add an x - representing all to switch
				if (!changes.equals(""))
					unreversedMethodChanges.add(changes);

				unreversedMethodChanges.add("x");
				changes = "";
			}
			else{ 
				changes += a;
			}
		}

		//Add anything that is left
		unreversedMethodChanges.add(changes);

		return unreversedMethodChanges;
	}

	private void getBobSingle(){

		final Set<String> type1 = new HashSet<String>(Arrays.asList("Alliance Methods", "Plain Methods", "Surprise Methods", "Treble Bob Methods", "Delight Methods")); 

		if (methodName.equals("Grandsire")){


		}
		else if (bells % 2 == 0){

			if(type1.contains(methodType)){

				if(leadEnd.equals("12")){
					bob = "14";
					single = "1234";
				}
				else if(leadEnd.equals("1" + Utils.bellsToBellNumber(Integer.toString(bells)))){
					bob = "1" + Utils.bellsToBellNumber(Integer.toString(bells - 2));
					single = "1" + Utils.bellsToBellNumber(Integer.toString(bells - 2)) 
							+ Utils.bellsToBellNumber(Integer.toString(bells - 1)) + Utils.bellsToBellNumber(Integer.toString(bells));
				}
				else{
					bob = "";
					single = "";
				}

			}
		}
		else{

			if(type1.contains(methodType)){

				if(leadEnd.equals("12" + Utils.bellsToBellNumber(Integer.toString(bells)))){
					bob = "14" + Utils.bellsToBellNumber(Integer.toString(bells));
					single = "1234" + Utils.bellsToBellNumber(Integer.toString(bells));
				}
				else if(leadEnd.equals("1")){
					bob = "1" + Utils.bellsToBellNumber(Integer.toString(bells - 1)) + Utils.bellsToBellNumber(Integer.toString(bells));
					single = "1" + Utils.bellsToBellNumber(Integer.toString(bells - 3)) + Utils.bellsToBellNumber(Integer.toString(bells - 2))
							+ Utils.bellsToBellNumber(Integer.toString(bells - 1)) + Utils.bellsToBellNumber(Integer.toString(bells));
				}
				else{
					bob = "";
					single = "";
				}
			}
		}
		
		System.out.println("Bob: " + bob + "    Single: " + single );
	}

	private void calculateNextBobSingle(){

		compositionStatus = "";
		methodChanges.clear();

		int random = (int) (Math.random() * 600);

		if (composition == Composition.PLAIN_COURSE){
			methodChanges.addAll(methodLeadEnd);
		}
		else if (composition == Composition.TOUCH_WITH_BOBS){
			if(random % 2 == 0){
				methodChanges.addAll(methodBob);
				compositionStatus = "Bob";
			}
			else{
				methodChanges.addAll(methodLeadEnd);
				compositionStatus = "";
			}
		}
		else if (composition == Composition.TOUCH_WITH_SINGLES){
			if(random % 2 == 0){
				methodChanges.addAll(methodSingle);
				compositionStatus = "Single";
			}
			else{
				methodChanges.addAll(methodLeadEnd);
				compositionStatus = "";
			}
		}
		else if (composition == Composition.TOUCH_WITH_BOBS_AND_SINGLES){
			if(random % 3 == 0){
				methodChanges.addAll(methodBob);
				compositionStatus = "Bob";
			}
			else if(random % 3 == 1) {
				methodChanges.addAll(methodLeadEnd);
				compositionStatus = "";
			}
			else {
				methodChanges.addAll(methodSingle);
				compositionStatus = "Single";
			}
		}

		
		System.out.println(compositionStatus);

		if (playingOnBells != bells){
			for(int i = 0; i < methodChanges.size(); i++){
				String s = methodChanges.get(i);

				if(!s.equals("x"))
					methodChanges.set(i, s + playingOnBells);

			}
		}

	}

	public String start(){

		if (handStroke == false && currentStart == bellNumbering.size()){
			currentStart = 0;
			handStroke = true;
		}

		if (handStroke == true && currentStart == bellNumbering.size()){
			currentStart = 0;
			handStroke = false;
		}
		
		return bellNumbering.get(currentStart++);

	}

	public void swapRound(){

		if (rounds)
			rounds = false;
		else
		{
			restartMethod();
			rounds = true;
		}
	}

	private void restartMethod(){

		bellNumbering = new ArrayList<String>(playingOnBells);

		for(int i = 0; i < playingOnBells; i++)
			bellNumbering.add(possibleBellNumbering[i] + "");

		currentMethodSection = 0;
		currentOperationSection = 0;

		currentLine = "";
		for(int i = 0; i < playingOnBells; i++)
			currentLine += bellNumbering.get(i);

	}

	public String calcNext(){

		if (rounds == true){
			return start();
		}
		else{

			if (currentOperationSection == methodChanges.size()){
				calculateNextBobSingle();
				currentOperationSection = 0;
			}			

			if (currentMethodSection == playingOnBells || ((currentMethodSection == 0 && currentOperationSection == 0))){
				System.out.println(currentLine);
				currentLine = calcLine(currentLine, methodChanges.get(currentOperationSection++));
				currentMethodSection = 0;
			}

			return currentLine.toCharArray()[currentMethodSection++] + "";
		}

	}

	private String calcLine(String lastLine, String operation){

		String newLine = lastLine;

		//Swap all if operation is x
		if (operation.equals("x")){
			for (int i = 1; i <= bells; i = i + 2)
				newLine = swap(newLine, i , i+1);
		}
		else {

			char[] temp = operation.toCharArray();
			ArrayList<String> copy = new ArrayList<String>(bellNumbering);

			//Mark all positions that do not move as REMOVE and remove them from the arraylist
			for (int i = 0; i < temp.length; i++)
				copy.set(bellNumbering.indexOf(temp[i] + ""), "REMOVE");

			copy.removeAll(Collections.singleton("REMOVE"));

			for (int i = 0; i < copy.size(); i = i + 2)
				newLine = swap(newLine,bellNumbering.indexOf(copy.get(i)) + 1, bellNumbering.indexOf(copy.get(i+1)) + 1);

		}

		return newLine;
	}

	public String getNextLine(){

		String finishOn = "";

		for(int i = 0; i < playingOnBells; i++)
			finishOn += bellNumbering.get(i);

		if(line.equals(finishOn))
			return "";

		if (line.equals("")){
			line = finishOn+"\n";
		}
		else{
			line = "";
		}
		
		for(int k = 0; k < playingOnBells; k++)
			line = line + calcNext();

		return line;




	}

	private String swap(String b, int pos1, int pos2){

		char[] a = b.toCharArray();

		char temp = a[pos1 - 1];
		a[pos1 - 1] = a[pos2 - 1];
		a[pos2 - 1] = temp;

		String c = new String(a);

		return c;

	}
	
	public int getMethodLeadEndLength(){
		return methodLeadEnd.size();
	}

	public String getCompositionStatus(){
		return compositionStatus;
	}
	
	public String getLeadEnd() {
		return leadEnd;
	}

	public void setLeadEnd(String leadEnd) {
		this.leadEnd = leadEnd;
	}

	public int getBells() {
		return bells;
	}

	public int getPlayingOn(){
		return playingOnBells;
	}

	public void setBells(int bells) {
		this.bells = bells;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getType() {
		return methodType;
	}

	public void setName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getWholeMethod(){
		return wholeMethod;
	}

	@Override
	public String toString() {
		return methodName + "\t" + methodType + "\t" + wholeMethod + "\t" + bells;
	}

}
