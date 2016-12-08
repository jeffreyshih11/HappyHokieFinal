package com.example.jeff.happyhokie;

/**
 * Created by Craig
 * Custom adapter for our expandable listview
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private ArrayList<FullDeal> listODeals;

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    DealGetter dealGetter;
    ArrayList<FullDeal> dealList;

    //to know if list is being made in main page or description page
    boolean description = false;


    //constructor for all restaurants on a single day
    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle, InputStream data
    ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        dealGetter = new DealGetter(day, data);
        dealList = dealGetter.getAllDeals();
        //this.expandableListDetail = expandableListDetail;
    }

    //constructor for all days at a single restaurant
    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle, InputStream data, String rest
    ) {
        description = true;
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        dealGetter = new DealGetter(day, data);
        dealList = dealGetter.getWholeWeek(rest);
        //this.expandableListDetail = expandableListDetail;
    }
    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        return this.dealList.get(listPosition).getAllDeals().get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    //Populate the children inside the main list
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);

        SingleDeal deal = (SingleDeal) getChild(listPosition,expandedListPosition);

        if(description){
            String d= deal.getDealWithDetails();
            if(!d.contains("See more...")){
                expandedListTextView.setText(d);
                expandedListTextView.setVisibility(View.VISIBLE);

            }
            else if(d.contains("See more...")){
                expandedListTextView.setVisibility(View.GONE);
            }
            //expandedListTextView.setText(deal.getDealWithDetails());
        }
        else {

            expandedListTextView.setText(deal.getName());
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.dealList.get(listPosition).getAllDeals().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.dealList.get(listPosition);
    }
    //
    @Override
    public int getGroupCount() {
        return this.dealList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }


    @Override
    //Populate the main list
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        if(description){
            ImageView banner = (ImageView) convertView.findViewById(R.id.banner);
            banner.setVisibility(View.GONE);
            TextView listTitleTextView = (TextView) convertView
                    .findViewById(R.id.listTitle);
            String fill = dealList.get(listPosition).sDay;
            listTitleTextView.setTypeface(null, Typeface.BOLD);
            //listTitleTextView.setText(rest2);
            listTitleTextView.setText(fill);
        }
        else{
            TextView listTitleTextView = (TextView) convertView
                    .findViewById(R.id.listTitle);
            listTitleTextView.setVisibility(View.GONE);
            ImageView banner = (ImageView) convertView.findViewById(R.id.banner);
            int pic = getPic(dealList.get(listPosition).getRestaurant());
            banner.setImageResource(pic);
        }


        return convertView;
    }

    //Selects correct banner for each restaurant
    private int getPic(String name){
        switch(name){
            case "622 North": return R.drawable.sixtwotwo;
            case "Blacksburg Taphouse": return R.drawable.blacksburgtaphouse;
            case "Boudreaux's": return R.drawable.boudreauxs;
            case "The Cellar": return R.drawable.cellarlogo;
            case "Champs": return R.drawable.champs;
            case "El Rodeo": return R.drawable.elrodeo;
            case "Hokie House": return R.drawable.hokiehouse;
            case "London Underground Pub": return R.drawable.londonunderground;
            case "Mellow Mushroom": return R.drawable.mellowmushroom;
            case "PK's": return R.drawable.pks;
            case "Sharkey's": return R.drawable.sharkeys;
            case "Top of the Stairs": return R.drawable.tots;
        }
        return -1;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }
    //
    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}