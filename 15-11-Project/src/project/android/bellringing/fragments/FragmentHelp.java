package project.android.bellringing.fragments;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import project.android.bellringing.R;
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
			br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Help/AllMobelHelp.htm")));

			String line;
			line = br.readLine();

			while(line != null){
				line = br.readLine();
				help += line;
			}
			br.close();	

		} catch (Exception e){
			//Ignore, Only happens when starting afresh

		} 
		
		TextView helpTxv = (TextView) view.findViewById(R.id.helpTxv);
		helpTxv.setText(Html.fromHtml(help));

		return view;
	}
}