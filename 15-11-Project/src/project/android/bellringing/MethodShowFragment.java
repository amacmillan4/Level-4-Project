package project.android.bellringing;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MethodShowFragment extends Fragment {

	private Method2 method = MethodLab.get(getActivity()).getChosenMethod().get(0);
	private ArrayList<LinesView> linesview = new ArrayList<LinesView>();


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_activity_show, parent, false);

		LinearLayout linLayout = (LinearLayout) v.findViewById(R.id.methodShowLinLayout);
		
		linLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (LinesView l: linesview)
					l.setTextColor(Color.TRANSPARENT);
			}
		});
		
		method.initialize(Integer.parseInt(Utils.stageToNumBells(MethodLab.get(getActivity()).getSetup().getStage())));
		
		String a = "\n";
		String b = "";
		
		method.swapRound();
				
		for(int i = 0; i < method.getBells(); i++)
			method.calcNext();
		
		for(int i = 0; i < method.getBells(); i++)
			b = b + method.calcNext();
				
		method.swapRound();
		
		int x = 0;
		while (x < 2 * (method.getBells() - 1)){
			
			int i = 0;
	
			LinesView displayMethod = new LinesView(getActivity());
			displayMethod.setTextColor(Color.BLACK);
			displayMethod.setClickable(false);
			displayMethod.setBackgroundColor(Color.WHITE);
			displayMethod.setTextSize(24);
			displayMethod.setNumberOfBells(method.getBells());
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(10,20,10, 0);
			displayMethod.setLayoutParams(params);
			
			if (x == 0){
				displayMethod.setText(b + "\n");
				i = b.length();
			}
			
			a = method.calcNext();
			i++;
			
			while (i < ((method.getBells()) * 16)){
				
				displayMethod.append(a);
				displayMethod.drawLines(true);

				if(i % method.getBells() == 0){
					displayMethod.append("\n");
				}
				i++;
				a = method.calcNext() + "";
			}
			
			displayMethod.append(a);
			
			x++;
			linesview.add(displayMethod);
			linLayout.addView(displayMethod);
			
			if (a.equals("\r"))
				break;
		}

		return v;

	}


}
