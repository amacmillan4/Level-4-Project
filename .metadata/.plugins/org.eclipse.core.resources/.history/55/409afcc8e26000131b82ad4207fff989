package project.android.bellringing;

import project.android.bellringing.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BellImageView extends ImageView {

	public BellImageView(Context context) {
		super(context);
	}

	public BellImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BellImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public void setImageResource(int i){
		super.setImageResource(i);
		super.setTag(i);
	}

	public void switchImage(int resourceID){

		if(resourceID == R.drawable.bell_dl_256)
			setImageResource(R.drawable.bell_ul_256);
		else if(resourceID == R.drawable.bell_dr_256)
			setImageResource(R.drawable.bell_ur_256);
		else if(resourceID == R.drawable.bell_ur_256)
			setImageResource(R.drawable.bell_dr_256);
		else if(resourceID == R.drawable.bell_ul_256)
			setImageResource(R.drawable.bell_dl_256);

	}
}