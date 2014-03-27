package project.android.bellringing.all;

import java.util.ArrayList;

import android.util.SparseArray;

public class ShortlistedMethods {

	ArrayList<String> types;
	SparseArray<ArrayList<Method>> map;
	
	public ShortlistedMethods(){
		types = new ArrayList<String>();
		map = new SparseArray<ArrayList<Method>>();
	}
	
	public void addMethod(Method m){
		
		if (!types.contains(m.getType())){
			types.add(m.getType());
			ArrayList<Method> a = new ArrayList<Method>();
			a.add(m);
			map.put(m.getType().hashCode(), a);
		}
		else{
			map.get(m.getType().hashCode()).add(m);
		}
	}
	
	public ArrayList<String> getKeys(){
		return types;
	}
	
	public SparseArray<ArrayList<Method>> getMap(){
		return map;
	}
	
	
}
