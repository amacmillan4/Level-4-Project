package project.android.bellringing;

import java.util.ArrayList;

import android.content.Context;

public class MethodLab {
    private ArrayList<Method> arrayMethod;

    private static MethodLab methodLab;
    private Context mAppContext;

    private MethodLab(Context appContext) {
        mAppContext = appContext;
        arrayMethod = new ArrayList<Method>();
        for (int i = 0; i < 100; i++) {
           // Method m = new Method();
            //method.add();
        }
    }

    public static MethodLab get(Context c) {
        if (methodLab == null) {
        	methodLab = new MethodLab(c.getApplicationContext());
        }
        return methodLab;
    }

    public ArrayList<Method> getCrimes() {
        return arrayMethod;
    }
}

