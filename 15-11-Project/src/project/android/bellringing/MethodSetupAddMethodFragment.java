package project.android.bellringing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MethodSetupAddMethodFragment extends ListFragment {

	HashMap<String, String> typeToFilenames = new HashMap<String, String>();
	HashMap<String, String> stageToBells = new HashMap<String, String>();
	SparseArray<Method2> map;
	CustomArrayAdapter<String> adapter;
	ArrayList<Integer> selections = new ArrayList<Integer>();
	
	MethodShortlistSerializer mss;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		map = new SparseArray<Method2>();
		hashMapSetup();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_activity_start_add_method, parent, false);	
		
		//Set up
		mss = new MethodShortlistSerializer(getActivity());
		ArrayList<String> names = new ArrayList<String>();
		
		//Get data passed from previous fragment
		Intent intent = getActivity().getIntent();
		String type = intent.getStringExtra("filename");
		String s = typeToFilenames.get(type) + stageToBells.get(MethodLab.get(getActivity()).getSetup().getStage());

		//Load data
		try{
			int i = 0;
			BufferedReader br = null;			
			br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("files/" + s)));

			String line;
			line = br.readLine();  //Dont need first line, it is a date
			line = br.readLine();

			while (line != null) {
				String[] split = line.split("><");

				try{
					Method2 m = new Method2(split[0].substring(1,split[0].length()), type , split[3] ,split[2]);

					map.put(i, m);
					names.add(i, m.getMethodName());

					i++;

				}catch(Exception e){
					e.printStackTrace();
					System.out.println("Exception with " + line);
				}

				line = br.readLine();
			}

			br.close();	


			adapter = new CustomArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, names);
			setListAdapter(adapter);

		}
		catch(Exception e){
			System.out.println("File Not Found");
		}
		
		Button add = (Button) view.findViewById(R.id.addMenu_save);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				ArrayList<Method2> a = new ArrayList<Method2>();
				
				for(Integer i: adapter.getMethods()){
					a.add(map.get(i));
					System.out.println("ADDING TO METHOD LAB:" + map.get(i).toString());
				}
							
				MethodLab.get(getActivity()).addMethods(a);
				MethodLab.get(getActivity()).saveMethodData();
				Intent i = new Intent(getActivity(), MethodSetupMethodChoiceActivity.class);
				getActivity().startActivity(i);	
				getActivity().finish();
				
			}
		});

		Button deselectAll = (Button) view.findViewById(R.id.addMenu_deselectAll);
		deselectAll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				adapter.uncheckAll();
				
			}
		});
		
		return view;
	}

	private void hashMapSetup(){

		String[] fileNames = {"A", "D", "DF", "H", "P", "PR", "S", "TP", "T"};
		String[] type = {"Alliance Methods", "Delight Methods", "Differential Methods", "Half Methods", "Plain Methods", "Principles", "Surprise Methods", "Treble Place Methods", "Treble Bob Methods"};

		String[] names = {"Minimus", "Doubles", "Minor", "Triples", "Major", "Caters", "Royal", "Cinques", "Maximus", "Sextuples", "14", "Septuples", "16"};
		
		for (int i = 0; i < type.length; i++){
			typeToFilenames.put(type[i], fileNames[i]);
		}
		
		for (int i = 0; i < names.length; i++){
			stageToBells.put(names[i], (4 + i) + "");
		}
		
	
	}
}
