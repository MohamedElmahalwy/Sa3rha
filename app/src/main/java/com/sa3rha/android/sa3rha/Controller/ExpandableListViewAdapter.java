package com.sa3rha.android.sa3rha.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.sa3rha.android.sa3rha.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    HashMap<String, List<String>> expandableListDetail;
    ArrayList<String> expandableListTitle;
    private final Context context;
    List<String>contans;

    public ExpandableListViewAdapter(ArrayList<String> expandableListTitle, HashMap<String, List<String>> expandableListDetail, Context context) {
        this.expandableListDetail = expandableListDetail;
        this.context = context;
        this.expandableListTitle =expandableListTitle;
        contans = new ArrayList<>();
        contans.add("(تحتوي علي تلاته انواع) ");
        contans.add("(تحتوي علي نوعين) ");
    }


    @Override
    public int getGroupCount() {
        return expandableListDetail.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableListDetail.get(expandableListTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandableListDetail.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandableListDetail.get(expandableListTitle.get(childPosition)).get(groupPosition);
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = expandableListTitle.get(groupPosition);
        String con =contans.get(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_category_view, null);
        }
        TextView contan = (TextView) convertView.findViewById(R.id.contan);
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.parent_text);
      //  listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        contan.setText(con);
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(childPosition,groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_category_view, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.child_text);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
