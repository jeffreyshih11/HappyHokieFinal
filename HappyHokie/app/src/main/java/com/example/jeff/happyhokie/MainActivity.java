package com.example.jeff.happyhokie;
/**
 * Created by Jeff
 * Main activity for our project.  Contains our expandable listview and is the main screen of the
 * application
 */

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        InputStream data = getResources().openRawResource(R.raw.data);
        DealGetter dealGetter = new DealGetter(day, data);
        ArrayList<FullDeal> dealList = dealGetter.getAllDeals();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        //expandableListDetail = ExpandableListDataPump.getData();
        for(int i = 0;i<dealList.size();i++) {
            expandableListTitle = new ArrayList<String>();
            expandableListTitle.add(dealList.get(i).getRestaurant());
        }
        System.out.println(dealList.get(0).getRestaurant());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, getResources().openRawResource(R.raw.data));
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

                //TODO: Intent to more details page

                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                FullDeal newA = (FullDeal) expandableListAdapter.getGroup(groupPosition);

                String title = newA.getRestaurant();

                System.out.println(title);

                // Send Restaurant name param
                intent.putExtra("REST_TITLE", title);
                System.out.println("just pushed title\n");
                if(childPosition == newA.getFoodDeals().size() + newA.getDrinkDeals().size() -1){
                    startActivity(intent);
                    return true;
                }
                return true;
                //return false;
            }
        });
    }

}