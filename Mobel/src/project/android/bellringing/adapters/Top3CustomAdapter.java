package project.android.bellringing.adapters;

import java.util.ArrayList;

import project.android.bellringing.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Top3CustomAdapter<T> extends ArrayAdapter<String[]> {

	private ArrayList<String[]> entries;

	public Top3CustomAdapter(Context context, int textViewResourceId){
		super(context, textViewResourceId);
	}

	public Top3CustomAdapter(Activity a, int textViewResourceId, ArrayList<String[]> entries) {
		super(a, textViewResourceId, entries);

		this.entries = entries;
	}

	public static class ViewHolder{
		public TextView item1;
		public TextView item2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {

			LayoutInflater vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.setup_top_3, null);

		}

		TextView title = (TextView) v.findViewById(R.id.txvTitle);
		TextView choice = (TextView) v.findViewById(R.id.txvChoice);

		
		title.setText(entries.get(position)[0] + "");
		choice.setText(entries.get(position)[1] + "");


		return v;
	}
}
