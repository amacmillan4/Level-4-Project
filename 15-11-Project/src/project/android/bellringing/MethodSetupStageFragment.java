package project.android.bellringing;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MethodSetupStageFragment extends Fragment {

	String[] options = {"Minimus","Doubles","Doubles on 6","Minor","Triples","Major","Caters","Royal","Cinques","Maximus","Sextuples", "14","Septuples","16"};

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_activity_start_stage, parent, false);
		
	    // Get ListView object from xml
        final ListView listView = (ListView) view.findViewById(R.id.StageListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter); 
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            	MethodLab.get(getActivity()).getSetup().setStage(listView.getItemAtPosition(position).toString());
  				MethodLab.get(getActivity()).loadMethods();
  				MethodLab.get(getActivity()).setChosenMethod(MethodLab.get(getActivity()).getMethods());
  				getActivity().finish();
              
              }

         }); 
    
		return view;
	}
	
}