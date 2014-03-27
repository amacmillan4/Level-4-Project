package project.android.bellringing.all;

import project.android.bellringing.R;

public class ImageId {

	private int leftDown, leftUp, rightDown, rightUp;
	private boolean handbells;
	
	public ImageId(boolean handbells) {
		this.handbells = handbells;
		
		if (this.handbells){
			leftDown = R.drawable.bell_dl_256;
			rightDown = R.drawable.bell_dr_256;
			leftUp = R.drawable.bell_ul_256;
			rightUp = R.drawable.bell_ur_256;
		}
		else{
			leftDown = R.drawable.towerbell_dl_256;
			rightDown = R.drawable.towerbell_dr_256;
			leftUp = R.drawable.towerbell_ul_256;
			rightUp = R.drawable.towerbell_ur_256;
		}
	}

	public int getLeftDown() {
		return leftDown;
	}

	public int getLeftUp() {
		return leftUp;
	}

	public int getRightDown() {
		return rightDown;
	}

	public int getRightUp() {
		return rightUp;
	}

	public boolean isHandbells() {
		return handbells;
	}
}
