import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		Pattern pattern = Pattern.compile("([a-zA-Z]+)([0-9]*)");
		Pattern pattern2 = Pattern.compile("<.*><.*");

		ArrayList<String> a = new ArrayList<String>();
		HashMap<Integer, ArrayList<String>> hmap = new HashMap<Integer, ArrayList<String>>();

		File dir = new File("files");
		File[] files = dir.listFiles();

		for(File f: files){
			Matcher m = pattern.matcher(f.getName());
			m.matches();

			if(m.group(2).equals("8"))
				a.add(f.getPath());
		}

		try{
			int i = 500;
			for(String file: a){

				BufferedReader br = null;			


				br = new BufferedReader(new FileReader(file));

				String line;
				line = br.readLine();

				while (line != null) {
					Matcher matcher = pattern2.matcher(line);

					while (matcher.find()) {
						String[] split = line.split("><");

						try{
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(split[0].substring(1,split[0].length()));
							alist.add(split[3].split(",")[0].substring(1,split[3].split(",")[0].length()));
							alist.add(split[3].split(",")[1]);
							
							System.out.println(i + " " + alist.get(1));
							
							hmap.put(i, alist);
							i++;
						}catch(Exception e){
							System.out.println("Poor format");
						}
						
					}
					
					line = br.readLine();
					System.out.println();
				}
				
				br.close();	
			}

		}catch(Exception e){
			System.out.println("File Not Found");
		}

		for (int i = 0; i < hmap.size(); i++){
			System.out.println(hmap.get(i + 500).get(1));
		}



	}
}