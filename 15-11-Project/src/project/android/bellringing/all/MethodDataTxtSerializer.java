package project.android.bellringing.all;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import android.content.Context;

public class MethodDataTxtSerializer {

	private Context context;
	private String filenameSetup;
	private String filenameMethod;

	public MethodDataTxtSerializer(Context c, String fS, String fD){
		context = c;
		filenameSetup = fS;
		filenameMethod = fD;

	}

	public SetupInstructions loadSetupData() throws IOException{

		BufferedReader reader = null;
		SetupInstructions s = null;

		try{
			//Open and read the file 
			InputStream in = context.openFileInput(filenameSetup);
			reader = new BufferedReader(new InputStreamReader(in));
			String line = reader.readLine().trim();
			System.out.println(line);
			s = new SetupInstructions(line);

		} catch (FileNotFoundException e){
			//Ignore, Only happens when starting afresh
			s = new SetupInstructions();

		} finally {
			if (reader != null)
				reader.close();
		}

		return s;

	}

	public ArrayList<Method> loadSetupMethod() throws IOException{

		BufferedReader reader = null;
		ArrayList<Method> a = new ArrayList<Method>();

		try{
			//Open and read the file 
			InputStream in = context.openFileInput(filenameMethod);
			reader = new BufferedReader(new InputStreamReader(in));
			String line = reader.readLine();
						
			while (line != null){
				System.out.println(line);
				Method m = new Method(line.trim());
				a.add(m);
				line = reader.readLine();
			}
			

		} catch (FileNotFoundException e){
			//Ignore, Only happens when starting afresh

		} finally {
			if (reader != null)
				reader.close();
		}
		

		return a;

	}

	public void saveSetupData(SetupInstructions s) throws IOException{

		//Write file to disk
		Writer writer = null;
		try{
			OutputStream out = context.openFileOutput(filenameSetup, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			System.out.println(s.toString());
			writer.write(s.toString());

		} finally {
			if (writer != null)
				writer.close();
		}

	}
	
	public void saveSetupMethod(ArrayList<Method> a) throws IOException{

		//Write file to disk
		Writer writer = null;
		try{
			OutputStream out = context.openFileOutput(filenameMethod, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			
			for(Method m: a){
				System.out.println("WRITE" + m.toString());
				writer.write(m.toString() + "\n");
			}

		} finally {
			if (writer != null)
				writer.close();
		}

	}


}
