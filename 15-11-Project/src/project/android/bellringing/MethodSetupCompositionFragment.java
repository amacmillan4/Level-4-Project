package project.android.bellringing;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MethodSetupCompositionFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_activity_start_composition, parent, false);

		LinearLayout ll = (LinearLayout) view.findViewById(R.id.stageList);

		String[] options = {"Plain Course", "Touch with Bobs", "Touch with Singles", "Touch with Bobs & Singles", "Touch of Spliced"};

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
				getActivity().finish();

			}
		});

		title.setText(text);

		return v1;

	}

}
