package project.android.bellringing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MethodShowFragment extends Fragment {

	Method method = new Method("Aberafan", "&-36-14-58-16-34-58-56-58,12", 100, 8);

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_activity_show, parent, false);

		LinearLayout linLayout = (LinearLayout) v.findViewById(R.id.methodShowLinLayout);

		String a = method.calcNext() + "";

		int x = 0;
		while (x < 16){
			
			int i = 1;
	
			LinesView displayMethod = new LinesView(getActivity());
			if (x == 0)
				displayMethod.setText("12345678\n");
		
			displayMethod.setTextColor(Color.BLACK);
			displayMethod.setBackgroundColor(Color.WHITE);
			displayMethod.setTextSize(24);
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(10, 0,10, 0);
			displayMethod.setLayoutParams(params);
			
			
			while (i < ((method.getBells() + 1) * 13) + 4){
				displayMethod.append(a);
				if(i % method.getBells() == 0){
					displayMethod.append("\n");
				}
				i++;
				a = method.calcNext() + "";
			}
			
			if (x != 0){
				i = 1;
				while (i < (method.getBells() + 1)){
					displayMethod.append(a);
					if(i % method.getBells() == 0){
						displayMethod.append("\n");
					}
					i++;
					a = method.calcNext() + "";
				}
			}
			x++;
			displayMethod.drawLines(true);
			linLayout.addView(displayMethod);
		}

		return v;

	}


}
