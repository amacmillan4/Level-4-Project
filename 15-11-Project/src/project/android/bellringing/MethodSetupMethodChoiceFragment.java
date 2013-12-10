package project.android.bellringing;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;


public class MethodSetupMethodChoiceFragment extends Fragment {

	HashMap<Integer, ArrayList<String>> hmap;
	HashMap<String, String> nameToBells = new HashMap<String, String>();
	HashMap<String, String> bellsToFile = new HashMap<String, String>();
	

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_activity_start_method_choice, parent, false);

		LinearLayout ll = (LinearLayout) view.findViewById(R.id.MC_listLL);
		
		hashMapSetup();
		setup();
		
		for (int i = 0; i <50; i++){
			View test = inflater.inflate(R.layout.method_choice_item, parent);
			ll.addView(setupCheckBox(hmap.get(i + 500).get(0),test));
		}

		return view;
	}

	private View setupCheckBox(String name, View test){


		test.setClickable(true);
		final CheckBox title = (CheckBox) test.findViewById(R.id.MC_checkbox);
		test.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				title.setChecked(!title.isChecked());

			}
		});

		title.setText(name);

		return test;

	}

	@SuppressLint("UseSparseArrays")
	private void setup(){

		Pattern pattern = Pattern.compile("([a-zA-Z]+)([0-9]*)");
		Pattern pattern2 = Pattern.compile("<.*><.*");

		ArrayList<String> a = new ArrayList<String>();
		hmap = new HashMap<Integer, ArrayList<String>>();

		String[] files = null;
		try {
			files = getActivity().getAssets().list("files");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		for(String f: files){
			Matcher m = pattern.matcher(f);
			m.matches();

			if(m.group(2).equals(nameToBells.get(MethodLab.get(getActivity()).getSetup().getComposition())));
				a.add("files/" +f);
		}

		try{
			int i = 500;
			for(String file: a){
				BufferedReader br = null;			
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(file)));

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
							
							hmap.put(i, alist);
							i++;
							
						}catch(Exception e){
							System.out.println("Poor format");
						}

					}

					line = br.readLine();
				}

				br.close();	
			}

		}catch(Exception e){
			System.out.println("File Not Found");
		}
	}
	
	private void hashMapSetup(){
		String[] names = {"Minimus", "Doubles", "Doubles on 6", "Minor", "Triples", "Major", "Caters", "Royal", "Cinques", "Maximus", "Sextuples", "14", "Septuples", "16"};
		String[] otherNames = {"Alliance Methods", "Delight Methods", "Differential Methods", "Half Methods", "Plain Methods", "Principles", "Surprise Methods", "Treble Bob Methods", "Treble Place Methods"};
		String[] fileNames = {"A", "D", "DF", "H", "P", "PR", "S", "T", "TB"};
		
		for( int i = 0; i < names.length; i++){
			nameToBells.put(names[i], i + "");
		}
		
		for (int i = 0; i < otherNames.length; i++){
			bellsToFile.put(otherNames[i], fileNames[i]);
		}
}
}
