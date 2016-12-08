package com.example.jeff.happyhokie;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jeff on 12/5/2016.
 */

public class SingleDeal {

    private String dealName;
    private int start;
    private int end;
    private ArrayList<String> details;

    public SingleDeal (String name){
        dealName = name;
        getTimes(dealName);

    }


    public String getName(){
        return dealName;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    public ArrayList<String> getDetails(){
        return details;
    }

    public void addDetails(String s){
        if(details == null){
            details = new ArrayList<String>();
        }
        details.add(s);
    }

    public void getTimes(String name){

        if(name.equals("No Food Deals") || name.equals("No Drink Deals") || name.equals("See more...")){
            start = -1;
            end = -1;
        }
        else {
            String sStart = name.substring(name.indexOf("(") + 1, name.indexOf("-"));
            String sEnd = name.substring(name.indexOf("-") + 1, name.length() - 1);

            start = Integer.parseInt(sStart.substring(0, sStart.indexOf(":")));
            end = Integer.parseInt(sEnd.substring(0, sEnd.indexOf(":")));

            if (isPm(sStart)) {
                start += 12;
            }

            if (isPm(sEnd)) {
                end += 12;
            }
        }
    }

    public String getDealWithDetails(){
        String s = dealName + "\n";
        if(details != null) {
            for (String d : details) {
                s += "\t\t" + d + "\n";
            }
        }
        return s;
    }
    public boolean isPm (String time){
        return time.contains("pm");
    }
}
