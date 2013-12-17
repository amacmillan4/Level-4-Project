package project.android.bellringing;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

public class MethodLab {
	private ArrayList<Method2> arrayMethod;
	private SetupInstructions setup;

	private static MethodLab methodLab;
	private static Context mAppContext;

	private MethodDataTxtSerializer mData;
	private MethodShortlistSerializer mss;

	private ArrayList<Method2> addMethods;

	public void saveData(){
		try {
			mData.saveSetupData(setup);
			mData.saveSetupMethod(arrayMethod);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void saveMethodData(){
		mss.saveData(addMethods, setup.getStage());
	}

	public void loadMethods(){
		try {
			addMethods =  mss.loadData(setup.getStage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public MethodLab(Context appContext) {
		setmAppContext(appContext);

		mData = new MethodDataTxtSerializer(mAppContext, "setup.txt", "method.txt");
		mss = new MethodShortlistSerializer(mAppContext);
		addMethods = new ArrayList<Method2>();

		try {
			setup = mData.loadSetupData();
			arrayMethod = mData.loadSetupMethod();
		} catch (IOException e) {
			setup = new SetupInstructions();
			arrayMethod = new ArrayList<Method2>();
		}
	}


	public static MethodLab get(Context c) {
		if (methodLab == null) {
			methodLab = new MethodLab(c.getApplicationContext());
		}
		return methodLab;
	}

	public void setChosenMethod(ArrayList<Method2> m){
		arrayMethod = m;
	}
	
	public ArrayList<Method2> getChosenMethod(){
		return arrayMethod;
	}

	public SetupInstructions getSetup(){
		return setup;
	}

	public void updateSetup(SetupInstructions s){
		setup = s;
	}

	public void setMethods(ArrayList<Method2> m){
		addMethods = m;
	}

	public void addMethods(ArrayList<Method2> m){

		for(Method2 method: m){
			addMethods.add(method);
		}

	}

	public ArrayList<Method2> getMethods(){
		return addMethods;
	}

	public Context getmAppContext() {
		return mAppContext;
	}


	public void setmAppContext(Context mAppContext) {
		MethodLab.mAppContext = mAppContext;
	}


}

