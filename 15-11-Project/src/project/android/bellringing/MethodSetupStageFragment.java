package project.android.bellringing;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


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

		LinearLayout ll = (LinearLayout) view.findViewById(R.id.stageList);

		for(String s: options){
			ll.addView(setup(inflater, R.layout.custom_selection, parent, s));
		}

		return view;
	}
	
	private View setup(LayoutInflater inflater, int layout, ViewGroup parent, final String text){
		View v1 = inflater.inflate(layout, parent);
		TextView title = (TextView) v1.findViewById(R.id.CustomTitle);
		v1.setClickable(true);
		
		v1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MethodLab.get(getActivity()).getSetup().setStage(text);
				MethodLab.get(getActivity()).loadMethods();
				MethodLab.get(getActivity()).setChosenMethod(MethodLab.get(getActivity()).getMethods());
				getActivity().finish();
				
			}
		});

		title.setText(text);
		
		return v1;
		
	}

}
