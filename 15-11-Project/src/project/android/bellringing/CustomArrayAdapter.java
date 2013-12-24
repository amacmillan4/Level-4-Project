package project.android.bellringing;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

public class CustomArrayAdapter<T> extends ArrayAdapter<String> {

	private ArrayList<Boolean> bitset = new ArrayList<Boolean>();
	private ArrayList<Integer> selections = new ArrayList<Integer>();
	private ArrayList<String> names;
	private ArrayList<CheckedTextView> ctvs = new ArrayList<CheckedTextView>();

	public CustomArrayAdapter(Context context, int textViewResourceId){
		super(context, textViewResourceId);
	}

	
	public CustomArrayAdapter(Activity a, int textViewResourceId, ArrayList<String> entries) {		
		super(a, textViewResourceId, entries);
		
		for (int i = 0; i < entries.size(); i++){
			bitset.add(i, false);
		}
		
		names = new ArrayList<String>(entries);
	}
	
	public ArrayList<Integer> getMethods(){
		return selections;
	}


	public  void uncheckAll(){
		
		for(Iterator<Integer> it = selections.iterator(); it.hasNext();){
			Integer i = it.next();
			it.remove();
			bitset.set(i, false);
		}
		
		for(CheckedTextView c: ctvs)
			c.setChecked(false);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		 CheckedTextView c;
		
		if (convertView == null) {

			LayoutInflater vi = LayoutInflater.from(getContext());
			 c = (CheckedTextView) vi.inflate(android.R.layout.simple_list_item_multiple_choice, null);

		}
		else{
			c = (CheckedTextView) convertView;
		}
		
		final CheckedTextView ctv = c;
		ctv.setText(names.get(position));
		
		if (bitset.get(position) == false)
			ctv.setChecked(false);
		else
			ctv.setChecked(true);
		
		ctv.setFocusable(false);
		
		ctv.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				ctv.setChecked(!ctv.isChecked());
				
				if (bitset.get(position) == false){
					ctvs.add(ctv);
					selections.add((Integer) position);
					bitset.set(position, true);
				}
				else{
					bitset.set(position, false);
					selections.remove((Integer) position);
				}
			}
			
		});


		return ctv;
	}
}
