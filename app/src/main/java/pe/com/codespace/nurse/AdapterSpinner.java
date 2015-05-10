package pe.com.codespace.nurse;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Carlos on 08/05/2015.
 */
class AdapterSpinner extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] items;

    public AdapterSpinner(Activity ctx, int resource, String[] listItems) {
        super(ctx, resource, listItems);
        this.context = ctx;
        this.items = listItems;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null){
            LayoutInflater inflater = context.getLayoutInflater();
            row=inflater.inflate(R.layout.spinner_layout,parent,false);
        }
        String item = items[position];
        if(item != null){
            TextView textView = (TextView) row.findViewById(R.id.spinnerItem);
            if(textView != null)
                textView.setText(item);
        }

        return row;
    }
}
