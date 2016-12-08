package com.example.jeff.happyhokie;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

import static com.example.jeff.happyhokie.R.id.expandableListView;
//import com.google.android.gms.maps.GoogleMap;


/*
 *  Created by Lauren Cahill
 */

public class DescriptionActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    private TextView title;
    private TextView deals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        // Get Restaurant & Day
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        String rest = params.getString("REST_TITLE");

        InputStream data = getResources().openRawResource(R.raw.data);
        //change day for future to open specific day
        DealGetter dealGetter = new DealGetter(0, data);
        ArrayList<FullDeal> weekDeals = dealGetter.getWholeWeek(rest);

        // Get Activity Items
        title = (TextView) findViewById(R.id.restName);
        ImageButton dir = (ImageButton) findViewById(R.id.getLocButton);
        dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence search = title.getText();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.2296, -80.4139?q="+search));
                startActivity(intent);
            }
        });
        //deals = (TextView) findViewById(R.id.deals);

        // Load Details
        loadPage(rest);
        //System.out.println("LOADED PAGE!");

        // Initialize Map
    }

    public void loadPage(String restaurant) {

        // Set Title
        title.setText(restaurant);

        //Just to create DealGetter object with correct day, day is not used elsewhere
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        //parse xml and populate list
        InputStream data = getResources().openRawResource(R.raw.data);
        DealGetter dealGetter = new DealGetter(day, data);
        ArrayList<FullDeal> weekDeals = dealGetter.getWholeWeek(restaurant);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListTitle = new ArrayList<String>();
        //expandableListDetail = ExpandableListDataPump.getData();
        for(int i = 0;i<weekDeals.size();i++) {

            expandableListTitle.add(weekDeals.get(i).sDay);
        }
        System.out.println(weekDeals.get(0).getRestaurant());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, getResources().openRawResource(R.raw.data), restaurant);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                //What happens when a group is expanded
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                //What happens when a group is collapsed

            }
        });
        /*
         *
         */
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

    //Used to try and open google maps view within app.
    /*public void loadMap(View view) {
        CharSequence search = title.getText();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.2296, -80.4139?q="+search));
        startActivity(intent);
    }*/



}