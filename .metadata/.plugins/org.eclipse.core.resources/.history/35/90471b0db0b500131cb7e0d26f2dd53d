package project.android.bellringing.all;

import java.io.IOException;
import java.util.ArrayList;

import project.android.bellringing.fileHandling.MethodDataTxtSerializer;
import project.android.bellringing.fileHandling.MethodShortlistSerializer;

import android.content.Context;

public class SingletonData {
	private ArrayList<Method> arrayMethod;
	private SetupInstructions setup;

	private static SingletonData methodLab;
	private static Context mAppContext;

	private MethodDataTxtSerializer mData;
	private MethodShortlistSerializer mss;

	private ArrayList<Method> addMethods;

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
			
			for(Method m: addMethods)
				System.out.println("METHODLAB LOADED: " + m.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public SingletonData(Context appContext) {
		setmAppContext(appContext);

		mData = new MethodDataTxtSerializer(mAppContext, "setup.txt", "method.txt");
		mss = new MethodShortlistSerializer(mAppContext);
		addMethods = new ArrayList<Method>();

		try {
			setup = mData.loadSetupData();
			arrayMethod = mData.loadSetupMethod();

			
			
		} catch (IOException e) {
			setup = new SetupInstructions();
			arrayMethod = new ArrayList<Method>();
		}
	}


	public static SingletonData get(Context c) {
		if (methodLab == null) {
			methodLab = new SingletonData(c.getApplicationContext());
		}
		return methodLab;
	}

	public void setChosenMethod(ArrayList<Method> m){
		arrayMethod = m;
	}
	
	public ArrayList<Method> getChosenMethod(){
		return arrayMethod;
	}

	public SetupInstructions getSetup(){
		return setup;
	}

	public void updateSetup(SetupInstructions s){
		setup = s;
	}

	public void setMethods(ArrayList<Method> m){
		addMethods = m;
	}

	public void addMethods(ArrayList<Method> m){

		for(Method method: m){
			addMethods.add(method);
			System.out.println("ADDING :" + method.toString());
		}

	}
	
	public void removeMethods(Method m){
		addMethods.remove(m);
	}

	public ArrayList<Method> getMethods(){
		return addMethods;
	}

	public Context getmAppContext() {
		return mAppContext;
	}


	public void setmAppContext(Context mAppContext) {
		SingletonData.mAppContext = mAppContext;
	}


}

