package project.android.bellringing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;


public class MethodSetupAddMethodFragment extends Fragment {

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
		View view = inflater.inflate(R.layout.fragment_activity_start_add_method, parent, false);

		long t = System.currentTimeMillis()/1000;
		
		
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.MC_add_listLL);

		hashMapSetup();

		try{
			int i = 0;
			BufferedReader br = null;			
			br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("files/S8")));

			String line;
			line = br.readLine();
			line = br.readLine();



			while (line != null) {
				String[] split = line.split("><");

				try{
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(split[0].substring(1,split[0].length()));
					alist.add(split[3].split(",")[0].substring(1,split[3].split(",")[0].length()));
					alist.add(split[3].split(",")[1]);

					//View test = inflater.inflate(R.layout.method_choice_item, parent);
					//ll.addView(setupCheckBox(split[0].substring(1,split[0].length()),test));

					i++;

				}catch(Exception e){
					System.out.println("Poor format");
				}

				line = br.readLine();
			}

			br.close();	
		}


		catch(Exception e){
			System.out.println("File Not Found");
		}

		System.out.println(System.currentTimeMillis()/1000 - t);

		return view;
	}

	private View setupCheckBox(String name, View test){


		test.setClickable(true);
		final CheckBox title = (CheckBox) test.findViewById(R.id.MC_checkbox);
		test.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), MethodSetupAddMethodActivity.class);
				getActivity().startActivity(i);				


			}

		});

		title.setText(name);

		return test;

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
