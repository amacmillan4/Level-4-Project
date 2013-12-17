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


public class MethodSetupMiniMethodChoiceFragment extends Fragment {

	ArrayList<String> list = new ArrayList<String>();
	HashMap<String, String> typeToFilenames = new HashMap<String, String>();
	HashMap<String, String> stageToBells = new HashMap<String, String>();



	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_activity_start_mini_method_choice, parent, false);

		LinearLayout ll = (LinearLayout) view.findViewById(R.id.mini_MC_listLL);

		setup();

		for (int i = 0; i < list.size(); i++){

			View test = inflater.inflate(R.layout.method_choice_item, parent);
			ll.addView(setupCheckBox(list.get(i),test));

		}

		return view;
	}

	private View setupCheckBox(final String name, View test){


		test.setClickable(true);
		final CheckBox title = (CheckBox) test.findViewById(R.id.MC_checkbox);
		test.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), MethodSetupAddMethodActivity.class);
				i.putExtra("filename", name);
				getActivity().startActivity(i);		
				getActivity().finish();
			}
		});

		title.setText(name);

		return test;

	}

	private void setup(){

		String[] names = {"Minimus", "Doubles", "Minor", "Triples", "Major", "Caters", "Royal", "Cinques", "Maximus", "Sextuples", "14", "Septuples", "16"};

		for (int i = 0; i < names.length; i++){
			stageToBells.put(names[i], (4 + i) + "");
		}

		try{
			BufferedReader br = null;			
			br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("files/AvailibleOptions")));

			String line;
			line = br.readLine();

			System.out.println(stageToBells.get(MethodLab.get(getActivity()).getSetup().getStage()));

			while(line != null){
				if(line.split("-")[0].equals(stageToBells.get(MethodLab.get(getActivity()).getSetup().getStage()))){
					for(String s: line.split("-")[1].split(",")){
						list.add(s);
						System.out.println(s);
					}
				}

				line = br.readLine();
			}
			br.close();	

		}catch(Exception e){
			System.out.println("File Not Found");
		}
	}	

}