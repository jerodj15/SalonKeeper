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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class EditEmployeeController{

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
    private PasswordField passwordField;
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

    
    private SalonKeeper myKeeper;
    private List<Object> employeeInfo;
    boolean showPressed = false;
    // Lists for database information
     private static List<List> jobTitlesInfo;
     private static List<List> serviceTypes;
     private static List<List> serviceSubTypes;
     private static List<List> servicesList;
     private static List<String> offeredList = new ArrayList<>();
     private static List<List> allServicesList = DBRequest.getAllServices();

     // Method for the initial setup of the new employee creation screen
    public void setupNewEmployeeScreen(SalonKeeper mySalonKeeper, List<Object> editEmployeeInfo)
    {
        this.myKeeper = mySalonKeeper;
        this.employeeInfo = editEmployeeInfo;
        jobTitlesInfo = DBRequest.getAllJobTitles();
        serviceTypes = DBRequest.getAllServiceTypes();
        serviceSubTypes = DBRequest.getAllServiceSubTypes();
        List<String> jobTitles = new ArrayList<>();
        List<String> serviceTitles = new ArrayList<>();
        List<String> serviceSubTitles = new ArrayList<>();
        // Populate the service subtypes combobox
        for (int i = 0; i < serviceSubTypes.size(); i++)
        {
            serviceSubTitles.add((String) serviceSubTypes.get(i).get(1));
        }
        serviceSubTypeCB.getItems().setAll(serviceSubTitles);
        
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
        setupFields();
        setupOfferedServices();
    }
    // Method for pressing the back button
    public void backButtonPressed()
    {
        myKeeper.adminEmployeeMenu();
    }
    
    // Method for adding individual services
    public void addOfferedService()
    {
        String selectedString = (String) notOfferedLV.getSelectionModel().getSelectedItem();
        try {
            if (selectedString.isEmpty())
            {
                Alert selectAlert = new Alert(Alert.AlertType.NONE, "Please Select an Item First", ButtonType.OK);
                selectAlert.show();
            }
            else {
                offeredLV.getItems().add(selectedString);
                notOfferedLV.getItems().remove(selectedString); 
            } 
        } catch (Exception e) {
                Alert selectAlert = new Alert(Alert.AlertType.NONE, "Please Select an Item First", ButtonType.OK);
                selectAlert.show();
        }  
    }
    
 
    
    // Method for removing individual services
    public void removeOfferedService()
    {
        String selectedString = (String) offeredLV.getSelectionModel().getSelectedItem();
        try {
            if (selectedString.isEmpty())
            {
                Alert selectAlert = new Alert(Alert.AlertType.NONE, "Please Select an Item First", ButtonType.OK);
                selectAlert.show();
            }
            else
            {
                notOfferedLV.getItems().add(selectedString);
                offeredLV.getItems().remove(selectedString);
            }
        } catch (NullPointerException e) {
            Alert selectAlert = new Alert(Alert.AlertType.NONE, "Please Select an Item First", ButtonType.OK);
            selectAlert.show();
        }
        
               
    }
    
    
    // Method for pressing the save button
    public void saveButtonPressed()
    {
        try {
            String styleID = employeeIdTF.getText();
        int stylistID = Integer.parseInt(styleID);
        // Save employee information
        List<Object> editedStylistInfo = new ArrayList<>();
        String editFirstName, editLastName, editBirthday, editPassword, jobTitle;
        int jobTitleId;
        editFirstName = firstNameTF.getText();
        editLastName = lastNameTF.getText();
        editBirthday = birthdateDP.getEditor().getText();
        editPassword = passwordField.getText();
        jobTitle = (String) jobTitleCB.getSelectionModel().getSelectedItem();
        jobTitleId = DBRequest.getJobTitleId(jobTitle);
        editedStylistInfo.add(stylistID);
        editedStylistInfo.add(editFirstName);
        editedStylistInfo.add(editLastName);
        editedStylistInfo.add(jobTitleId);
        editedStylistInfo.add(editBirthday);
        editedStylistInfo.add(editPassword);
        if (editedStylistInfo.contains(""))
        {
            Alert selectAlert = new Alert(Alert.AlertType.NONE, "Please make sure the all the fields have information.", ButtonType.OK);
            selectAlert.show();
        }
        else
        {
        // Save edited services information
        List<String> editedOfferedList = new ArrayList<>();
        List<String> nonOfferedList = new ArrayList<>();
        List<Integer> offeredIDList = new ArrayList<>();
        List<Integer> nonOfferedIDList = new ArrayList<>();
        int offeredSize = offeredLV.getItems().size();
        int nonOfferedSize = notOfferedLV.getItems().size();
        int doNext = 0;
        while (doNext < 3)
        {
            if (doNext == 0)
            {
                // Populate the offered lists (Strings)
                for (int i = 0; i < offeredSize; i++)
                {
                    editedOfferedList.add((String) offeredLV.getItems().get(i));
                }
                // Populate the nonOfferedList (Strings)
                for (int i = 0; i < nonOfferedSize; i ++)
                {
                    nonOfferedList.add((String) notOfferedLV.getItems().get(i));
                }
                doNext++;
            }
            if (doNext == 1)
            {
                // Populate the offered list (Integers)
                for (int i = 0; i < editedOfferedList.size(); i++)
                {
                    int serveID = DBRequest.getServiceIdByTitle(editedOfferedList.get(i));
                    offeredIDList.add(serveID);
                }
                // Populate the not offered list (Integers)
                for (int i = 0; i < nonOfferedList.size(); i++)
                {
                    int serviceID = DBRequest.getServiceIdByTitle(nonOfferedList.get(i));
                    nonOfferedIDList.add(serviceID);
                }
                doNext++;
            }
            if (doNext == 2)
            {
                DBSave.saveOfferedServices(offeredIDList, nonOfferedIDList, stylistID);
                DBSave.saveEditedEmployee(editedStylistInfo);
                setupOfferedServices();
                doNext++;
            }
            myKeeper.adminEmployeeMenu();

        }
        }
        } catch (NumberFormatException e) {
            Alert selectAlert = new Alert(Alert.AlertType.NONE, "Please make sure the ", ButtonType.OK);
            selectAlert.show();
        }
        
    }
    // Method for setting up the fields with the employee information
    public void setupFields()
    {
        int employeeId = (int) employeeInfo.get(0);
        employeeIdTF.setText(String.valueOf(employeeId));
        firstNameTF.setText((String) employeeInfo.get(1));
        lastNameTF.setText((String) employeeInfo.get(2));
        jobTitleCB.getSelectionModel().select(employeeInfo.get(3));
        birthdateDP.getEditor().setText((String) employeeInfo.get(4));
        passwordField.setText((String) employeeInfo.get(5));
    }
    // Method for setting up the list of offered services
    public void setupOfferedServices()
    {
        servicesList = DBRequest.getEmployeeServices((int) employeeInfo.get(0));
        List<String> offeredServices = new ArrayList<>();
        List<String> notOfferedServices = new ArrayList<>();
        for (int i = 0; i < servicesList.size(); i++)
        {
            int offeredNum = (int) servicesList.get(i).get(2);
            if (offeredNum == 1)
            {
                int serveID = (int) servicesList.get(i).get(1);
                String serviceTitle = DBRequest.getServiceById(serveID);
                offeredServices.add(serviceTitle);
            }
            if (offeredNum == 0)
            {
                int serveID = (int) servicesList.get(i).get(1);
                String serviceTitle = DBRequest.getServiceById(serveID);
                notOfferedServices.add(serviceTitle);
            }
        }
        offeredLV.getItems().setAll(offeredServices);
        notOfferedLV.getItems().setAll(notOfferedServices);
    }
    // Method for pressing the apply filters button
    public void filterButtonPressed()
    {
        
        String typeSelected = (String) serviceTypeCB.getSelectionModel().getSelectedItem();
        String subTypeSelected = (String) serviceSubTypeCB.getSelectionModel().getSelectedItem();
        try {
            boolean isOkay = typeSelected.isEmpty();
            boolean isOkay2 = subTypeSelected.isEmpty();
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
        } catch (NullPointerException e) {
            Alert selectAlert = new Alert(Alert.AlertType.NONE, "Please Select a Type and Subtype from the drop down menu and try again", ButtonType.OK);
            selectAlert.show();
        }
        
    }
    // Method for pressing the show button to expose the password
    public void showButtonPressed()
    {
        if (showPressed == false)
        {
            showPasswordButton.setText("Hide");
            String passwordString = passwordField.getText();
            passwordField.clear();
            passwordField.setPromptText(passwordString);
            showPressed = true;  
        }
        else if (showPressed == true)
        {
            showPasswordButton.setText("Show");
            String passwordString = passwordField.getText();
            passwordField.clear();
            passwordField.setText((String) employeeInfo.get(5));
            showPressed = false;
        }
        else
        {
            System.out.println("Something went wrong");
        }
        
        
    }
    
    
}
