package com.example.jeff.happyhokie;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    /*@Test
    public void addition_isCorrect() throws Exception {
        try {
            //ClassLoader classLoader = this.getClass().getClassLoader();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse("C:\\Users\\Jeff\\Documents\\jeffrey\\school\\senior\\design\\happy hokie\\HappyHokie\\app\\src\\main\\res\\Deals.xml");

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            xml.getDocumentElement().normalize();

            String sDay = "Thursday";
            NodeList nodeList = xml.getDocumentElement().getElementsByTagName("Restaurant");

            ArrayList<FullDeal> AllDeals = new ArrayList<FullDeal>();

            for (int j = 0 nodeList.getLength() - 20; j < nodeList.getLength(); j++) {
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
                            Element drinkDeal = (Element) ddeals.item(x);
                            //System.out.println("\t\tDrink:" + DrinkDeal.getTextContent());
                            f.addDrink(drinkDeal.getAttribute("name"));
                        }
                    }
                }

                AllDeals.add(f);
            }

            for(FullDeal f: AllDeals){
                System.out.println(f.toString());
            }
            assert(true);
        } catch (Exception e) {
            System.out.println("Error while processing resource file: " + e.getStackTrace());
            assert(false);

        }

    }

    @Test
    public void getAllDealsTest() {
        DealGetter d = new DealGetter(4);

        ArrayList<FullDeal> nd = d.getAllDeals();
        for(FullDeal fd: nd){
            System.out.println(fd.toString());
        }

    }

    @Test
    public void test1() throws Exception{
        String restaurant = "Champs";
        ArrayList<FullDeal> AllDeals = new ArrayList<FullDeal>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse("C:\\Users\\Jeff\\Documents\\jeffrey\\school\\senior\\design\\happy hokie\\HappyHokie\\app\\src\\main\\res\\Deals.xml");

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            xml.getDocumentElement().normalize();
            NodeList nodeList = xml.getDocumentElement().getElementsByTagName("Restaurant");

            for (int j = 0 nodeList.getLength() - 20; j < nodeList.getLength(); j++) {
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
            for(FullDeal f: AllDeals){
                System.out.println(f.toString());
            }
            assert(true);
        } catch (Exception e) {
            System.out.println("Error while processing resource file: " + e.getStackTrace());
            assert (false);
        }
    }

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
    }*/
}