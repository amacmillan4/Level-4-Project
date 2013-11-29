package project.android.bellringing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MethodSetupFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment

		View view = inflater.inflate(R.layout.fragment_activity_start, parent, false);
	
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.test);
		
		View v1 = inflater.inflate(R.layout.listview_top_3, parent);
		TextView title = (TextView) v1.findViewById(R.id.Title);
		TextView choice = (TextView) v1.findViewById(R.id.Choice);
		v1.setClickable(true);

		title.setText("Stage");
		choice.setText("Major");
		ll.addView(v1);
		
		View v2 = inflater.inflate(R.layout.listview_top_3, parent);
		v2.setClickable(true);
		title = (TextView) v2.findViewById(R.id.Title);
		choice = (TextView) v2.findViewById(R.id.Choice);

		title.setText("Composition");
		choice.setText("Plain Course");
		ll.addView(v2);
		
		View v3 = inflater.inflate(R.layout.listview_top_3, parent);
		v3.setClickable(true);
		title = (TextView) v3.findViewById(R.id.Title);
		choice = (TextView) v3.findViewById(R.id.Choice);
		
		title.setText("Method");
		choice.setText("Aberafan");
		ll.addView(v3);

		return view;
	}

}
