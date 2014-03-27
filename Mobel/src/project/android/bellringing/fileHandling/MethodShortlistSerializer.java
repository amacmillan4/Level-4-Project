package project.android.bellringing.fileHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import project.android.bellringing.all.Method;

import android.content.Context;

public class MethodShortlistSerializer {

	private Context context;

	public MethodShortlistSerializer(Context c){
		context = c;
	}

	public ArrayList<Method> loadData(String stage) throws IOException{

		BufferedReader reader = null;
		ArrayList<Method> methods = new ArrayList<Method>();

		System.out.println(stage);
		
		try{
			//Open and read the file 
			InputStream in = context.openFileInput(stage);
			reader = new BufferedReader(new InputStreamReader(in));
			String line = reader.readLine().trim();

			while (line != null) {
				String[] split = line.split("\t");

				System.out.println("LOADING: " + line);
				Method method = new Method(split[0], split[1], split[2], split[3]);
				System.out.println("LOADINGGGGGG : " + method.toString());
				methods.add(method);

				line = reader.readLine();
			}		

		} catch (Exception e){
			//Ignore, Only happens when starting afresh

		} finally {
			if (reader != null)
				reader.close();
		}

		return methods;

	}

	public void saveData(ArrayList<Method> methods, String filename){

		//Write file to disk
		Writer writer = null;
		try{
			OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);

			//Sort methods
			Collections.sort(methods, new Comparator<Method>() {
			    public int compare(Method a, Method b) {
			        return a.getType().compareTo(b.getType());
			    }
			});
			
			String currentType = "";
			String stringToWrite = "";
			
			for (Method m: methods){
				
				System.out.println("CHECKING:" +  m.toString());
				
				if (!m.getType().equals(currentType)){
					currentType = m.getType();
				}
				stringToWrite += m.toString() + "\n";
				
			}
			
			System.out.println("WRITING TO FILE: " + stringToWrite);
			
			writer.write(stringToWrite);
	
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}


}
