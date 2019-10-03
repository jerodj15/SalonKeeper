/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBRequest;
import Database.DBSave;
import MainClass.SalonKeeper;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class NewAppointmentController {

    @FXML
    private Button backButton;
    @FXML
    private TextField clientSearchTF;
    @FXML
    private Button clientSearchButton;
    @FXML
    private ListView clientsLV;
    @FXML
    private Button createNewClientButton;
    @FXML
    private Button chooseSelectedClientButton;
    @FXML
    private ListView selectedServicesLV;
    @FXML
    private Button addServiceButton;
    @FXML
    private Button removeSelectedServiceButtton;
    @FXML
    private ListView availableServicesLV;
    @FXML
    private DatePicker dateDP;
    @FXML
    private TextField startTimeTF;
    @FXML
    private TextField endTimeTF;
    @FXML
    private ComboBox startAMPMCB;
    @FXML
    private ComboBox endAMPMCB;
    @FXML
    private ComboBox stylistCB;
    @FXML
    private ComboBox startHourCB;
    @FXML
    private ComboBox startMinuteCB;
    @FXML 
    private ComboBox endHourCB;
    @FXML 
    private ComboBox endMinuteCB;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveAppointmentButton;
    @FXML
    private Button searchServicesButton;
    @FXML
    private TextField clientTF;
    @FXML
    private TextField searchServicesTF;

     private int selEmpId;
     String clientLastName;
     String clientPhoneNumber;
    private SalonKeeper myKeeper;
    private List<List> allClients = DBRequest.getAllClients();
    private List<List> allStylists = DBRequest.getActiveEmployees();
    private List<List> serviceTypesList = DBRequest.getAllServiceTypes();
    private List<List> serviceSubTypesList = DBRequest.getAllServiceSubTypes();
    private List<List> stylistServicesList;
    private List<String> startAMPM;
    private List<String> endAMPM;
    private List<Object> employeeInfoList;
    List<List> servicesStrings;
    
    // Setup the new appointment screen
    public void setupNewAppointmentMenu(SalonKeeper mySalonKeeper)
    {
        this.myKeeper = mySalonKeeper;
        setupClientList();
        System.out.println(myKeeper.userID);
        setupAMPM();
        setupStylistCB();
        setupTimeCB();  
    }
    
    // Method for pressing the save button
    public void saveButtonPressed()
    {// Setup the create date
        SimpleDateFormat apptDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date todayDate = Date.from(Instant.now());
        String nowDate = sdf.format(todayDate);

        try {
            // Setup the appt date
        String apptDate = dateDP.getEditor().getText();
        Date appointmentDate = new Date(apptDate);
        String editApptDate = apptDateFormat.format(appointmentDate);
        // Get the timing information
        int startHour, endHour, startMin, endMin; 
        String startAMP, endAMP;
        startHour = (Integer) startHourCB.getSelectionModel().getSelectedItem();
        endHour = (Integer) endHourCB.getSelectionModel().getSelectedItem();
        startMin = (Integer) startMinuteCB.getSelectionModel().getSelectedItem();
        endMin = (Integer) endMinuteCB.getSelectionModel().getSelectedItem();
        startAMP = (String) startAMPMCB.getSelectionModel().getSelectedItem();
        endAMP = (String) endAMPMCB.getSelectionModel().getSelectedItem();
        // Check each of the times for empties
        if (startHour == 0 || endHour == 0 || startMin == 0 || endMin == 0)
        {
            System.out.println("hereproblem");
        }
        else
        {
        String startingTime = startHour + ":" + startMin + " " + startAMP;
        String endingTime = endHour + ":" + endMin + " " + endAMP;
        List<Object> clientInfoListing = DBRequest.getSpecificClient(clientLastName, clientPhoneNumber);
        int clientID = (int) clientInfoListing.get(0);
        List<List> servicesGetting = new ArrayList<>();
        int gettingSize = selectedServicesLV.getItems().size();
        List<Integer> entryIds = new ArrayList<>();
        for (int i = 0; i < gettingSize; i++)
        {
            List<Object> serviceInfoGetting = (List<Object>) selectedServicesLV.getItems().get(i);
            servicesGetting.add(serviceInfoGetting);
        }
        for (int i = 0; i < gettingSize; i++)
        {
            int entId = (int) servicesGetting.get(i).get(0);
            entryIds.add(entId);
        }
        
        
        int creatorID = myKeeper.userID;
        int employeeID = (int) employeeInfoList.get(0);
        
        // Add all the appointment information to the list to be saved to the database
        for (int i = 0; i < entryIds.size(); i++)
        {
            List<Object> apptInformationList = new ArrayList<>();
            apptInformationList.add(employeeID);
            apptInformationList.add(entryIds.get(i));
            apptInformationList.add(clientID);
            apptInformationList.add(editApptDate);
            apptInformationList.add(startingTime);
            apptInformationList.add(endingTime);
            apptInformationList.add(creatorID);
            apptInformationList.add(nowDate);
            DBSave.saveNewAppointment(apptInformationList);
        }
        
        }
        } catch (Exception e) {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Something happened", ButtonType.OK);
            myAlert.show();
        }
        
            myKeeper.mainScreen();
    }
    
    // Method for the back button
    public void backButtonPressed()
    {
        myKeeper.mainScreen();
    }
    // Method for the create new client button
    public void createNewClientPressed()
    {
        myKeeper.newClientMenu();
    }
    // Method to populate the list of clients
    public void setupClientList()
    {
        clientsLV.getItems().setAll(allClients);
    }
    
    // Method for the choose selected client button
    public void chooseSelectedButtonPressed()
    {
        try {
            List<String> clientInfoStrings = ((List<String>)  clientsLV.getSelectionModel().getSelectedItem());
        String clientFirstName = clientInfoStrings.get(0);
        clientLastName = clientInfoStrings.get(1);
        clientPhoneNumber = clientInfoStrings.get(2);
        String selectedClientString = clientFirstName + " " + clientLastName;
        clientTF.clear();
        clientTF.setText(selectedClientString);
        } catch (NullPointerException e) {
           Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Make sure to select a client from the list then click the Choose Selected Button", ButtonType.OK);
            myAlert.show();
        }
        
        
    }
    
    // Method to setup the AM / PM Selection boxes
    public void setupAMPM()
    {
        startAMPM = OtherMethods.Information.getAMPM();
        endAMPM = OtherMethods.Information.getAMPM();
        startAMPMCB.getItems().setAll(startAMPM);
        endAMPMCB.getItems().setAll(endAMPM);
        startAMPMCB.getSelectionModel().select("PM");
        endAMPMCB.getSelectionModel().select("PM");
        startAMPMCB.setDisable(true);
        startAMPMCB.setOpacity(0.9);
        endAMPMCB.setDisable(true);
        endAMPMCB.setOpacity(0.9);
    }
    
    // Method to setup the time combo boxes
    public void setupTimeCB()
    {
        List<Integer> hoursList = OtherMethods.Information.hoursList();
        List<Integer> minutesList = OtherMethods.Information.minutesList();
        startHourCB.getItems().setAll(hoursList);
        endHourCB.getItems().setAll(hoursList);
        startMinuteCB.getItems().setAll(minutesList);
        endMinuteCB.getItems().setAll(minutesList);
        
    }
    
    // Setup the list of stylists combo box
    public void setupStylistCB()
    {
        String stylistFirstName;
        String stylistLastName;
        int stylistId;
        List<List> stylistsInformation = new ArrayList<>();
        // Get first names
        for (int i = 0; i < allStylists.size(); i++)
        {
            
            List<Object> individualStylist = new ArrayList<>();
            stylistId = (Integer) allStylists.get(i).get(0);
            stylistFirstName = (String) allStylists.get(i).get(1);
            stylistLastName = (String) allStylists.get(i).get(2);
            individualStylist.add(stylistId);
            individualStylist.add(stylistFirstName);
            individualStylist.add(stylistLastName);
            stylistsInformation.add(individualStylist);
        }
        stylistCB.getItems().clear();
        stylistCB.getItems().setAll(stylistsInformation);
    }
    
    // Method for what happens when a stylist is chosen
    public void stylistSelected()
    {
        employeeInfoList = (List<Object>) stylistCB.getSelectionModel().getSelectedItem();
        int empId = (int) employeeInfoList.get(0);
        String empFirst = (String) employeeInfoList.get(1);
        String empLast = (String) employeeInfoList.get(2);
        servicesStrings = new ArrayList<>();
        stylistServicesList = DBRequest.getOfferedEmployeeServices(empId);
        for (int i = 0; i < stylistServicesList.size(); i++)
        {
            List<Object> serviceInfoObjects = new ArrayList<>();
            int serviceId = (int) stylistServicesList.get(i).get(2);
            int entryId = (int) stylistServicesList.get(i).get(0);
            String serviceString = DBRequest.getServiceById(serviceId);
            serviceInfoObjects.add(entryId);
            serviceInfoObjects.add(serviceString);
            servicesStrings.add(serviceInfoObjects);
        }
        availableServicesLV.getItems().setAll(servicesStrings);
        
    }
    
    // Method for searching available services by name
    public void searchServicesPressed()
    {
        try {
            if (searchServicesTF.getText().isEmpty())
        {
            availableServicesLV.getItems().setAll(servicesStrings);
        }
        else
        {
            String searchString = searchServicesTF.getText();
            List<List> searchResultsList = new ArrayList<>();
            for (int i = 0; i < servicesStrings.size(); i++)
            {
                if (servicesStrings.get(i).get(1).toString().contains(searchString))
                {
                    searchResultsList.add(servicesStrings.get(i));
                }
            }
            availableServicesLV.getItems().setAll(searchResultsList);
            }
            
        } catch (NullPointerException e) {
           Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Make sure to select a stylist before searching services. Please try again.", ButtonType.OK);
            myAlert.show();
        }
        
        
    }
    
   // Method for the add service button    
    public void addServiceButtonPressed()
    {
        try {
            List<Object> selectedItem = (List<Object>) availableServicesLV.getSelectionModel().getSelectedItem();
            boolean isOkay = selectedItem.isEmpty();
        selectedServicesLV.getItems().add(selectedItem);
        availableServicesLV.getItems().remove(selectedItem);
        servicesStrings.remove(selectedItem);
        } catch (Exception e) {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Make sure to select a service to add then click the + button.", ButtonType.OK);
            myAlert.show();
        }
        
    }
    // Method for the remove service button
    public void removeServiceButtonPressed()
    {
        try {
            List<Object> selectedItem = (List<Object>) selectedServicesLV.getSelectionModel().getSelectedItem();
            boolean isOkay = selectedItem.isEmpty();
        availableServicesLV.getItems().add(selectedItem);
        selectedServicesLV.getItems().remove(selectedItem);
        servicesStrings.add(selectedItem);
        } catch (Exception e) {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Make sure to select a service to remove then click the - button.", ButtonType.OK);
            myAlert.show();
        }
        
    }
    // Method for searching for clients by first name, last name, or phone number
    public void searchClientsButtonPressed()
    {
        if (clientSearchTF.getText().isEmpty())
        {
            clientsLV.getItems().setAll(allClients);
        }
        else
        {
        String searchString = clientSearchTF.getText();
        List<List> returnedClients = new ArrayList<>();
        for (int i = 0; i < allClients.size(); i++)
        {
            List<Object> clientInfoList = (List<Object>) clientsLV.getItems().get(i);
            String clientFirstString = (String) clientInfoList.get(0);
            String clientLastString = (String) clientInfoList.get(1);
            String clientPhoneString = (String) clientInfoList.get(2);
            if (clientFirstString.equalsIgnoreCase(searchString) || clientLastString.equalsIgnoreCase(searchString) || clientPhoneString.equalsIgnoreCase(searchString))
            {
                returnedClients.add(clientInfoList);
            }
            
        }
        clientsLV.getItems().setAll(returnedClients);
        }
    }
    
}
