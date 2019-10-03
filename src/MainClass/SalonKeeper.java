package MainClass;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Scenes.AdminClientsController;
import Scenes.AdminNewClientController;
import Scenes.AdminReportsController;
import Scenes.AdminServicesController;
import Scenes.AppointmentsController;
import Scenes.EditEmployeeController;
import Scenes.EmployeeStatsController;
import Scenes.EmployeesController;
import Scenes.InitialEntryController;
import Scenes.LoginScreenController;
import Scenes.MainScreenController;
import Scenes.NewAppointmentController;
import Scenes.NewClientController;
import Scenes.NewEmployeeController;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class SalonKeeper extends Application {
    
    private Stage primaryStage;
    public int userID;
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/initialEntry.fxml"));
            Pane mainPane = (Pane) loader.load();
            InitialEntryController initialEntryController = loader.getController();
            initialEntryController.setupInitScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Welcome, Please fill in the information");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


    public static void main(String[] args) {
        //Database.DBConnect.connectToDB();
        launch(args);
        
    }
    // Method for setting up the login screen
    public void initialScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/initialEntry.fxml"));
            Pane mainPane = (Pane) loader.load();
            InitialEntryController initialEntryController = loader.getController();
            initialEntryController.setupInitScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Welcome, Please fill in the information");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    
    // Method for setting up the login screen
    public void loginScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/loginScreen.fxml"));
            Pane mainPane = (Pane) loader.load();
            LoginScreenController loginScreenController = loader.getController();
            loginScreenController.setupLoginScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Welcome, Please Login");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Method for setting up the main screen
    public void mainScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/mainScreen.fxml"));
            Pane mainPane = (Pane) loader.load();
            MainScreenController mainScreenController = loader.getController();
            mainScreenController.setupMainScene(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Welcome");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    
    // Administrator Button Functions
    // Method for setting up the administrator employee screen
    public void adminEmployeeMenu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/employees.fxml"));
            Pane mainPane = (Pane) loader.load();
            EmployeesController employeesController = loader.getController();
            employeesController.setupEmployeesScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Employees (Administrator)");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the administrator services screen
    public void adminServices()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/adminServices.fxml"));
            Pane mainPane = (Pane) loader.load();
            AdminServicesController adminServicesController = loader.getController();
            adminServicesController.setupServices(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Services (Administrator)");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the administrator new employee screen
    public void adminNewEmployee()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/newEmployee.fxml"));
            Pane mainPane = (Pane) loader.load();
            NewEmployeeController newEmployeeController = loader.getController();
            newEmployeeController.setupNewEmployeeScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("New Employee (Administrator)");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the administrator clients screen
    public void adminClientsMenu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/adminClients.fxml"));
            Pane mainPane = (Pane) loader.load();
            AdminClientsController adminClientsController = loader.getController();
            adminClientsController.setupClentScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Clients (Administrator)");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the administrator new client screen
    public void adminNewClientMenu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/adminNewClient.fxml"));
            Pane mainPane = (Pane) loader.load();
            AdminNewClientController adminNewClientController = loader.getController();
            adminNewClientController.setupAdminNewClientMenu(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("New Client (Administrator)");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the administrator reports screen
    public void adminReportsMenu()
    {
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/adminReports.fxml"));
            Pane mainPane = (Pane) loader.load();
            AdminReportsController adminReportsController = loader.getController();
            adminReportsController.setupAdminReports(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Reports (Administrator)");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the administrator employee editing screen
    public void adminEditEmployee(List<Object> employeeInfo)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/editEmployee.fxml"));
            Pane mainPane = (Pane) loader.load();
            EditEmployeeController editEmployeeController = loader.getController();
            editEmployeeController.setupNewEmployeeScreen(this, employeeInfo);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Edit Employee (Administrator)");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Employee Button Functions
    // Method for setting up the employee new appointment screen
    public void newAppointmentMenu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/newAppointment.fxml"));
            Pane mainPane = (Pane) loader.load();
            NewAppointmentController newAppointmentController = loader.getController();
            newAppointmentController.setupNewAppointmentMenu(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("New Appointment");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the employee new client creation screen
    public void newClientMenu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/newClient.fxml"));
            Pane mainPane = (Pane) loader.load();
            NewClientController newClientController = loader.getController();
            newClientController.setupNewClientScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("New Client");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Method for setting up the employee statistics screen
    public void employeeStatsMenu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/employeeStats.fxml"));
            Pane mainPane = (Pane) loader.load();
            EmployeeStatsController employeeStatsController = loader.getController();
            employeeStatsController.setupEmployeeStats(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Employee Statistics");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Method for setting up the employee appointments screen
    public void appointments()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/appointments.fxml"));
            Pane mainPane = (Pane) loader.load();
            AppointmentsController appointmentsController = loader.getController();
            appointmentsController.setupAppointments(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Appointments");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Method for setting up the employee clients screen
    public void employeeClientsMenu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SalonKeeper.class.getResource("/Scenes/adminClients.fxml"));
            Pane mainPane = (Pane) loader.load();
            AdminClientsController adminClientsController = loader.getController();
            adminClientsController.setupClentScreen(this);
            Scene mainScene = new Scene(mainPane);
            primaryStage.setTitle("Clients");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
