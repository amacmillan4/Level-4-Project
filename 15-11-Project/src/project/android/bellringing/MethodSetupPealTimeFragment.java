package project.android.bellringing;
import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;


public class MethodSetupPealTimeFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_activity_start_pealtime, parent, false);

		LinearLayout ll = (LinearLayout) view.findViewById(R.id.stageList);

		// Create the array of numbers that will populate the numberpicker
		final String[] hours = {"1","2","3","4","5"};
		final String[] mins = new String[30];

		for(int i=0; i < 30; i++) {
			mins[i] = Integer.toString(i*2);
		}



		//set up number picker
		final NumberPicker nmHours = (NumberPicker) view.findViewById(R.id.hours);
		nmHours.setMaxValue(5);
		nmHours.setMinValue(1);
		nmHours.setWrapSelectorWheel(true);
		nmHours.setDisplayedValues(hours);
		nmHours.setValue(Integer.parseInt(MethodLab.get(getActivity()).getSetup().getPealTime().substring(0,1)));
		nmHours.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		final NumberPicker nmMins = (NumberPicker) view.findViewById(R.id.minutes);
		nmMins.setMaxValue(30);
		nmMins.setMinValue(1);
		nmMins.setWrapSelectorWheel(true);
		nmMins.setDisplayedValues(mins);
		nmMins.setValue(Arrays.asList(mins).indexOf(Integer.parseInt(MethodLab.get(getActivity()).getSetup().getPealTime().substring(2,MethodLab.get(getActivity()).getSetup().getPealTime().length()))));
		nmMins.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		//		nmMins.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
		//
		//			@Override
		//			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		//				// TODO Auto-generated method stub
		//
		//
		// TODO Auto-generated method stub
		//				if (nmHours.getValue() == 1){
		//					//nmMins.setDisplayedValues(Arrays.copyOfRange(mins, 15, 30));
		//					nmMins.setMaxValue(30);
		//					nmMins.setMaxValue(15);
		//					nmMins.setValue(30);
		//				}
		//				else{
		//					nmMins.setDisplayedValues(mins);
		//					nmMins.setMaxValue(30);
		//					nmMins.setMinValue(1);
		//				}	
		//			}
		//		});

		Button btnPeal = (Button) view.findViewById(R.id.btnPealTime);
		btnPeal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String minutes;
				if (Integer.parseInt(mins[nmMins.getValue()-1]) < 10)
					minutes = "0" + mins[nmMins.getValue()-1];
				else
					minutes = mins[nmMins.getValue()-1] + "";
				
				MethodLab.get(getActivity()).getSetup().setPealTime(nmHours.getValue() + ":" + minutes);
				getActivity().finish();

			}
		});


		return view;
	}


}
