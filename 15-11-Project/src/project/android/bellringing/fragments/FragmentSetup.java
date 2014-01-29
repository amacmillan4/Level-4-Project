package project.android.bellringing.fragments;

import java.util.ArrayList;

import project.android.bellringing.R;
import project.android.bellringing.activities.ActivitySelectComposition;
import project.android.bellringing.activities.ActivitySelectMethod;
import project.android.bellringing.activities.ActivityPlayMethod;
import project.android.bellringing.activities.ActivitySelectPealTime;
import project.android.bellringing.activities.ActivitySelectStage;
import project.android.bellringing.all.Method;
import project.android.bellringing.all.SingletonData;
import project.android.bellringing.all.SetupInstructions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSetup extends Fragment {

	//Variables
	TextView txvStage, txvComposition, txvMethod, txvPealTime;
	Switch swiBellType, swiStopAtRounds, swiHandstrokeGap, swiWaitForMe, swiScoreBlows, swiScoreSummary, swiOrientationLock;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onResume(){
		super.onResume();
		updateView();
	}

	@Override
	public void onPause(){
		super.onPause();
		SingletonData.get(getActivity()).saveData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_setup, parent, false);
		
		//Lock Orientation or Not
		if (SingletonData.get(getActivity()).getSetup().isOrientationLock())
			getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		else
			getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

		//Set up Switch to choose what bells the user will play the method on 
		swiBellType = (Switch) view.findViewById(R.id.setupSwiBellType);
		swiBellType.setChecked(SingletonData.get(getActivity()).getSetup().getHandbellsOrNot());
		swiBellType.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SingletonData.get(getActivity()).getSetup().setHandbellsOrNot(isChecked);

			}
		});
		
		//Set up Switch to choose whether the User will stop at Rounds
		swiStopAtRounds = (Switch) view.findViewById(R.id.setupSwiStopAtRounds);
		swiStopAtRounds.setChecked(SingletonData.get(getActivity()).getSetup().isStopAtRounds());
		swiStopAtRounds.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SingletonData.get(getActivity()).getSetup().setStopAtRounds(isChecked);

			}
		});

		///Set up Switch to control Handstroke Gap
		swiHandstrokeGap = (Switch) view.findViewById(R.id.setupSwiHandstrokeGap);
		swiHandstrokeGap.setChecked(SingletonData.get(getActivity()).getSetup().isHandstrokeGap());
		swiHandstrokeGap.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SingletonData.get(getActivity()).getSetup().setHandstrokeGap(isChecked);

			}
		});

		//Set up Switch to control whether the system should wait for the User
		swiWaitForMe = (Switch) view.findViewById(R.id.setupSwihWaitForMe);
		swiWaitForMe.setChecked(SingletonData.get(getActivity()).getSetup().isWaitForMe());
		swiWaitForMe.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SingletonData.get(getActivity()).getSetup().setWaitForMe(isChecked);

			}
		});

		//Setup up Switch to choose whether the system will display a score for Bell timing
		swiScoreBlows = (Switch) view.findViewById(R.id.setupSwiScoreBlows);
		swiScoreBlows.setChecked(SingletonData.get(getActivity()).getSetup().isScoreBlows());
		swiScoreBlows.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SingletonData.get(getActivity()).getSetup().setScoreBlows(isChecked);

			}
		});


		//Setup up Switch to choose whether the Orientation of the App will be locked in Portrait
		swiOrientationLock = (Switch) view.findViewById(R.id.setupSwiOrientationLock);
		swiOrientationLock.setChecked(SingletonData.get(getActivity()).getSetup().isOrientationLock());
		swiOrientationLock.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SingletonData.get(getActivity()).getSetup().setOrientationLock(isChecked);


				if (isChecked)
					getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				else
					getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

			}
		});

		
		//TODO
		swiScoreSummary = (Switch) view.findViewById(R.id.setupSwiScoreSummary);
		swiScoreSummary.setChecked(SingletonData.get(getActivity()).getSetup().isScoreSummary());
		swiScoreSummary.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SingletonData.get(getActivity()).getSetup().setScoreSummary(isChecked);

			}
		});

		
		//Get handle on layout for first 3 options
		LinearLayout llFirstThree = (LinearLayout) view.findViewById(R.id.setupllFirstThree);

		//Setup View to let User choose Stage
		View vStage = inflater.inflate(R.layout.setup_top_3, parent);
		
		TextView title = (TextView) vStage.findViewById(R.id.txvTitle);
		title.setText("Stage");
		txvStage = (TextView) vStage.findViewById(R.id.txvChoice);
		
		vStage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				//Start new Activity to allow user to select their chosen stage
				Intent i = new Intent(getActivity(), ActivitySelectStage.class);
				getActivity().startActivity(i);

			}
		});

		//Setup View to allow the user to choose Composition
		View vComposition = inflater.inflate(R.layout.setup_top_3, parent);
		vComposition.setClickable(true);
		title = (TextView) vComposition.findViewById(R.id.txvTitle);
		title.setText("Composition");

		txvComposition = (TextView) vComposition.findViewById(R.id.txvChoice);

		vComposition.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent i = new Intent(getActivity(), ActivitySelectComposition.class);
				getActivity().startActivity(i);

			}
		});

		//Setup View to allow the user to choose Method
		View vMethod = inflater.inflate(R.layout.setup_top_3, parent);
		
		title = (TextView) vMethod.findViewById(R.id.txvTitle);
		title.setText("Method");
		txvMethod = (TextView) vMethod.findViewById(R.id.txvChoice);

		vMethod.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getActivity(), ActivitySelectMethod.class);
				getActivity().startActivity(i);

			}
		});

		// Get a Handle on the Layout containing the last 5 options
		LinearLayout llLastFive = (LinearLayout) view.findViewById(R.id.setupllPealTime);

		//Setup View to choose the Peal Time
		View vPealTime = inflater.inflate(R.layout.setup_top_3, parent);
		
		title = (TextView) vPealTime.findViewById(R.id.txvTitle);
		title.setText("Peal Time");
		txvPealTime = (TextView) vPealTime.findViewById(R.id.txvChoice);

		vPealTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getActivity(), ActivitySelectPealTime.class);
				getActivity().startActivity(i);

			}
		});


		//Button to open Fragment allowing method to be played
		Button btnRun = (Button) view.findViewById(R.id.setupBtnStartRun);
		btnRun.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				updateView();

				//Do not open next Fragment if the user has not selected a method
				if (!(SingletonData.get(getActivity()).getChosenMethod().size() == 0)){
					Intent i = new Intent(getActivity(), ActivityPlayMethod.class);
					getActivity().startActivity(i);
				}
				else
					Toast.makeText(getActivity().getApplicationContext(), "Please Choose a Method", Toast.LENGTH_SHORT).show();

			}
		});
		
		
		//Add Views to parent Layout
		llFirstThree.addView(vStage);
		llFirstThree.addView(vComposition);
		llFirstThree.addView(vMethod);

		llLastFive.addView(vPealTime);

		//Ensure View is kept up-to-date
		updateView();

		return view;
	}

	public void updateView(){

		//Retrieve Data from Singleton class
		SetupInstructions s = SingletonData.get(getActivity()).getSetup();
		ArrayList<Method> m = SingletonData.get(getActivity()).getChosenMethod();

		//Update TextViews and Switches
		txvStage.setText(s.getStage());
		txvComposition.setText(s.getComposition());

		if (m.size() == 0)
			txvMethod.setText("");
		else
			txvMethod.setText(m.get(0).getMethodName());

		txvPealTime.setText(s.getPealTime());
		swiBellType.setChecked(s.getHandbellsOrNot());
		swiStopAtRounds.setChecked(s.isStopAtRounds());
		swiHandstrokeGap.setChecked(s.isHandstrokeGap());
		swiWaitForMe.setChecked(s.isWaitForMe());
		swiScoreBlows.setChecked(s.isScoreBlows());
		swiScoreSummary.setChecked(s.isScoreSummary());
		swiOrientationLock.setChecked(s.isOrientationLock());
	}

	public class WriteChanges extends AsyncTask<Void,Void,Void>{


		@Override
		protected synchronized Void doInBackground(Void... arg0) {




			return null;
		}

	}



}
