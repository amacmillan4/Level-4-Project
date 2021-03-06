
import java.util.ArrayList;
import java.util.Collections;

public class Method {

	private final char[] possibleBellNumbering = {'1','2','3','4','5','6','7','8','9','0','E','T','A','B','C','D'};
	private ArrayList<String> bellNumbering;

	private String name;
	private String method;
	private ArrayList<String> methodRep;
	private String leadEnd;
	private int pealTime;
	private int bells;

	private int loops;

	private String currentLine;

	private int currentMethodSection;
	private int currentOperationSection = 0;

	private int currentStart = 0;
	private boolean handStroke = true;

	boolean rounds = false;

	public Method(String name, String method, int pealTime, int bells) {
		this.name = name;
		this.pealTime = pealTime; 
		this.method = method.split(",")[0];
		this.leadEnd = method.split(",")[1];
		this.bells = bells;

		//Fill arrayList with correct number of bells
		bellNumbering = new ArrayList<String>(bells);
		for(int i = 0; i < bells; i++)
			bellNumbering.add(possibleBellNumbering[i] + "");

		methodRep = new ArrayList<String>();

		char[] methodCharArray = this.method.toCharArray();
		String tmp = "";

		//Changes that are required
		for(char a: methodCharArray){

			if (a == '.'){
				methodRep.add(tmp);
				tmp = "";
			}
			else if (a == '-'){
				if (!tmp.equals(""))
					methodRep.add(tmp);
				methodRep.add("x");
				tmp = "";
			}
			else if (a == '&' || a == ','){
				;
			}
			else{ 
				tmp += a;
			}
		}

		methodRep.add(tmp);

		//Reverse the method now
		for(int i = methodRep.size() - 2; i >= 0 ; i--)
			methodRep.add(methodRep.get(i));

		//Add the leadend
		methodRep.add(leadEnd);

		//Variables for calculating the next bell
		currentLine = "";
		currentMethodSection = 0;
		currentOperationSection = 0;

		for(int i = 0; i < bells; i++)
			currentLine += bellNumbering.get(i);

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

	public void restartMethod(){

		bellNumbering = new ArrayList<String>(bells);

		for(int i = 0; i < bells; i++)
			bellNumbering.add(possibleBellNumbering[i] + "");

		loops = 0;
		currentMethodSection = 0;
		currentOperationSection = 0;

		currentLine = "";
		for(int i = 0; i < bells; i++)
			currentLine += bellNumbering.get(i);
		
		System.out.println("RESTART "+currentLine);
	}

	public String calcNext(){

		if (rounds == true){
			return start();
		}
		else{

			if (loops == (bells - 1) && currentMethodSection == bells)
				return "\r";

			if (currentOperationSection == methodRep.size()){
				currentOperationSection = 0;
				loops++;
			}			

			if (currentMethodSection == bells || ((currentMethodSection == 0 && currentOperationSection == 0))){
				currentLine = calcLine(currentLine, methodRep.get(currentOperationSection++));
				currentMethodSection = 0;
			}

			return currentLine.toCharArray()[currentMethodSection++] + "";
		}

	}

	public String calcLine(String lastLine, String operation){

		String newLine = lastLine;

		if (operation.equals("x")){
			for (int i = 1; i <= bells; i = i + 2)
				newLine = swap(newLine, i , i+1);
		}

		else {

			char[] temp = operation.toCharArray();
			ArrayList<String> copy = new ArrayList<String>(bellNumbering);

			for (int i = 0; i < temp.length; i++)
				copy.set(bellNumbering.indexOf(temp[i] + ""), "REMOVE");

			copy.removeAll(Collections.singleton("REMOVE"));

			for (int i = 0; i < copy.size(); i = i + 2)
				newLine = swap(newLine,bellNumbering.indexOf(copy.get(i)) + 1, bellNumbering.indexOf(copy.get(i+1)) + 1);

		}

		return newLine;
	}

	private String swap(String b, int pos1, int pos2){

		char[] a = b.toCharArray();

		char temp = a[pos1 - 1];
		a[pos1 - 1] = a[pos2 - 1];
		a[pos2 - 1] = temp;

		String c = new String(a);

		return c;

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

	public void setBells(int bells) {
		this.bells = bells;
	}

	public int getPealTime() {
		return pealTime;
	}

	public void setPealTime(int pealTime) {
		this.pealTime = pealTime;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void arr(){

		for (String s: methodRep)
			System.out.print(s + " " );
	}

	@Override
	public String toString() {
		return "Method [name=" + name + ", method=" + method + ", leadEnd="
				+ leadEnd + ", pealTime=" + pealTime + ", bells=" + bells + "]";
	}

}
