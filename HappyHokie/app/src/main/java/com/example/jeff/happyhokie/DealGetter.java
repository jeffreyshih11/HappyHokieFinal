package com.example.jeff.happyhokie;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Jeff on 12/2/2016.
 */

//
public class DealGetter {

    String sDay;
    Document xml;
    public DealGetter(int day, InputStream f) {

        sDay = setDay(day);

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            //will have to change the path to file

            Document document = documentBuilder.parse(f);
            document.getDocumentElement().normalize();
            xml = document;
        }
        catch(Exception e){
            System.out.println("fucked up " + e.getStackTrace());
        }
    }

    //gets all the deals at all the restaurants on the same day
    public ArrayList<FullDeal> getAllDeals() {
        //returns list of FullDeal objects
        ArrayList<FullDeal> AllDeals = new ArrayList<FullDeal>();

        try {
            NodeList nodeList = xml.getDocumentElement().getElementsByTagName("Restaurant");
            for (int j = 0 /*nodeList.getLength() - 20*/; j < nodeList.getLength(); j++) {
                Element e = (Element) nodeList.item(j);
                FullDeal f = new FullDeal(e.getAttribute("id"), sDay);    //restaurant name
                //System.out.println(e.getAttribute("id"));

                NodeList children = e.getElementsByTagName("Day");

                for(int i = 0; i < children.getLength(); i++){
                    //System.out.println("\t" + e2.getAttribute("id"));
                    Element e2 = (Element) children.item(i);

                    if(e2.getAttribute("id").equals(sDay)) {
                        NodeList fdeals = e2.getElementsByTagName("Food");
                        NodeList ddeals = e2.getElementsByTagName("Drink");

                        for (int k = 0; k < fdeals.getLength(); k++) {
                            Element foodDeal = (Element) fdeals.item(k);
                            //System.out.println("\t\tFood:" + foodDeal.getTextContent());
                            f.addFood(foodDeal.getTextContent());
                        }
                        for (int x = 0; x < ddeals.getLength(); x++) {
                            Element DrinkDeal = (Element) ddeals.item(x);
                            //System.out.println("\t\tDrink:" + DrinkDeal.getTextContent());
                            f.addDrink(DrinkDeal.getAttribute("name"));
                        }
                    }
                }
                AllDeals.add(f);
            }
            //return null;
        } catch (Exception e) {
            System.out.println("Error while processing resource file: " + e.getStackTrace());
            assert (false);
        }

        return AllDeals;
    }

    //each full deal object corresponds to a day of the week
    public ArrayList<FullDeal> getWholeWeek(String restaurant) {
        //deal all deals from specific restaurant
        ArrayList<FullDeal> AllDeals = new ArrayList<FullDeal>();

        try {
            NodeList nodeList = xml.getDocumentElement().getElementsByTagName("Restaurant");

            for (int j = 0 /*nodeList.getLength() - 20*/; j < nodeList.getLength(); j++) {
                Element e = (Element) nodeList.item(j);
                if(e.getAttribute("id").equals(restaurant)) {
                    for (int d = 1; d < 8; d++) {
                        String tempDay = setDay(d);
                        FullDeal f = new FullDeal(e.getAttribute("id"), tempDay);    //restaurant name
                        //System.out.println(e.getAttribute("id"));

                        NodeList children = e.getElementsByTagName("Day");

                        for (int i = 0; i < children.getLength(); i++) {
                            //System.out.println("\t" + e2.getAttribute("id"));
                            Element e2 = (Element) children.item(i);

                            if (e2.getAttribute("id").equals(tempDay)) {
                                NodeList fdeals = e2.getElementsByTagName("Food");
                                NodeList ddeals = e2.getElementsByTagName("Drink");

                                for (int k = 0; k < fdeals.getLength(); k++) {
                                    Element foodDeal = (Element) fdeals.item(k);
                                    //System.out.println("\t\tFood:" + foodDeal.getTextContent());
                                    f.addFood(foodDeal.getTextContent());
                                }
                                for (int x = 0; x < ddeals.getLength(); x++) {
                                    Element DrinkDeal = (Element) ddeals.item(x);
                                    //System.out.println("\t\tDrink:" + DrinkDeal.getTextContent());
                                    f.addDrink(DrinkDeal.getAttribute("name"));

                                    NodeList details = DrinkDeal.getElementsByTagName("Detail");
                                    for(int m = 0; m < details.getLength(); m++){
                                        f.addDrinkDetails(details.item(m).getTextContent());
                                        //System.out.println(details.item(m).getTextContent());
                                    }
                                }
                            }
                        }
                        AllDeals.add(f);
                    }
                }
            }
            //return null;
        } catch (Exception e) {
            System.out.println("Error while processing resource file: " + e.getStackTrace());
            assert (false);
        }

        return AllDeals;
    }

    //for refresh or for reuse to get all deals at one place
    public String setDay(int newDay){
        switch (newDay){
            case 1: return "Sunday";

            case 2: return "Monday";

            case 3: return "Tuesday";

            case 4: return "Wednesday";

            case 5: return "Thursday";

            case 6: return "Friday";

            case 7: return "Saturday";

        }
        return null;
    }

}
