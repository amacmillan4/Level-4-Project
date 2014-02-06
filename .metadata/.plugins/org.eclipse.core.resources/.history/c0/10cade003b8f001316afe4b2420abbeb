package project.android.bellringing.activities;

import project.android.bellringing.R;
import project.android.bellringing.all.SingletonData;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public abstract class AbstractActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (SingletonData.get(this).getSetup().isOrientationLock())
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
