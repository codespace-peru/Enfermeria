package pe.com.codespace.nurse;

import android.content.Context;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Carlos on 01/03/14.
 */
public class Tools {

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
    }
}

