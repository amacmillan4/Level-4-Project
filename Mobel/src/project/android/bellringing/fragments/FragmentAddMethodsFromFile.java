package project.android.bellringing.fragments;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import project.android.bellringing.R;
import project.android.bellringing.activities.ActivitySelectMethod;
import project.android.bellringing.adapters.AddMethodsArrayAdapter;
import project.android.bellringing.all.Method;
import project.android.bellringing.all.SingletonData;
import project.android.bellringing.fileHandling.MethodShortlistSerializer;
import project.android.bellringing.utilities.Utils;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentAddMethodsFromFile extends ListFragment {

	SparseArray<Method> map;
	AddMethodsArrayAdapter<String> adapter;
	ArrayList<Integer> selections = new ArrayList<Integer>();
	
	MethodShortlistSerializer mss;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		map = new SparseArray<Method>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_method_from_file, parent, false);	
		
		//Setup
		mss = new MethodShortlistSerializer(getActivity());
		ArrayList<String> allMethodNames = new ArrayList<String>();
		
		//Get filename chosen passed from previous fragment
		Intent intent = getActivity().getIntent();
		String type = intent.getStringExtra("filename");
		String s = Utils.typeToFileName(type) + Utils.stageToNumBells(SingletonData.get(getActivity()).getSetup().getStage());

		//Load Method Data from File
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
					Method m = new Method(split[0].substring(1,split[0].length()), type , split[3] ,split[2]);

					map.put(i, m);
					allMethodNames.add(i, m.getMethodName());

					i++;

				}catch(Exception e){
					e.printStackTrace();
				}

				line = br.readLine();
			}

			br.close();	

			//Pass all method names to adapter
			adapter = new AddMethodsArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, allMethodNames);
			setListAdapter(adapter);

		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//Set up of Button to save Users choices
		Button btnSaveChoices = (Button) view.findViewById(R.id.addBtnSave);
		btnSaveChoices.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				ArrayList<Method> selectedMethods = new ArrayList<Method>();
				
				//For every selected method name, add the methods to file
				for(Integer i: adapter.getPositionsOfMethods()){
					selectedMethods.add(map.get(i));
					System.out.println("ADDING TO METHOD LAB:" + map.get(i).toString());
				}
							
				SingletonData.get(getActivity()).addMethods(selectedMethods);
				SingletonData.get(getActivity()).saveMethodData();
				
				//Close fragment and go back to Method Choice
				Intent i = new Intent(getActivity(), ActivitySelectMethod.class);
				getActivity().startActivity(i);	
				getActivity().finish();
				
			}
		});
		
		//Set up Button to deselct all selected methods
		Button btnDeselectAll = (Button) view.findViewById(R.id.addBtnDeselectAll);
		btnDeselectAll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.uncheckAll();
			}
		});
		
		return view;
	}
}
