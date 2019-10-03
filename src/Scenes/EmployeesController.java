/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBRemove;
import Database.DBRequest;
import Database.DBSave;
import MainClass.SalonKeeper;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;


public class EmployeesController{

    @FXML
    private Button backButton;
    @FXML
    private ListView activeLV;
    @FXML
    private ListView inactiveLV;
    @FXML
    private Button moveInactiveButton;
    @FXML
    private Button moveActiveButton;
    @FXML
    private Button createNewButton;
    @FXML
    private TextField IdTF;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private ComboBox titleCB;
    @FXML
    private Button showButton;
    @FXML
    private DatePicker birthdateDP;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button editButton;
    
    private SalonKeeper myKeeper;
    private static List<List> jobTitlesInfo;
    public List<Object> editedEmployee;
    boolean showPressed = false;
    List<List> activeStylist;
    List<List> inactiveStylist;
    List<Object> stylistInfo;
    int selectedLV;

    // Method for initially setting up the employees screen
    public void setupEmployeesScreen(SalonKeeper mySalonKeeper)
    {
        jobTitlesInfo = DBRequest.getAllJobTitles();
        this.myKeeper = mySalonKeeper;
        setupInactive();
        setupActive();
        firstNameTF.setDisable(true);
        lastNameTF.setDisable(true);
        birthdateDP.setDisable(true);
        passwordField.setDisable(true);
        titleCB.setDisable(true);
        setupTitleComboBox();
        activeLV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedLV = 1;
            }
        });
        inactiveLV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedLV = 0;
            }
        });
    }
    // Method for creating a new employee
    public void createNewButtonPressed()
    {
        myKeeper.adminNewEmployee();
    }
    // Method for pressing the back button
    public void backButtonPressed()
    {
        myKeeper.mainScreen();
    }
    //Setup the jobTitleComboBoc
    public void setupTitleComboBox()
    {
         List<String> jobTitles = new ArrayList<>();
          for (int i = 0; i < jobTitlesInfo.size(); i++)
        {
            jobTitles.add((String) jobTitlesInfo.get(i).get(1));
        }
        titleCB.getItems().setAll(jobTitles);
        
    }
    
    
    // Setup the inactive list view
    public void setupInactive()
    {
        inactiveStylist = DBRequest.getInactiveEmployees();
        List<List> inactiveInfo = new ArrayList<>();
        String firstName, lastName;
        int employeeId;
        for (int i = 0; i < inactiveStylist.size(); i++)
        {
            List<Object> stylistInformation = new ArrayList<>();
            employeeId = (int) inactiveStylist.get(i).get(0);
            firstName = (String) inactiveStylist.get(i).get(1);
            lastName = (String) inactiveStylist.get(i).get(2);
            stylistInformation.add(employeeId);
            stylistInformation.add(firstName);
            stylistInformation.add(lastName);
            inactiveInfo.add(stylistInformation);
        }
        inactiveLV.getItems().setAll(inactiveInfo);
    }
    
    // Setup the active listview
    public void setupActive()
    {
        activeStylist = DBRequest.getActiveEmployees();
        List<List> activeInfo = new ArrayList<>();
        String firstName, lastName;
        int employeeId;
        for (int i = 0; i < activeStylist.size(); i++)
        {
            List<Object> stylistInformation = new ArrayList<>();
            employeeId = (int) activeStylist.get(i).get(0);
            firstName = (String) activeStylist.get(i).get(1);
            lastName = (String) activeStylist.get(i).get(2);
            stylistInformation.add(employeeId);
            stylistInformation.add(firstName);
            stylistInformation.add(lastName);
            activeInfo.add(stylistInformation);
        }
        activeLV.getItems().setAll(activeInfo);
    }
    // Method for moving an employee to active status
    public void moveToActive()
    {
        try {
            int selected = inactiveLV.getSelectionModel().getSelectedIndex();
        int stylistID = (int) inactiveStylist.get(selected).get(0);
        DBSave.moveToActive(stylistID);
        setupActive();
        setupInactive();
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Please select an employee from one of the lists the click the + / - button.", ButtonType.OK);
            myAlert.show();
        }
        
    }
    // Method for moving an employee to inactive status
    public void moveToInactive()
    {
        try {
            int selected = activeLV.getSelectionModel().getSelectedIndex();
        int stylistID = (int) activeStylist.get(selected).get(0);
        DBSave.moveToInactive(stylistID);
        setupActive();
        setupInactive();
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Please select an employee from one of the lists the click the + / - button.", ButtonType.OK);
            myAlert.show();
        }
        
    }
    // Method for pressing the select button
    public void selectButtonPressed()
    {
        // Active selection
        try {
             if (selectedLV == 1)
        {
           String jobTitle;
           int selectedEmp = activeLV.getSelectionModel().getSelectedIndex();
           int empID = (int) activeStylist.get(selectedEmp).get(0);
           stylistInfo = DBRequest.getEmployeeById(empID);
           IdTF.setText(String.valueOf(empID));
           firstNameTF.setText((String) stylistInfo.get(1));
           lastNameTF.setText((String) stylistInfo.get(2));
           jobTitle = DBRequest.getJobTitle((int) stylistInfo.get(3));
           titleCB.getSelectionModel().select(jobTitle);
           birthdateDP.getEditor().setText((String) stylistInfo.get(4));
           passwordField.setText((String) stylistInfo.get(5));

        }
        //Inactive Seletion
        if (selectedLV == 0)
        {
           String jobTitle;
           int selectedEmp = inactiveLV.getSelectionModel().getSelectedIndex();
           int empID = (int) inactiveStylist.get(selectedEmp).get(0);
           stylistInfo = DBRequest.getEmployeeById(empID);
           IdTF.setText(String.valueOf(empID));
           firstNameTF.setText((String) stylistInfo.get(1));
           lastNameTF.setText((String) stylistInfo.get(2));
           jobTitle = DBRequest.getJobTitle((int) stylistInfo.get(3));
           titleCB.getSelectionModel().select(jobTitle);
           birthdateDP.getEditor().setText((String) stylistInfo.get(4));
           passwordField.setText((String) stylistInfo.get(5));
        }
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Please select an employee from one of the lists the click Select", ButtonType.OK);
            myAlert.show();
        }
       
    }
    // Method for pressing the edit button
    public void editButtonPressed()
    {
        firstNameTF.setDisable(false);
        lastNameTF.setDisable(false);
        birthdateDP.setDisable(false);
        passwordField.setDisable(false);
        titleCB.setDisable(false);
        editedEmployee = new ArrayList<>();
        int employeeId, jobTitleId;
        String firstName, lastName, birthdateString, passwordString, jobTitle;
        employeeId = Integer.parseInt(IdTF.getText());
        jobTitle = (String) titleCB.getSelectionModel().getSelectedItem();
        jobTitleId = DBRequest.getJobTitleId((String) titleCB.getSelectionModel().getSelectedItem());
        firstName = firstNameTF.getText();
        lastName = lastNameTF.getText();
        birthdateString = birthdateDP.getEditor().getText();
        passwordString = passwordField.getText();
        // Add the employee info to the list
        editedEmployee.add(employeeId);
        editedEmployee.add(firstName);
        editedEmployee.add(lastName);
        editedEmployee.add(jobTitle);
        editedEmployee.add(birthdateString);
        editedEmployee.add(passwordString);
        // Start the edit employee window
        myKeeper.adminEditEmployee(editedEmployee);
    }
    
    // Delete button method
    public void deleteButtonPressed()
    {
        String stylistId = IdTF.getText();
        int employeeID = Integer.parseInt(stylistId);
        DBRemove.removeStylist(employeeID);
        setupActive();
        setupInactive();
        IdTF.clear();
        firstNameTF.clear();
        lastNameTF.clear();
        titleCB.getSelectionModel().clearSelection();
        birthdateDP.getEditor().clear();
        passwordField.clear();
    }
    // Method for showing the employee password
    public void showButtonPressed()
    {
        if (showPressed == false)
        {
            showButton.setText("Hide");
            String passwordString = passwordField.getText();
            passwordField.clear();
            passwordField.setPromptText(passwordString);
            showPressed = true;  
        }
        else if (showPressed == true)
        {
            showButton.setText("Show");
            String passwordString = passwordField.getText();
            passwordField.clear();
            passwordField.setText((String) stylistInfo.get(5));
            showPressed = false;
        }
        else
        {
            System.out.println("Something went wrong");
        }
        
        
    }
    
    
}
