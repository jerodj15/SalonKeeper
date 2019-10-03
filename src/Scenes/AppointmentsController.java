/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBRemove;
import Database.DBRequest;
import MainClass.SalonKeeper;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author jerod
 */
public class AppointmentsController{

    @FXML
    private Button backButton;
    @FXML
    private ListView appointmenteLV;
    @FXML
    private TextField empIDTF;
    @FXML
    private DatePicker startDP;
    @FXML
    private DatePicker endDP;
    @FXML
    private Button searchButton;
    @FXML
    private Button deleteSelected;
    
    private SalonKeeper myKeeper;
    private List<List> searchResults;

    // Method for setting up the appointments screen
    public void setupAppointments(SalonKeeper mySalonKeeper)
    {
        this.myKeeper = mySalonKeeper;
    }
    
    // Method for the back button
    public void backButtonPressed()
    {
        myKeeper.mainScreen();
    }
    
    // Method for searching for appointments
    public void searchButtonPressed()
    {
        try {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<List> simplifiedReturnResults = new ArrayList<>();
        List<String> simpleServiceNamesOutput = new ArrayList<>();
        System.out.println("here");
        List<Object> searchCriteria = new ArrayList<>();
        String stylistString = empIDTF.getText();
        int stylistID = Integer.parseInt(stylistString);
        String startDate = startDP.getEditor().getText();
        String endDate = endDP.getEditor().getText();
        Date startingDate = new Date(startDate);
        Date endingDate = new Date(endDate);
        String editStartDate = sdf.format(startingDate);
        String editEndDate = sdf.format(endingDate);
        searchCriteria.add(stylistID);
        searchCriteria.add(editStartDate);
        searchCriteria.add(editEndDate);
        // Query the database
        searchResults = DBRequest.getAppointmentsWithinTimeframe(searchCriteria);
        
        for (int i = 0; i < searchResults.size(); i++)
        {
            List<Object> resultInfo = new ArrayList<>();
            int appointmentID = (int) searchResults.get(i).get(0);
            int clientID = (int) searchResults.get(i).get(3);
            int serviceEntryID = (int) searchResults.get(i).get(2);
            int serviceID = DBRequest.getServiceIDByEntryID(serviceEntryID);
            String serviceName = DBRequest.getServiceById(serviceID);
            String clientName = DBRequest.getClientByID(clientID);
            resultInfo.add(appointmentID);
            resultInfo.add(serviceName);
            resultInfo.add(clientName);
            resultInfo.add((String) searchResults.get(i).get(4));
            resultInfo.add((String) searchResults.get(i).get(5));
            resultInfo.add((String) searchResults.get(i).get(6));
            simplifiedReturnResults.add(resultInfo);
            
        }
        appointmenteLV.getItems().setAll(simplifiedReturnResults);
            
        } catch (NumberFormatException e) {
            Alert numAlert = new Alert(Alert.AlertType.INFORMATION, "Please enter the id of the employee and try again.", ButtonType.OK);
            numAlert.show();
        }
        catch (IllegalArgumentException f)
        {
            Alert numAlert = new Alert(Alert.AlertType.INFORMATION, "Please use the date selection buttons to select the dates and try again.", ButtonType.OK);
            numAlert.show();
        }
       
    }
    
    // Method for removing a selected appointment
    public void deleteButtonPressed()
    {
        try {
            List<Object> selectedAppointment = (List<Object>) appointmenteLV.getSelectionModel().getSelectedItem();
                int selAptID = (int) selectedAppointment.get(0);
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete the selected appointment. This cannot be undone.");
            if (yesOrNo == 0)
            {
                 DBRemove.deleteAppointment(selAptID);
                 searchButtonPressed();
            }

        } catch (NullPointerException e) {
            Alert numAlert = new Alert(Alert.AlertType.INFORMATION, "Please select an appointment from the list before trying to delete.", ButtonType.OK);
            numAlert.show();
        }
        
    }
    
}
