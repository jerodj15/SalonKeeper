/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBRequest;
import MainClass.SalonKeeper;
import SetGet.Appointment;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class EmployeeStatsController{

    @FXML
    private Button backButton;
    @FXML
    private ComboBox retentionStylistCB;
    @FXML
    private DatePicker retentionStartDP;
    @FXML
    private DatePicker retentionEndDP;
    @FXML
    private TextField retentionNewTF;
    @FXML
    private TextField retentionReturnTF;
    @FXML
    private Button retentionCalcButton;
    @FXML
    private Button retentionClearButton;
    @FXML
    private ComboBox typeStylistCB;
    @FXML
    private DatePicker typeStartDP;
    @FXML
    private DatePicker typeEndDP;
    @FXML
    private PieChart typePieChart;
    @FXML
    private Button typeCalcButton;
    @FXML
    private Button typeClearButton;
    @FXML
    private TableView<Appointment> apptTableView;
    // variables for the appointment history tab
    @FXML
    private ComboBox apptHistoryCB;
    @FXML
    private DatePicker apptStartDP;
    @FXML
    private DatePicker apptEndDP;
    @FXML
    private Button searchApptsButton;
    @FXML
    private Button clearApptsButton;

    private SalonKeeper myKeeper;
    private List<Object> employeeInfoList;
    private String stylistName;
    private List<List> clientRetentionList;
    private List<List> employeeServicesOffered;
    private List<Integer> employeeServicesOfferedIDList;
    private int employeeID;
    SimpleDateFormat sendDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat returnDateFormat = new SimpleDateFormat("MM/dd/yy");
    
    // Method for setting up the employee statistics screen
    public void setupEmployeeStats(SalonKeeper mySalonKeeper)
    {
       
        this.myKeeper = mySalonKeeper;
        employeeID = myKeeper.userID;
        employeeInfoList = DBRequest.getEmployeeById(employeeID);
        employeeServicesOffered = DBRequest.getOfferedEmployeeServices(employeeID);
        setupClientRetention();
        setupServicesPerformed();
        setupApptHistory();
        
       
    }
    // Method for pressing the back button
    public void backButtonPressed()
    {
        myKeeper.mainScreen();
    }
    // Setup the client retention tab *****************************************************************************
    public void setupClientRetention()
    {
        
        // Setup the stylist combo box
        stylistName = ((String) employeeInfoList.get(1)) + " " + ((String) employeeInfoList.get(2));
        retentionStylistCB.getItems().setAll(stylistName);
        retentionStylistCB.getSelectionModel().select(stylistName);
        
    }
    // Method for calculating the client retention ----TEST ME--------
    public void calculateRetentionPressed()
    {
        int doNext = 0;
        int dooNext = 0;
        int totalClients = 0;
        int totalRepeatClients = 0;
        List<List> retentionBasics = new ArrayList<>();
        List<Integer> uniqueList = new ArrayList<>();
        try {
            // Get the starting date
        String startDateString = retentionStartDP.getEditor().getText();
        Date startDate = new Date(startDateString);
        String startingDate = sendDateFormat.format(startDate);
        // Get the ending date
        String endDateString = retentionEndDP.getEditor().getText();
        Date endDate = new Date(endDateString);
        String endingDate = sendDateFormat.format(endDate);
        // Add items to list to search
        List<Object> searchStuff = new ArrayList<>();
        searchStuff.add(employeeID);
        searchStuff.add(startingDate);
        searchStuff.add(endingDate);
        // Get all the appointments within the specified time frame
        clientRetentionList = DBRequest.getAppointmentsWithinTimeframe(searchStuff);
        // Go through the results and tally up the numbers
        for (int i = 0; i < clientRetentionList.size(); i++)
        {
            List<Object> crListing = new ArrayList<>();
            int clientID = (int) clientRetentionList.get(i).get(3);
            String apptDate = (String) clientRetentionList.get(i).get(4);
            crListing.add(clientID);
            crListing.add(apptDate);
            retentionBasics.add(crListing);
        }
        
        int compareID = 0;
        String compoareDate = null;
        for (int i = 0; i < retentionBasics.size(); i++)
        {
            
            int clientID = (Integer) retentionBasics.get(i).get(0);
            String apptDate  = (String) retentionBasics.get(i).get(1);
            if (doNext == 0)
            {
                uniqueList.add(clientID);
                compareID = clientID;
                compoareDate = apptDate;
                totalClients ++;
                doNext++;
            }
            // Calculate the total number of appointments
            else
            {
                if (clientID == compareID && apptDate.equals(compoareDate))
                {
                    compareID = clientID;
                    compoareDate = apptDate;
                }
                else
                {
                    
                    totalClients++;
                    compareID = clientID;
                    compoareDate = apptDate;
                    uniqueList.add(clientID);
                }
            }
        }
        // Calculate the total number of repeat clients
        int uniqueID;
        int clientCompare = 0;
        for (int i = 0; i < uniqueList.size(); i++)
        {
            
            uniqueID = uniqueList.get(i);
            if (dooNext == 0)
            {
                clientCompare = uniqueID;
                dooNext++;
            }
            else
            {
                if (uniqueID == clientCompare)
                {
                    totalRepeatClients++;
                    clientCompare = uniqueID;
                }
                else
                {
                    clientCompare = uniqueID;
                }
            }
        }
        System.out.println("Total Clients: "+ totalClients);
        System.out.println("Total Repeat Clients: "+ totalRepeatClients);
        // Formula is (numTotal - totalNew) / numTotal * 100
        
        double newClients = ((totalClients - totalRepeatClients));
        System.out.println("New Clients : " + newClients);
        double returnPercentage = (totalClients - newClients) / totalClients * 100;
        System.out.println("Retention percentage: " + returnPercentage);
        double newPercentage = 100 - returnPercentage;
        retentionReturnTF.setText(String.valueOf(returnPercentage));
        retentionNewTF.setText(String.valueOf(newPercentage));
            
        } catch (IllegalArgumentException e) {
            Alert dateAlert = new Alert(Alert.AlertType.INFORMATION, "Please use the date selection tool to select the dates.", ButtonType.OK);
            dateAlert.show();
        }
        catch (NullPointerException e)
        {
            Alert dateAlert = new Alert(Alert.AlertType.INFORMATION, "Please select an employee from the drop down menu.", ButtonType.OK);
            dateAlert.show();
        }
        
    }
    
    //Method for the clear button
    public void clearRetentionButtonPressed()
    {
        retentionReturnTF.clear();
        retentionNewTF.clear();
        retentionStartDP.getEditor().clear();
        retentionEndDP.getEditor().clear();
        
    }
    
    // Setup the services performed tab****************************************************
    public void setupServicesPerformed()
    {
        typeStylistCB.getItems().setAll(stylistName);
        typeStylistCB.getSelectionModel().select(stylistName);
    }
    // Method for calculating the number of services performed -----TEST ME----
    public void calculateServiceAmountPressed()
    {
        List<PieChart.Data> pieData = new ArrayList<>();
        List<List> serviceNumbersList;
        employeeServicesOfferedIDList = new ArrayList<>();
        List<Integer> entryIDsList = new ArrayList<>();
        List<Integer> serviceIdList = new ArrayList<>();
        List<List> serviceTotalDetails = new ArrayList<>();
        try {
            // Get the starting date
        String startDateString = typeStartDP.getEditor().getText();
        Date startDate = new Date(startDateString);
        String startingDate = sendDateFormat.format(startDate);
        // Get the ending date
        String endDateString = typeEndDP.getEditor().getText();
        Date endDate = new Date(endDateString);
        String endingDate = sendDateFormat.format(endDate);
        // Add items to list to search
        List<Object> searchStuff = new ArrayList<>();
        searchStuff.add(employeeID);
        searchStuff.add(startingDate);
        searchStuff.add(endingDate);
        // Search for appointments within the time frame
        System.out.println("Employee ID :" + employeeID);
        System.out.println("Starting date : " + startingDate);
        System.out.println("Ending date : " + endingDate);
        serviceNumbersList = DBRequest.getAppointmentsWithinTimeframe(searchStuff);
        // Populate the service ids list the employee offers
        for (int i = 0; i < employeeServicesOffered.size(); i++)
        {
            employeeServicesOfferedIDList.add((Integer) employeeServicesOffered.get(i).get(2));
        }
       
        // Get the entry ids of the employees appointments within the time frame
        for (int i = 0; i < serviceNumbersList.size(); i++)
        {
           // System.out.println(serviceNumbersList.get(i).get(2));
            entryIDsList.add((Integer) serviceNumbersList.get(i).get(2));
        }
        // Populate the list of service Ids
        for (int i = 0; i < entryIDsList.size(); i++)
        {
            int entID = entryIDsList.get(i);
            int servID = DBRequest.getServiceIDByEntryID(entID);
            serviceIdList.add(servID);
        }
        // Count the number of occurences of each service
        List<List> numTimesAndServiceID = new ArrayList<>();
        Collections.sort(serviceIdList);
        
        int compareID = 0;
        int servid;
        int doNext = 0;
        for (int i = 0; i < serviceIdList.size(); i++)
        {
            int serviceId = serviceIdList.get(i);
            if ( doNext == 0)
            {
                ArrayList<Integer> initAdd = new ArrayList<>();
                int serviceAmount = Collections.frequency(serviceIdList, serviceId);
                initAdd.add(serviceAmount);
                initAdd.add(serviceId);
                numTimesAndServiceID.add(initAdd);
                compareID = serviceId;
                doNext++;
            }
            if (serviceId == compareID)
            {
                compareID = serviceId;
            }
            else
            {
                ArrayList<Integer> initAdd = new ArrayList<>();
                int serviceAmount = Collections.frequency(serviceIdList, serviceId);
                initAdd.add(serviceAmount);
                initAdd.add(serviceId);
                numTimesAndServiceID.add(initAdd);
                compareID = serviceId;
            }
        }
        //Populate the list of service details
        for (int i = 0; i < numTimesAndServiceID.size(); i++)
        {
            
            List<Object> serviceDetails = new ArrayList<>();
            int servID = (int) numTimesAndServiceID.get(i).get(1);
            int numTimes = (int) numTimesAndServiceID.get(i).get(0);
            String servName = DBRequest.getServiceById(servID);
            serviceDetails.add(servName);
            serviceDetails.add(numTimes);
            PieChart.Data myData = new PieChart.Data(servName, numTimes);
            serviceTotalDetails.add(serviceDetails);
            pieData.add(myData);
        }
        System.out.println(serviceTotalDetails);
        ObservableList<PieChart.Data> myPieData = FXCollections.observableArrayList(pieData);
        typePieChart.setData(myPieData);
        myPieData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty()
                        )
                )
        );
        } catch (IllegalArgumentException e) {
            Alert dateAlert = new Alert(Alert.AlertType.INFORMATION, "Please use the date selection tool to select the dates.", ButtonType.OK);
            dateAlert.show();
        }
        catch (NullPointerException e)
        {
            Alert dateAlert = new Alert(Alert.AlertType.INFORMATION, "Please select an employee from the drop down menu.", ButtonType.OK);
            dateAlert.show();
        }
        
    }
    
    // Method for clearing the service type tab entries
    public void clearServiceTypeButtonPressed()
    {
        typePieChart.getData().clear();
        typeStartDP.getEditor().clear();
        typeEndDP.getEditor().clear();
    }
    
    // Setup the appointment history tab****************************************************
    public void setupApptHistory()
    {
        // Stylist selection combo box
        apptHistoryCB.setDisable(true);
        apptHistoryCB.setOpacity(0.9);
        stylistName = ((String) employeeInfoList.get(1)) + " " + ((String) employeeInfoList.get(2));
        apptHistoryCB.getItems().setAll(stylistName);
        apptHistoryCB.getSelectionModel().select(stylistName);
        
    }
    // Method for clearing the search criteria
    public void clearAptHistPressed()
    {
        apptStartDP.getEditor().clear();
        apptEndDP.getEditor().clear();
        apptTableView.getItems().clear();
    }
    // Method for retrieving appt history and populating the tableview
    public void searchApptHistoryPressed()
    {
        // Create the table columns
    TableColumn<Appointment, String> startingColumn = new TableColumn("Starting Time");
    TableColumn<Appointment, String> endingColumn = new TableColumn("Ending Time");
    TableColumn<Appointment, String> clientColumn = new TableColumn("Client Name");
    TableColumn<Appointment, String> serviceColumn = new TableColumn("Service Performed");
    TableColumn<Appointment, String> dateColumn = new TableColumn("Date of Service");
        try {
            List<List> searchResults;
        List<List> simplifiedReturnResults = new ArrayList<>();
        List<Appointment> returnedAppointments = new ArrayList<>();
        System.out.println("here");
        List<Object> searchCriteria = new ArrayList<>();
        int stylistID = employeeID;
        String startDate = apptStartDP.getEditor().getText();
        String endDate = apptEndDP.getEditor().getText();
        Date startingDate = new Date(startDate);
        Date endingDate = new Date(endDate);
        String editStartDate = sendDateFormat.format(startingDate);
        String editEndDate = sendDateFormat.format(endingDate);
        searchCriteria.add(stylistID);
        searchCriteria.add(editStartDate);
        searchCriteria.add(editEndDate);
        // Query the database
        searchResults = DBRequest.getAppointmentsWithinTimeframe(searchCriteria);
        for (int i = 0; i < searchResults.size(); i++)
        {
            
            int appointmentID = (int) searchResults.get(i).get(0);
            int clientID = (int) searchResults.get(i).get(3);
            int serviceEntryID = (int) searchResults.get(i).get(2);
            int serviceID = DBRequest.getServiceIDByEntryID(serviceEntryID);
            String serviceName = DBRequest.getServiceById(serviceID); // Service Name
            String clientName = DBRequest.getClientByID(clientID); // Client Name
            String servDate = ((String) searchResults.get(i).get(4)); // Date
            String servStartTime = ((String) searchResults.get(i).get(5)); // Start Time
            String servEndTime = ((String) searchResults.get(i).get(6)); // End time
            // Create the appointment using the constructor
            Appointment myAppointment = new Appointment(servStartTime, servEndTime, clientName, serviceName, servDate);
            returnedAppointments.add(myAppointment);
        }
         // Setup the table
        startingColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endingColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("Client"));
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("Service"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("ServiceDate"));
        apptTableView.getColumns().setAll(startingColumn, endingColumn, clientColumn, serviceColumn, dateColumn);
        ObservableList<Appointment> data = FXCollections.observableArrayList(returnedAppointments);
        apptTableView.getItems().setAll(data);
        } catch (IllegalArgumentException e) {
            Alert dateAlert = new Alert(Alert.AlertType.INFORMATION, "Please use the date selection tool to select the dates.", ButtonType.OK);
            dateAlert.show();
        }
        catch (NullPointerException e)
        {
            Alert dateAlert = new Alert(Alert.AlertType.INFORMATION, "Please select an employee from the drop down menu.", ButtonType.OK);
            dateAlert.show();
        }
        
        
        
    }
   
}
