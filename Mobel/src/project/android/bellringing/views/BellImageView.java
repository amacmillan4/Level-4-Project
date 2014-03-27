package project.android.bellringing.views;

import project.android.bellringing.all.ImageId;
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

	/** 
	 * @param resourceID
	 * @param imageId
	 * 
	 * Switches the image from up to down or down to up
	 * 
	 */
	public void switchImage(int resourceID, ImageId imageId){

		if(resourceID == imageId.getLeftDown())
			setImageResource(imageId.getLeftUp());
		else if(resourceID == imageId.getRightDown())
			setImageResource(imageId.getRightUp());
		else if(resourceID == imageId.getRightUp())
			setImageResource(imageId.getRightDown());
		else if(resourceID == imageId.getLeftUp())
			setImageResource(imageId.getLeftDown());
	}
	
	/** 
	 * @param resourceID
	 * @param imageId
	 * 
	 * Puts image back to starting position
	 * 
	 */
	public void restart(int resourceID, ImageId imageId){
		if(resourceID == imageId.getLeftUp())
			setImageResource(imageId.getLeftDown());
		else if(resourceID == imageId.getRightUp())
			setImageResource(imageId.getRightDown());
	}
}