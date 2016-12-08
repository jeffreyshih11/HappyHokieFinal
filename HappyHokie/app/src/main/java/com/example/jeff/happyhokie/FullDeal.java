package com.example.jeff.happyhokie;



import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Jeff
 */

//Object that stores all the deals on a certain day
public class FullDeal {

    String restaurant;
    String sDay;
    ArrayList<SingleDeal> FoodDeals = new ArrayList<SingleDeal>();
    ArrayList<SingleDeal> DrinkDeals = new ArrayList<SingleDeal>();



    public FullDeal(String rest, String day){
        restaurant = rest;
        sDay = day;
    }
    //get Restaurant
    public String getRestaurant(){
        return restaurant;
    }

    //get food deals
    public List<SingleDeal> getFoodDeals(){
        return FoodDeals;
    }
    //get Drink deals
    public List<SingleDeal> getDrinkDeals(){
        return DrinkDeals;
    }

    //gets all food and drink deals
    public List<SingleDeal> getAllDeals() {
        List<SingleDeal> deals = new ArrayList<SingleDeal>();
        for(int i = 0;i<DrinkDeals.size();i++) {
            deals.add(DrinkDeals.get(i));
        }
        for(int i = 0;i<FoodDeals.size();i++) {
            deals.add(FoodDeals.get(i));
        }
        return deals;
    }
    //populate food deals
    public void addFood(String deal){
        SingleDeal newDeal = new SingleDeal(deal);
        FoodDeals.add(newDeal);
    }

    //populate drink deals
    public void addDrink(String deal){
        SingleDeal newDeal = new SingleDeal(deal);
        DrinkDeals.add(newDeal);
    }


    //adds details to the current drink deal which is also happens to be the last
    //element in the current list of drink deals
    public void addDrinkDetails(String s){
        DrinkDeals.get(DrinkDeals.size()-1).addDetails(s);
    }


    //for testing xml parsing
    public String toString(){
        String s = "";
        s += "Restaurant: " + restaurant + "\n";
        s += "\tDay: " + sDay + "\n";
        for(SingleDeal x: FoodDeals){
            s+= "\tFood: " + x.getName() + "\n";
        }
        for(SingleDeal y: DrinkDeals){
            s+= "\tDrink: " + y.getName() + "\n";
            ArrayList<String> deets = y.getDetails();
            if(deets != null) {
                for(int n = 0; n < deets.size(); n++){
                    s+= "\t\t" + deets.get(n) + "\n";
                }
            }
        }

        return s;
    }




}