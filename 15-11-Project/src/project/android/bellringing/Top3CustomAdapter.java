package project.android.bellringing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Top3CustomAdapter<T> extends ArrayAdapter<T> {

	    private ArrayList<T> entries;
	    private Activity activity;
	 
	    public Top3CustomAdapter(Activity a, int textViewResourceId, ArrayList<T> entries) {
	        super(a, textViewResourceId, entries);
	        this.entries = entries;
	        this.activity = a;
	    }
	 
	    public static class ViewHolder{
	        public TextView item1;
	        public TextView item2;
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        ViewHolder holder;
	        if (v == null) {
	            LayoutInflater vi =
	                (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.grid_item, null);
	            holder = new ViewHolder();
	            holder.item1 = (TextView) v.findViewById(R.id.big);
	            holder.item2 = (TextView) v.findViewById(R.id.small);
	            v.setTag(holder);
	        }
	        else
	            holder=(ViewHolder)v.getTag();
	 
	        final Custom custom = entries.get(position);
	        if (custom != null) {
	            holder.item1.setText(custom.getFirst());
	            holder.item2.setText(custom.getSecond());
	        }
	        return v;
	    }
	 
	- See more at: http://sogacity.com/how-to-make-a-custom-arrayadapter-for-listview/#sthash.f4qTR06W.dpuf
}
