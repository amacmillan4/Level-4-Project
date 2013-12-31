package project.android.bellringing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MethodShowFragment extends Fragment {

	Method2 method = MethodLab.get(getActivity()).getChosenMethod().get(0);

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_activity_show, parent, false);

		LinearLayout linLayout = (LinearLayout) v.findViewById(R.id.methodShowLinLayout);

		method.initialize();
		
		String a = "\n";
		String b = "";
		
		method.swapRound();
				
		for(int i = 0; i < method.getBells(); i++)
			method.calcNext();
		
		for(int i = 0; i < method.getBells(); i++)
			b = b + method.calcNext();
				
		method.swapRound();
		
		System.out.println(b);

		int x = 0;
		while (x < 2 * (method.getBells() - 1)){
			
			int i = 0;
	
			LinesView displayMethod = new LinesView(getActivity());
			displayMethod.setTextColor(Color.BLACK);
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
			linLayout.addView(displayMethod);
		}

		return v;

	}


}
