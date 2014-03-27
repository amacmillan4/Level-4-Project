package project.android.bellringing.fragments;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import project.android.bellringing.R;
import project.android.bellringing.activities.ActivityAddMethodsFromFile;
import project.android.bellringing.activities.ActivityDisplayMethod;
import project.android.bellringing.activities.ActivityPlayMethod;
import project.android.bellringing.activities.ActivitySelectComposition;
import project.android.bellringing.activities.ActivitySelectMethod;
import project.android.bellringing.activities.ActivitySelectPealTime;
import project.android.bellringing.activities.ActivitySetup;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentHelp extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_help, parent, false);
		String help = "";
		try{
			BufferedReader br = null;

			ComponentName cm = getActivity().getIntent().getParcelableExtra("Parent");
			
			if(cm.getClassName().equals(ActivitySelectComposition.class.toString().split(" ")[1])){
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/compositionsView.htm")));
			}
			else if(cm.getClassName().equals(ActivitySelectPealTime.class.toString().split(" ")[1])){
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/pealTimeView.htm")));
			}
			else if(cm.getClassName().equals(ActivityAddMethodsFromFile.class.toString().split(" ")[1])){
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/msLibView.htm")));
			}
			else if(cm.getClassName().equals(ActivitySelectMethod.class.toString().split(" ")[1])){
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/methodsView.htm")));
			}
			else if(cm.getClassName().equals(ActivityPlayMethod.class.toString().split(" ")[1])){
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/mainView.htm")));
			}
			else if(cm.getClassName().equals(ActivitySetup.class.toString().split(" ")[1])){
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/flipsideView.htm")));
			}
			else if(cm.getClassName().equals(ActivityDisplayMethod.class.toString().split(" ")[1])){
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/blView.htm")));
			}
			else{
				br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/AllMobelHelp.htm")));
			}

			String line;
			line = br.readLine();

			while(line != null){
				line = br.readLine();
				help += line;
			}
			br.close();	

		} catch (Exception e){
			System.err.println("Error");

		} 

		TextView helpTxv = (TextView) view.findViewById(R.id.helpTxv);
		helpTxv.setText(Html.fromHtml(help));

		return view;
	}
}
