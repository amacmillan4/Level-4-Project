package project.android.bellringing.all;

import java.util.ArrayList;

import android.util.SparseArray;

public class ShortlistedMethods {

	ArrayList<String> types;
	SparseArray<ArrayList<Method2>> map;
	
	public ShortlistedMethods(){
		types = new ArrayList<String>();
		map = new SparseArray<ArrayList<Method2>>();
	}
	
	public void addMethod(Method2 m){
		
		if (!types.contains(m.getType())){
			types.add(m.getType());
			ArrayList<Method2> a = new ArrayList<Method2>();
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
	
	public SparseArray<ArrayList<Method2>> getMap(){
		return map;
	}
	
	
}
