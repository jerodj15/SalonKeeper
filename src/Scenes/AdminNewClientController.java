/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBSave;
import MainClass.SalonKeeper;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AdminNewClientController{

    @FXML
    private Button backButton;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField zipTF;
    @FXML
    private TextArea notesTA;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private ComboBox stateCB;

    private SalonKeeper myKeeper;
    private List<String> statesList;
    int zipCode;
    
    // Method for initial setup of the new client screen
    public void setupAdminNewClientMenu(SalonKeeper mySalonKeeper)
    {
        this.myKeeper = mySalonKeeper;
        
        setupStatesCombo();
        
    }
    // Method for pressing the back button
    public void backButtonPressed()
    {
        myKeeper.adminClientsMenu();
    }
    // Populate the state Combo Box
    public void setupStatesCombo()
    {
        this.statesList = OtherMethods.Information.getStates();
        stateCB.getItems().setAll(statesList);
    }
    // Method for pressing the save button
    public void saveButtonPressed()
    {
        List<Object> clientInfoList = new ArrayList<>();
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String phoneNumber = phoneTF.getText();
        String emailString = emailTF.getText();
        String addressString = addressTF.getText();
        String stateString = (String) stateCB.getSelectionModel().getSelectedItem();
        String cityString = cityTF.getText();
        String zipcodeString = zipTF.getText();
        try {
            zipCode = Integer.parseInt(zipcodeString);
        } catch (NumberFormatException e) {
            Alert numberAlert = new Alert(Alert.AlertType.INFORMATION, "The zip code must be numbers", ButtonType.OK);
            numberAlert.show();
        }
        
        String notesString = notesTA.getText();
        clientInfoList.add(firstName);
        clientInfoList.add(lastName);
        clientInfoList.add(addressString);
        clientInfoList.add(phoneNumber);
        clientInfoList.add(emailString);
        clientInfoList.add(stateString);
        clientInfoList.add(cityString);
        clientInfoList.add(zipCode);
        
        if (clientInfoList.contains(""))
        {
            Alert allFields = new Alert(Alert.AlertType.INFORMATION, "All of the fields must contain information. The notes area can be left blank.", ButtonType.OK);
            allFields.show();
        }
        else
        {
        clientInfoList.add(notesString);
        System.out.println(clientInfoList);
        DBSave.saveNewClientAdmin(clientInfoList);
        }
    }
    
}
