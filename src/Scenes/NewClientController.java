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


public class NewClientController{

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
    
    // Method for setup of the new client screen
    public void setupNewClientScreen(SalonKeeper mySalonKeeper)
    {
        this.myKeeper = mySalonKeeper;
        setupStatesCombo();
    }
    // Method for pressing the back button
    public void backButtonPressed()
    {
        myKeeper.newAppointmentMenu();
    }
    // Method for setting up the state combobox selections
    public void setupStatesCombo()
    {
        this.statesList = OtherMethods.Information.getStates();
        stateCB.getItems().setAll(statesList);
    }
    // Method for pressing the save button
    public void saveButtonPressed()
    {
        try {
            List<Object> clientInfoList = new ArrayList<>();
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String phoneNumber = phoneTF.getText();
        String emailString = emailTF.getText();
        String addressString = addressTF.getText();
        String stateString = (String) stateCB.getSelectionModel().getSelectedItem();
        String cityString = cityTF.getText();
        String zipcodeString = zipTF.getText();
        int zipCode = Integer.parseInt(zipcodeString);
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
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Please make sure all the fields have the client information and try again.(The notes field is optional)", ButtonType.OK);
            myAlert.show();
        }
        else
        {
        clientInfoList.add(notesString);
        DBSave.saveNewClientAdmin(clientInfoList);
        }
        } catch (NumberFormatException e) {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Please make sure the zip code is in number form and try again.", ButtonType.OK);
            myAlert.show();
        }
        catch (NullPointerException f)
        {
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Please make sure all the fields have the client information and try again. (The notes field is optional)", ButtonType.OK);
            myAlert.show();
        }
        
    }

    
    
}
