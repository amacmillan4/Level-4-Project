package project.android.bellringing.all;
import java.util.Arrays;

import project.android.bellringing.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
		View view = inflater.inflate(R.layout.fragment_select_pealtime, parent, false);

		// Create the array of numbers that will populate the numberpicker
		final String[] hours = {"1","2","3","4","5"};
		final String[] mins = new String[30];

		for(int i=0; i < 30; i++) {
			mins[i] = Integer.toString(i*2);
		}
		//set up number picker
		final NumberPicker nmMins = (NumberPicker) view.findViewById(R.id.minutes);
		nmMins.setWrapSelectorWheel(true);
	    nmMins.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		if (MethodLab.get(getActivity()).getSetup().getPealTime().substring(0,1).equals("1")){
			nmMins.setMaxValue(15);
			nmMins.setMinValue(1);
			nmMins.setDisplayedValues(Arrays.copyOfRange(mins, 15, 30));
			nmMins.setValue((Integer.parseInt(MethodLab.get(getActivity()).getSetup().getPealTime().substring(2,MethodLab.get(getActivity()).getSetup().getPealTime().length())))/2 - 14);
			
		}
		else{
			nmMins.setMaxValue(30);
			nmMins.setMinValue(1);
			nmMins.setDisplayedValues(mins);
			nmMins.setValue(Integer.parseInt(MethodLab.get(getActivity()).getSetup().getPealTime().substring(2,MethodLab.get(getActivity()).getSetup().getPealTime().length()))/2 + 1);
		
		}

		final NumberPicker nmHours = (NumberPicker) view.findViewById(R.id.hours);
		nmHours.setMaxValue(5);
		nmHours.setMinValue(1);
		nmHours.setWrapSelectorWheel(true);
		nmHours.setDisplayedValues(hours);
		nmHours.setValue(Integer.parseInt(MethodLab.get(getActivity()).getSetup().getPealTime().substring(0,1)));
		nmHours.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		nmHours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

				//TODO Auto-generated method stub
				if (nmHours.getValue() == 1){
					
					nmMins.setValue(1);
					nmMins.setMaxValue(15);
					nmMins.setMinValue(1);
					nmMins.setDisplayedValues(Arrays.copyOfRange(mins, 15, 30));
					
				}
				else {
					nmMins.setDisplayedValues(mins);
					nmMins.setValue(nmMins.getValue());
					nmMins.setMaxValue(30);
					nmMins.setMinValue(1);
				}	
			}
		});


		Button btnPeal = (Button) view.findViewById(R.id.btnPealTime);
		btnPeal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String minutes;
				if (nmHours.getValue() == 1){
					minutes = ((nmMins.getValue() - 1) * 2) + 30 + "";
				}
				else{
					if (Integer.parseInt(mins[nmMins.getValue()-1]) < 10)
						minutes = "0" + mins[nmMins.getValue()-1];
					else
						minutes = mins[nmMins.getValue()-1] + "";
				}
				

				MethodLab.get(getActivity()).getSetup().setPealTime(nmHours.getValue() + ":" + minutes);
				getActivity().finish();

			}
		});


		return view;
	}


}
