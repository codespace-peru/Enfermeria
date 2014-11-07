package pe.com.codespace.nurse;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 23/11/13.
 */
public class AdapterExpandableList extends BaseExpandableListAdapter {
    private Context context;
    private List<String> _listHeader;
    private HashMap<String, List<String>> _listChild;
    //private LayoutInflater inflater;


    public AdapterExpandableList(Context context, List<String> listHeader, HashMap<String, List<String>> listChild){
        this._listChild = listChild;
        this._listHeader = listHeader;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return this._listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listChild.get(this._listHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listChild.get(this._listHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {

        View row = view;
        TextView textView = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_group, null);
        }
        textView = (TextView) row.findViewById(R.id.tvGroup);
        textView.setText(_listHeader.get(groupPosition));
        row.setPadding(10,0,0,0);
        return row;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        View row = view;
        //ArrayList<String> child = (ArrayList<String>)_listChild.get(groupPosition);
        TextView textView = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, null);
        }
        textView = (TextView) row.findViewById(R.id.tvItem);
        String text = (String) getChild(groupPosition, childPosition);
        textView.setText(text);
        row.setPadding(20,0,0,0);
        return row;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}




