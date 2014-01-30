package project.android.bellringing.fragments;

import java.util.ArrayList;

import project.android.bellringing.R;
import project.android.bellringing.all.Method;
import project.android.bellringing.all.SingletonData;
import project.android.bellringing.utilities.Composition;
import project.android.bellringing.utilities.Utils;
import project.android.bellringing.views.ShowView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FragmentDisplayMethod extends Fragment {

	private Method method;
	private ArrayList<ShowView> ShowView = new ArrayList<ShowView>();


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_display_method, parent, false);
 
		method  = new Method(SingletonData.get(getActivity()).getChosenMethod().get(0));

		LinearLayout linLayout = (LinearLayout) v.findViewById(R.id.methodShowLinLayout);	
		linLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (ShowView l: ShowView)
					l.changeDisplay();
			}
		});
		
		method.initialize(Integer.parseInt(Utils.stageToNumBells(SingletonData.get(getActivity()).getSetup().getStage())), Composition.PLAIN_COURSE);
		
		boolean finished = false;
		boolean start = true;
		int size = method.getMethodLeadEndLength();
		
		while (size > 20)
			size = size/2;
				
		String x = "";
		
		while (finished == false){
	
			ShowView displayMethod = new ShowView(getActivity());
			displayMethod.setTextColor(Color.BLACK);
			displayMethod.setClickable(false);
			displayMethod.setBackgroundColor(Color.WHITE);
			displayMethod.setTextSize(14);
			displayMethod.setNumberOfBells(method.getPlayingOn());
			displayMethod.setTypeface(Typeface.MONOSPACE);  
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(20,20,20, 0);
			displayMethod.setLayoutParams(params);
			
			int i = 0;
			
			if (start){
				start = false;
				i = 1;
				x = method.getNextLine();

			}
			

			while (i < size){
			
				displayMethod.append(x + "\n");
				displayMethod.drawLines(true);
				
				x = method.getNextLine();
				
				i++;
				
				if (x.equals("1234567890ETABCD".substring(0, method.getPlayingOn()))){
					finished = true;
					break;
				}
			}
			
			if (finished)
				displayMethod.append(x + "\n");
					
			displayMethod.setText(displayMethod.getText().toString().substring(0, displayMethod.getText().toString().length() - 1));
			
			ShowView.add(displayMethod);
			linLayout.addView(displayMethod);
			
			
		}

		return v;

	}


}
