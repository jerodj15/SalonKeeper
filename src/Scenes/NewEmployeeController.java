/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBConnect;
import Database.DBRequest;
import Database.DBSave;
import MainClass.SalonKeeper;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class NewEmployeeController{

    @FXML
    private Button backButton;
    @FXML
    private TextField employeeIdTF;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private ComboBox jobTitleCB;
    @FXML
    private Button showPasswordButton;
    @FXML
    private DatePicker birthdateDP;
    @FXML
    private ListView offeredLV;
    @FXML
    private ListView notOfferedLV;
    @FXML
    private ComboBox serviceTypeCB;
    @FXML
    private ComboBox serviceSubTypeCB;
    @FXML
    private Button addAllButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button removeAllButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML 
    private PasswordField passwordField;

     private SalonKeeper myKeeper;

     // Lists for database information
     private static List<List> jobTitlesInfo;
     private static List<List> serviceTypes;
     private static List<String> serviceSubTypes;
     private static List<String> offeredList = new ArrayList<>();
     private static List<List> allServicesList = DBRequest.getAllServices();
     
     
   
    // Method for initial setup of the new employee screen
    public void setupNewEmployeeScreen(SalonKeeper mySalonKeeper)
    {
        this.myKeeper = mySalonKeeper;
        
        jobTitlesInfo = DBRequest.getAllJobTitles();
        serviceTypes = DBRequest.getAllServiceTypes();
        
        List<String> jobTitles = new ArrayList<>();
        List<String> serviceTitles = new ArrayList<>();
        List<String> serviceSubTitles = new ArrayList<>();
        
        // Populate the service types combo box
        for (int i = 0; i < serviceTypes.size(); i++)
        {
            serviceTitles.add((String) serviceTypes.get(i).get(1));
        }
        serviceTypeCB.getItems().setAll(serviceTitles);
        
        // Populate the jobTitles comboBox
        for (int i = 0; i < jobTitlesInfo.size(); i++)
        {
            jobTitles.add((String) jobTitlesInfo.get(i).get(1));
        }
        jobTitleCB.getItems().setAll(jobTitles);
        setupOfferedServices();
        // Setup onclick listener for the subtype combobox
        serviceSubTypeCB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int typeSelection = serviceTypeCB.getSelectionModel().getSelectedIndex();
                serviceSubTypes = DBRequest.getServiceSubTypeById(typeSelection + 1);
                serviceSubTypeCB.getItems().setAll(serviceSubTypes);
            }
        });
    }
    
    // Method for pressing the back button
    @FXML
    public void backButtonPressed()
    {
        myKeeper.adminEmployeeMenu();
    }
    // Method for pressing the save button
    @FXML
    public void saveButtonPressed()
    {
        String firstName = null, lastName = null, birthdate, jobTitle, passWord = null;
        int jobTitleId, stylistId;
        int doNext = 0;
        while(doNext < 2)
        {
            if (doNext == 0)
            {
                List<Object> employeeInfo = new ArrayList<>();
                
                firstName = firstNameTF.getText();
                lastName = lastNameTF.getText();
                jobTitle = jobTitleCB.getSelectionModel().getSelectedItem().toString();
                jobTitleId = DBRequest.getJobTitleId(jobTitle);
                birthdate = birthdateDP.getEditor().getText();
                passWord = passwordField.getText();
                employeeInfo.add(firstName);
                employeeInfo.add(lastName);
                employeeInfo.add(jobTitleId);
                employeeInfo.add(birthdate);
                employeeInfo.add(passWord);
                DBSave.saveNewEmployee(employeeInfo);
                System.out.println(employeeInfo.toString());
                doNext++;
            }
            if (doNext == 1)
            {
                stylistId = DBRequest.getEmployeeId(firstName, lastName, passWord);
                List<String> newlyOfferedList = new ArrayList<>();
                List<String> notOfferedList = new ArrayList<>();
                List<Integer> nonOfferedIds = new ArrayList<>();
                List<Integer> serviceIdsList = new ArrayList<>();
                int dooNext = 0;
                while (dooNext < 3)
                {
                    if (dooNext == 0)
                    {
                        for (int i = 0; i < offeredLV.getItems().size(); i++)
                        {
                            newlyOfferedList.add((String) offeredLV.getItems().get(i));
                        }
                        for (int i = 0; i < notOfferedLV.getItems().size(); i++)
                        {
                            notOfferedList.add((String) notOfferedLV.getItems().get(i));
                        }
                        dooNext++;
                    }
                    if (dooNext == 1)
                    {
                        for (int i = 0; i < newlyOfferedList.size(); i++)
                        {
                            int serviceID = DBRequest.getServiceIdByTitle(newlyOfferedList.get(i));
                            serviceIdsList.add(serviceID);
                        }
                        for (int i = 0; i < notOfferedList.size(); i++)
                        {
                            int nonOfferedID = DBRequest.getServiceIdByTitle(notOfferedList.get(i));
                            nonOfferedIds.add(nonOfferedID);
                        }
                        dooNext++;
                    }
                    if (dooNext == 2)
                    {
                        DBSave.saveNewOfferedServices(serviceIdsList, nonOfferedIds , stylistId);
                        dooNext++;
                    }
                }
               doNext++;
            }
        
        }
    }
    // Method for setting up the offered services list
    public void setupOfferedServices()
    {
       
       List<String> serviceNamesList = new ArrayList<>();
       for (int i = 0; i < allServicesList.size(); i++)
       {
           serviceNamesList.add((String) allServicesList.get(i).get(1));
       }
       notOfferedLV.getItems().setAll(serviceNamesList);
    }
    // Method for filtering the unoffered services list
    public void filterButtonPressed()
    {
        
        String typeSelected = (String) serviceTypeCB.getSelectionModel().getSelectedItem();
        String subTypeSelected = (String) serviceSubTypeCB.getSelectionModel().getSelectedItem();
        int typeSelID = DBRequest.getServiceTypeId(typeSelected);
        int subTypeSelID = DBRequest.getServiceSubTypeId(subTypeSelected);
        List<String> filteredServiceList = new ArrayList<>();
        for (int i = 0; i < allServicesList.size(); i++)
        {
            if (allServicesList.get(i).get(2).equals(typeSelID) == true && allServicesList.get(i).get(3).equals(subTypeSelID))
            {
                filteredServiceList.add((String) allServicesList.get(i).get(1));
            }
        }
        notOfferedLV.getItems().setAll(filteredServiceList);
    }
    // Method for adding individual services
    public void addOfferedService()
    {
        String selectedString = (String) notOfferedLV.getSelectionModel().getSelectedItem();
        offeredList.add(selectedString);
        notOfferedLV.getItems().remove(selectedString);
        offeredLV.getItems().setAll(offeredList);
        
    }
    // Method for adding all services
    public void addAllOfferedService()
    {
        List<String> servicesList = notOfferedLV.getItems();
        offeredList.addAll(servicesList);
        offeredLV.getItems().setAll(servicesList);
        allServicesList.clear();
        notOfferedLV.getItems().clear();
        
    }
    
    
    
}
