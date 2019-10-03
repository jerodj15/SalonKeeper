/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtherMethods;

import java.util.ArrayList;
import java.util.List;


public class Information {
    
    // Method for getting a list of all 50 states
    public static List<String> getStates()
    {
        List<String> statesList = new ArrayList<>();
        statesList.add("Alabama");
        statesList.add("Alaska");
        statesList.add("Arkansas");
        statesList.add("California");
        statesList.add("Colorado");
        statesList.add("Connecticut");
        statesList.add("Delaware");
        statesList.add("Florida");
        statesList.add("Georgia");
        statesList.add("Hawaii");
        statesList.add("Idaho");
        statesList.add("Illinois");
        statesList.add("Indiana");
        statesList.add("Iowa");
        statesList.add("Kansas");
        statesList.add("Kentucky");
        statesList.add("Louisiana");
        statesList.add("Maine");
        statesList.add("Maryland");
        statesList.add("Massachusetts");
        statesList.add("Michigan");
        statesList.add("Minnesota");
        statesList.add("Mississippi");
        statesList.add("Missouri");
        statesList.add("Montana");
        statesList.add("Nebraska");
        statesList.add("Nevada");
        statesList.add("New Hampshire");
        statesList.add("New Jersey");
        statesList.add("New Mexico");
        statesList.add("New York");
        statesList.add("North Carolina");
        statesList.add("North Dakota");
        statesList.add("Ohio");
        statesList.add("Oklahoma");
        statesList.add("Oregon");
        statesList.add("Pennsylvania");
        statesList.add("Rhode Island");
        statesList.add("South Carolina");
        statesList.add("South Dakota");
        statesList.add("Tennessee");
        statesList.add("Texas");
        statesList.add("Utah");
        statesList.add("Vermont");
        statesList.add("Virginia");
        statesList.add("Washington");
        statesList.add("West Virginia");
        statesList.add("Wisconsin");
        statesList.add("Wyoming");
        
        return statesList;
    }
    public static List<String> getAMPM()
    {
        List<String> ampmList = new ArrayList<>();
        ampmList.add("AM");
        ampmList.add("PM");
        return ampmList;
    }
    
    public static List<Integer> hoursList()
    {
        List<Integer> hoursList = new ArrayList<>();
        hoursList.add(12);
        hoursList.add(1);
        hoursList.add(2);
        hoursList.add(3);
        hoursList.add(4);
        hoursList.add(5);
        hoursList.add(6);
        hoursList.add(7);
        hoursList.add(8);
        return hoursList;
    }
    // Method for creating the incremental appointment times combo box
    public static List<Integer> minutesList()
    {
        List<Integer> minutesList = new ArrayList<>();
        minutesList.add(00);
        minutesList.add(15);
        minutesList.add(30);
        minutesList.add(45);
        return minutesList;
    }
}
