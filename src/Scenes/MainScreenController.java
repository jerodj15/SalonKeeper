/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;


import Database.DBRequest;
import MainClass.SalonKeeper;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Jerod
 */
public class MainScreenController extends SalonKeeper{

    @FXML
    private Button employeesButton;
    @FXML
    private Button clientsButton;
    @FXML
    private Button schedulingButton;
    @FXML
    private Button adminReportsButton;
    @FXML
    private TabPane employeesTab;
    @FXML
    private Label dailyLabel;
    @FXML
    private Button newApptButton;
    @FXML
    private Button newClientButton;
    @FXML
    private Button myStatsButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button empClientsButton;
    @FXML
    private ToolBar adminToolBar;
    @FXML
    private AnchorPane mainPane;  
    
    
    List<List> employeesInfo;
    private SalonKeeper mainKeeper;
    private int logInEmployeeID;
    private int employeeCount;
    private int timerToLogout;
    private double paneWidthSize;
    private double paneHeightSize;
    private String todaysString;
    private boolean mouseMoved = false;
    Date todayDate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    // Method for setting up the main screen
    public void setupMainScene(SalonKeeper mySalonKeeper)
    {
        
        todayDate = Date.from(Instant.now());
        todaysString = sdf.format(todayDate);
        this.mainKeeper = mySalonKeeper;
        logInEmployeeID = this.mainKeeper.userID;
        if (logInEmployeeID != 1)
        {
            adminToolBar.setDisable(true);
        }
        System.out.println(mainKeeper.userID);
        employeesInfo = DBRequest.getActiveEmployees();
        employeeCount = employeesInfo.size();
        setupGrid();
        
        
        
    }
    
    // Method for setting up the gridPane area
    public void setupGrid()
    {
        List<Tab> employeesTabs = new ArrayList<>();
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        System.out.println(employeeCount);
        System.out.println(employeesInfo.get(0));
        
           for (int j = 0; j < employeeCount; j++)
           {
               List<List> employeeApptsList = new ArrayList<>();
               List<Object> searchStuff = new ArrayList<>();
               int curEmpID = (int) employeesInfo.get(j).get(0);
               searchStuff.add(curEmpID);
               searchStuff.add(todaysString);
               List<List> employeeAppointments = DBRequest.getTodaysAppointments(searchStuff);
               for (int i = 0; i < employeeAppointments.size(); i++)
               {
                   List<Object> apptInfoList = new ArrayList<>();
                   int serviceEntryID, clientID, serviceID;
                   String clientNameString, startTimeString, endTimeString, serviceNameString;
                   serviceEntryID = (int) employeeAppointments.get(i).get(2);
                   serviceID = DBRequest.getServiceIDByEntryID(serviceEntryID);
                   serviceNameString = DBRequest.getServiceById(serviceID);
                   clientID = (int) employeeAppointments.get(i).get(3);
                   clientNameString = DBRequest.getClientByID(clientID);
                   startTimeString = (String) employeeAppointments.get(i).get(5);
                   endTimeString = (String) employeeAppointments.get(i).get(6);
                   apptInfoList.add(startTimeString);
                   apptInfoList.add(endTimeString);
                   apptInfoList.add(clientNameString);
                   apptInfoList.add(serviceNameString);
                   employeeApptsList.add(apptInfoList);
               }
               System.out.println(todaysString);
               ListView employeeSchedule = new ListView();
               employeeSchedule.getItems().setAll(employeeApptsList);
               String employeeFullName = ((String) employeesInfo.get(j).get(1)) + " " + ((String) employeesInfo.get(j).get(2));
               Tab employeeTab = new Tab(employeeFullName);
               employeesTabs.add(employeeTab);
               employeesTab.getTabs().add(employeeTab);
               employeesTab.getTabs().get(j).setContent(employeeSchedule);
           }
           
    }
    
    // Methods for the different menu button
    // Method for admin employees button
    public void adminEmployeePressed()
    {
        mainKeeper.adminEmployeeMenu();
    }
    // Method for admin clients button
    public void adminClientsPressed()
    {
        mainKeeper.adminClientsMenu();
    }
    // Method for admin reports button
    public void adminReportsPressed()
    {
        mainKeeper.adminReportsMenu();
    }
    // Method for admin services button
    public void adminServicesPressed()
    {
        mainKeeper.adminServices();
    }
    // Method for new appointment button
    public void newAppointmentPressed()
    {
        mainKeeper.newAppointmentMenu();
    }
    // Method for new client button
    public void newClientPressed()
    {
        mainKeeper.newClientMenu();
    }
    // Method for employee reports button
    public void employeeReportsPressed()
    {
        mainKeeper.employeeStatsMenu();
    }
    // Method for pressing the logout button
    public void logoutButtonPressed()
    {
        mainKeeper.loginScreen();
        mainKeeper.userID = 0;
    }
    // Method for pressing the appointments button
    public void appointmentsButtonPressed()
    {
        mainKeeper.appointments();
        
    }
    // Method for pressing the clients button
    public void employeeClientsPressed()
    {
        mainKeeper.employeeClientsMenu();
    }
    
    
    

     
    
}
