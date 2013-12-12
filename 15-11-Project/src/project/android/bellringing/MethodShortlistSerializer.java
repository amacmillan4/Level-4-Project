package project.android.bellringing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import android.content.Context;
import android.util.SparseArray;

public class MethodShortlistSerializer {

	private Context context;

	public MethodShortlistSerializer(Context c){
		context = c;
	}

	public ShortlistedMethods loadData(String stage) throws IOException{

		BufferedReader reader = null;
		ShortlistedMethods sm = new ShortlistedMethods();

		try{
			//Open and read the file 
			InputStream in = context.openFileInput(stage);
			reader = new BufferedReader(new InputStreamReader(in));
			String line = reader.readLine().trim();

			while (line != null) {
				String[] split = line.split("><");

				Method2 method = new Method2((split[0].substring(1,split[0].length())), split[1],
						Integer.parseInt(split[2]), split[3].substring(1,split[3].length()));

				sm.addMethod(method);

				line = reader.readLine();
			}		
			
		} catch (FileNotFoundException e){
			//Ignore, Only happens when starting afresh

		} finally {
			if (reader != null)
				reader.close();
		}

		return sm;

	}

	public void saveData(SparseArray<Method2> s, String filename) throws IOException{

		//Write file to disk
		Writer writer = null;
		try{
			OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(s.toString());

		} finally {
			if (writer != null)
				writer.close();
		}

	}


}
