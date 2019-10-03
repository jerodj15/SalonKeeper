/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBConnect;
import Database.DBRemove;
import Database.DBRequest;
import Database.DBSave;
import MainClass.SalonKeeper;
import SetGet.Service;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jerod
 */
public class AdminServicesController{

    @FXML
    private Button backButton;
    @FXML
    private ComboBox typeCB;
    @FXML
    private ComboBox subTypeCB;
    @FXML
    private ListView servicesLV;
    @FXML
    private Button removeSelectedButton;
    @FXML
    private ComboBox newTypeCB;
    @FXML
    private ComboBox newSubTypeCB;
    @FXML
    private TextField newNameTF;
    @FXML
    private Button clearFieldsButton;
    @FXML
    private Button saveServiceButton;
    @FXML
    private Button filtersButton;

    // Variables
    private SalonKeeper myKeeper;
    private static List<List> serviceTypes;
    private static List<String> serviceSubTypes;
    
    // Method for initially setting up the services screen
    public void setupServices(SalonKeeper mySalonKeeper)
    {
        List<Service> seviceList = new ArrayList<>();
        List<List> allServicesList = DBRequest.getAllServices();
        this.myKeeper = mySalonKeeper;
        typePopulate();
        serviceListPopulate();
        // Method for populating the service subtype Combo Box
        newSubTypeCB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            newSubTypeCB.getItems().clear();
            int typeSelection = newTypeCB.getSelectionModel().getSelectedIndex();
            serviceSubTypes = DBRequest.getServiceSubTypeById(typeSelection + 1);
            newSubTypeCB.getItems().setAll(serviceSubTypes);
            
            }
            });
        subTypeCB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            subTypeCB.getItems().clear();
            int typeSelection = typeCB.getSelectionModel().getSelectedIndex();
            serviceSubTypes = DBRequest.getServiceSubTypeById(typeSelection + 1);
            subTypeCB.getItems().setAll(serviceSubTypes);
            
            }
            });
    }
    
    
    // Method for pressing the back button
    public void backButtonPressed()
    {
        myKeeper.mainScreen();
    }
    
    //Method for populating the Service Type Combo Box
    public void typePopulate()
    {
        List<String> serviceTitles = new ArrayList<>();
        serviceTypes = DBRequest.getAllServiceTypes();
        typeCB.getItems().clear();
        newTypeCB.getItems().clear();
        // Populate the service types combo box
        for (int i = 0; i < serviceTypes.size(); i++)
        {
            serviceTitles.add((String) serviceTypes.get(i).get(1));
        }
        typeCB.getItems().setAll(serviceTitles);
        newTypeCB.getItems().setAll(serviceTitles);
    }
    // Method for populating the service list
    public void serviceListPopulate()
    {
        List<List> allServicesList = DBRequest.getAllServices();
        List<String> serviceStrings = new ArrayList<>();
        for (int i = 0; i < allServicesList.size(); i++)
        {
            serviceStrings.add((String) allServicesList.get(i).get(1));
        }
        servicesLV.getItems().setAll(serviceStrings);
    }
    
    // Method for the save button
    public void saveButtonPressed()
    {
        try {
            String selectedType = (String) newTypeCB.getSelectionModel().getSelectedItem();
            String selectedSubType = (String) newSubTypeCB.getSelectionModel().getSelectedItem();
            String serviceName = newNameTF.getText();
            if (selectedType.equals("") || selectedSubType.equals("") || serviceName.isEmpty())
            {
                Alert selAlert = new Alert(Alert.AlertType.INFORMATION, "Please make sure all the fields have a selection and try saving again.", ButtonType.OK);
                selAlert.show();
            }
            else
            {
            int servTypeId = DBRequest.getServiceTypeId(selectedType);
            int subserveId = DBRequest.getServiceSubTypeId(selectedSubType);
            System.out.println(servTypeId);
            System.out.println(subserveId);
            List<Object> serviceToSave = new ArrayList<>();
            serviceToSave.add(serviceName);
            serviceToSave.add(servTypeId);
            serviceToSave.add(subserveId);
            DBSave.saveNewService(serviceToSave);
            serviceListPopulate();
            clearButtonPressed();
            }
        } catch (NullPointerException e) {
            try {
                 if (e.getMessage().isEmpty())
                {
                System.out.println("sdfsazdfawsfvsrefgg");
                }
            } catch (NullPointerException f) {
                Alert selAlert = new Alert(Alert.AlertType.INFORMATION, "Please make sure all the fields have a selection and try saving again.", ButtonType.OK);
                selAlert.show();
            }
           
        
        }
        
        
    }
    // Method for pressing the clear button
    public void clearButtonPressed()
    {
        newNameTF.clear();
        newSubTypeCB.getItems().clear();
        newTypeCB.getSelectionModel().clearSelection();
    }
    // Method for pressing the apply filters button
    public void filtersButtonPressed()
    {
        List<List> allServicesList = DBRequest.getAllServices();
        String typeSelected = (String) typeCB.getSelectionModel().getSelectedItem();
        String subTypeSelected = (String) subTypeCB.getSelectionModel().getSelectedItem();
        int typeSelID = DBRequest.getServiceTypeId(typeSelected);
        int subTypeSelID = DBRequest.getServiceSubTypeId(subTypeSelected);
        System.out.println(typeSelID);
        System.out.println(subTypeSelID);
        if (typeSelID == 0 || subTypeSelID == 0)
        {
            Alert selAlert = new Alert(Alert.AlertType.INFORMATION, "Please select a type and subtype to filter the services", ButtonType.OK);
            selAlert.show();
        }
        else
        {
        List<String> filteredServiceList = new ArrayList<>();
        for (int i = 0; i < allServicesList.size(); i++)
        {
            if (allServicesList.get(i).get(2).equals(typeSelID) == true && allServicesList.get(i).get(3).equals(subTypeSelID))
            {
                filteredServiceList.add((String) allServicesList.get(i).get(1));
            }
            
        }
        servicesLV.getItems().setAll(filteredServiceList);
        }
    }
    // Method for pressing the delete selected service button
    public void deleteSelectedButtonPressed()
    {
        
        try {
            String servName = (String) servicesLV.getSelectionModel().getSelectedItem();
            boolean isSomethingSelected = servName.isEmpty();
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this service?");
            if (yesOrNo == 0)
            {
                DBRemove.removeService(servName);
                serviceListPopulate();
            }
            
        } catch (NullPointerException e) {
            Alert selAlert = new Alert(Alert.AlertType.INFORMATION, "To delete a service, please select a service from the list then click Remove Selected", ButtonType.OK);
            selAlert.show();
        }
        
        
        
        

    }
    
    
   
    
}
