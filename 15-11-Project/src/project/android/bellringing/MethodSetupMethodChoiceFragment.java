package project.android.bellringing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MethodSetupMethodChoiceFragment extends Fragment {

	HashMap<Integer, ArrayList<String>> hmap = new HashMap<Integer, ArrayList<String>>() ;
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

		for (int i = 0; i < hmap.size(); i++){			

			if (hmap.get(500 + i).size() == 1){

				View test = inflater.inflate(R.layout.method_choice_title, parent);
				TextView t = (TextView) test.findViewById(R.id.MC_title);
				t.setText(hmap.get(500 + i).get(0));
				test.setClickable(false);
				ll.addView(test);			

			}
			else{
				System.out.println("2");
				View test = inflater.inflate(R.layout.method_choice_item, parent);
				ll.addView(setupCheckBox(hmap.get(i + 500).get(0),test));
			}
		}

		Button b = (Button) view.findViewById(R.id.MC_Add);
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), MethodSetupMiniMethodChoiceActivity.class);
				getActivity().startActivity(i);				
			}
		});

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


	private void setup(){

		Pattern pattern2 = Pattern.compile("<.*><.*");


		try{
			int i = 500;
			BufferedReader br = null;			
			br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("cache/" + MethodLab.get(getActivity()).getSetup().getStage())));

			String line;
			line = br.readLine();

			while (line != null) {

				Matcher matcher = pattern2.matcher(line);
				ArrayList<String> alist = new ArrayList<String>();

				if (matcher.find()) {

					String[] split = line.split("><");

					try{						
						alist.add(split[0].substring(1,split[0].length()));
						alist.add(split[3].split(",")[0].substring(1,split[3].split(",")[0].length()));
						alist.add(split[3].split(",")[1]);

						hmap.put(i, alist);

					}catch(Exception e){
						System.out.println("Poor format");
					}
				}
				else{
					alist.add(line);
					hmap.put(i, alist);
				}
				i++;

				line = br.readLine();
			}

			br.close();	
		}

		catch(Exception e){
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
