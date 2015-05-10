package pe.com.codespace.nurse;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Creado por Carlos on 28/04/2015.
 */
public class PrefSingleton {
    private static PrefSingleton mInstance;
    protected Context mContext;
    private SharedPreferences myPreferences;

    private PrefSingleton(){ }

    public static PrefSingleton getInstance(){
        if (mInstance == null)
            mInstance = new PrefSingleton();
        return mInstance;
    }

    public void Initialize(Context ctxt){
        mContext = ctxt;
        myPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        //PreferenceManager.setDefaultValues(mContext, MyValues.SISTEMA_METRICO, true);
    }

    public void putPreference(String key, boolean value){
        SharedPreferences.Editor e = myPreferences.edit();
        e.putBoolean(key, value);
        e.apply();
    }

    public boolean getPreference(String key, boolean value){
        return myPreferences.getBoolean(key,value);
    }

}
