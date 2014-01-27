package project.android.bellringing.fragments;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import project.android.bellringing.R;
import project.android.bellringing.activities.ActivityAddMethodsFromFile;
import project.android.bellringing.all.MethodLab;
import project.android.bellringing.all.Utils;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FragmentLoadMethodTypes extends Fragment {

	ArrayList<String> list = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_method_type, parent, false);

		setup();

		final ListView listView = (ListView) view.findViewById(R.id.addTypeListView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter); 

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent i = new Intent(getActivity(), ActivityAddMethodsFromFile.class);
				i.putExtra("filename", listView.getItemAtPosition(position).toString());
				getActivity().startActivity(i);		
				getActivity().finish();
				
			}

		}); 

		return view;
	}


	private void setup(){

		try{
			BufferedReader br = null;			
			br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("files/AvailibleOptions")));

			String line;
			line = br.readLine();

			while(line != null){
				if(line.split("-")[0].equals(Utils.stageToNumBells(MethodLab.get(getActivity()).getSetup().getStage()))){
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
