package project.android.bellringing.adapters;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

public class AddMethodsArrayAdapter<T> extends ArrayAdapter<String> {

	private ArrayList<Boolean> bitset = new ArrayList<Boolean>();
	private ArrayList<Integer> positionsOfChosenMethods = new ArrayList<Integer>();
	private ArrayList<String> namesOfMethods;
	private ArrayList<CheckedTextView> checkedchkdTxtViews = new ArrayList<CheckedTextView>();

	public AddMethodsArrayAdapter(Context context, int textViewResourceId){
		super(context, textViewResourceId);
	}

	
	public AddMethodsArrayAdapter(Activity a, int textViewResourceId, ArrayList<String> entries) {		
		super(a, textViewResourceId, entries);
		
		//Initialise bitset - list of whether an item has been chosen
		for (int i = 0; i < entries.size(); i++){
			bitset.add(i, false);
		}
		
		namesOfMethods = new ArrayList<String>(entries);
	}
	
	public ArrayList<Integer> getPositionsOfMethods(){
		return positionsOfChosenMethods;
	}


	public  void uncheckAll(){
		
		//Go through ArrayList of selected Methods, remove each and update the bitset
		for(Iterator<Integer> it = positionsOfChosenMethods.iterator(); it.hasNext();){
			Integer i = it.next();
			it.remove();
			bitset.set(i, false);
		}
		
		for(CheckedTextView c: checkedchkdTxtViews)
			c.setChecked(false);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final CheckedTextView ctvMethodName;
		
		//Inflate a new item if one doesn't exist
		if (convertView == null) 
			ctvMethodName = (CheckedTextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_multiple_choice, null);
		else
			ctvMethodName = (CheckedTextView) convertView;
		
		ctvMethodName.setText(namesOfMethods.get(position));
		ctvMethodName.setFocusable(false);

		//Views are re-used so must check bitset to see if it has been previously checked
		if (bitset.get(position) == false)
			ctvMethodName.setChecked(false);
		else
			ctvMethodName.setChecked(true);
		
		ctvMethodName.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//Invert selection
				ctvMethodName.setChecked(!ctvMethodName.isChecked());
				
				//Update ArrayLists storing selected method information
				if (bitset.get(position) == false){
					checkedchkdTxtViews.add(ctvMethodName);
					positionsOfChosenMethods.add((Integer) position);
					bitset.set(position, true);
				}
				else{
					positionsOfChosenMethods.remove((Integer) position);
					bitset.set(position, false);
				}
			}
			
		});


		return ctvMethodName;
	}
}
