import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		Pattern pattern = Pattern.compile("([a-zA-Z]+)([0-9]*)");

		File dir = new File("files");
		File[] files = dir.listFiles();
		String a = "";
		
		for (int i = 4; i < 17; i++){

			a = a + i + " ";
			
			for(File f: files){
				Matcher m = pattern.matcher(f.getName());
				m.matches();

				if(m.group(2).equals(i + "")){
					a += m.group(1) + ",";
				}
				
			}
			
			a = a.substring(0, a.length() - 1);
			a += "\n";
			
		}
		System.out.println(a);
	}


}