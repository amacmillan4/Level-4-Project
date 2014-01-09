package project.android.bellringing;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MethodShowFragment extends Fragment {

	private Method2 method;
	private ArrayList<ShowView> ShowView = new ArrayList<ShowView>();


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_activity_show, parent, false);

		try {
			method = (Method2) MethodLab.get(getActivity()).getChosenMethod().get(0).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		LinearLayout linLayout = (LinearLayout) v.findViewById(R.id.methodShowLinLayout);	
		linLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (ShowView l: ShowView)
					l.changeDisplay();
			}
		});
		
		method.initialize(Integer.parseInt(Utils.stageToNumBells(MethodLab.get(getActivity()).getSetup().getStage())), Composition.PLAIN_COURSE);
		
		boolean finished = false;
		boolean start = true;
		
		while (finished == false){
	
			ShowView displayMethod = new ShowView(getActivity());
			displayMethod.setTextColor(Color.BLACK);
			displayMethod.setClickable(false);
			displayMethod.setBackgroundColor(Color.WHITE);
			displayMethod.setTextSize(14);
			displayMethod.setNumberOfBells(method.getBells());
			displayMethod.setTypeface(Typeface.MONOSPACE);  
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(10,20,10, 0);
			displayMethod.setLayoutParams(params);
			
			int i = 0;
			
			if (start){
				start = false;
				i = 1;
			}
			
			String x = "BLANK";
			
			while (true){
					
				if (x.charAt(0) == '1' && i > 2)
					break;
				
				x = method.getNextLine();
				
				if (x.equals("")){
					finished = true;
					break;
				}
			
				displayMethod.append(x + "\n");
				displayMethod.drawLines(true);
				
				i++;
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
