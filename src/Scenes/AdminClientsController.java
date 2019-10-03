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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jerod
 */
public class AdminClientsController{

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
    private ComboBox stateComboBox;
    @FXML
    private ListView ClientsLV;
    @FXML
    private TextField cleintIDTF;
    @FXML
    private TextField searchTF;
    @FXML
    private TextArea notesTA;
    
    
    private SalonKeeper myKeeper;
    List<List> clientList;
    List<List> searchedClientList = new ArrayList<>();
    boolean searchPressed = false;
    
    // Initial setup of the client screen
    public void setupClentScreen(SalonKeeper mySalonKeeper)
    {
        List<String> stateStrings = OtherMethods.Information.getStates();
        this.myKeeper = mySalonKeeper;
        stateComboBox.getItems().setAll(stateStrings);
        setupClientList();
    }
    // Method for pushing the back button
    public void backButtonPressed()
    {
        myKeeper.mainScreen();
    }
    // Method for pushing the create new client button
    public void createNewClient()
    {
        myKeeper.adminNewClientMenu();
    }
    // Method for editing the current client
    public void editButtonPressed()
    {
        firstNameTF.setEditable(true);
        firstNameTF.setDisable(false);
        lastNameTF.setEditable(true);
        lastNameTF.setDisable(false);
        phoneTF.setEditable(true);
        phoneTF.setDisable(false);
        emailTF.setEditable(true);
        emailTF.setDisable(false);
        addressTF.setEditable(true);
        addressTF.setDisable(false);
        cityTF.setEditable(true);
        cityTF.setDisable(false);
        zipTF.setEditable(true);
        zipTF.setDisable(false);
        stateComboBox.setDisable(false);
    }
    // Method for pushing the select button
    public void selectButtonPressed()
    {
        if (searchPressed == false)
        {
        int selectedClientIndex = ClientsLV.getSelectionModel().getSelectedIndex();
        String selLastName = (String) clientList.get(selectedClientIndex).get(1);
        System.out.println(selLastName);
        String selPhone = (String) clientList.get(selectedClientIndex).get(2);
        List<Object> selectedClientInfo = DBRequest.getSpecificClient(selLastName, selPhone);
        
        // Set the client details section
        String clientId = String.valueOf(selectedClientInfo.get(0));
        String clientZip = String.valueOf(selectedClientInfo.get(8));
        cleintIDTF.setText(clientId);
        firstNameTF.setText((String) selectedClientInfo.get(1));
        lastNameTF.setText((String) selectedClientInfo.get(2));
        phoneTF.setText((String) selectedClientInfo.get(4));
        emailTF.setText((String) selectedClientInfo.get(5));
        addressTF.setText((String) selectedClientInfo.get(3));
        stateComboBox.getSelectionModel().select(selectedClientInfo.get(6));
        cityTF.setText((String) selectedClientInfo.get(7));
        zipTF.setText(clientZip);
        notesTA.setText((String) selectedClientInfo.get(9));
        }
        if (searchPressed == true)
        {
        int selectedClientIndex = ClientsLV.getSelectionModel().getSelectedIndex();
        String selLastName = (String) searchedClientList.get(selectedClientIndex).get(1);
        System.out.println(selLastName);
        String selPhone = (String) searchedClientList.get(selectedClientIndex).get(2);
        List<Object> selectedClientInfo = DBRequest.getSpecificClient(selLastName, selPhone);
        
        // Set the client details section
        String clientId = String.valueOf(selectedClientInfo.get(0));
        String clientZip = String.valueOf(selectedClientInfo.get(8));
        cleintIDTF.setText(clientId);
        firstNameTF.setText((String) selectedClientInfo.get(1));
        lastNameTF.setText((String) selectedClientInfo.get(2));
        phoneTF.setText((String) selectedClientInfo.get(4));
        emailTF.setText((String) selectedClientInfo.get(5));
        addressTF.setText((String) selectedClientInfo.get(3));
        stateComboBox.getSelectionModel().select(selectedClientInfo.get(6));
        cityTF.setText((String) selectedClientInfo.get(7));
        zipTF.setText(clientZip);
        notesTA.setText((String) selectedClientInfo.get(9));
        }
        
        // Setup the client appointment history
        
    }
    // Method for pushing the search button
    public void searchButtonPressed()
    {
        searchPressed = true;
        String searchCriteria = searchTF.getText();
        searchedClientList.clear();
        for (int i = 0; i < clientList.size(); i++)
        {
            // Reset using blank search
            if (searchCriteria.isEmpty())
            {
                ClientsLV.getItems().setAll(clientList);
                searchPressed = false;
            }
            
            // Check first names
            if (searchCriteria.equalsIgnoreCase((String) clientList.get(i).get(0)))
            {
                searchedClientList.add(clientList.get(i));
                ClientsLV.getItems().setAll(searchedClientList);
            }
            // Check last names
            if (searchCriteria.equalsIgnoreCase((String) clientList.get(i).get(1)))
            {
                searchedClientList.add(clientList.get(i));
                ClientsLV.getItems().setAll(searchedClientList);
            }
            // Check phone number
            if (searchCriteria.equalsIgnoreCase((String) clientList.get(i).get(2)))
            {
                searchedClientList.add(clientList.get(i));
                ClientsLV.getItems().setAll(searchedClientList);
            }
            
            
        }
    }
    // Method for pushing the save button
    public void saveEditedClient()
    {
        List<Object> editClientInfo = new ArrayList<>();
        String clientId = cleintIDTF.getText();
        int clientID = Integer.parseInt(clientId);
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String phoneNum = phoneTF.getText();
        String emailString = emailTF.getText();
        String addressString = addressTF.getText();
        String stateString = (String) stateComboBox.getSelectionModel().getSelectedItem();
        String cityString = cityTF.getText();
        String zipString = zipTF.getText();
        String clientNotes = notesTA.getText();
        int zipInt = Integer.parseInt(zipString);
        // Add the items to the list
        editClientInfo.add(clientID);
        editClientInfo.add(firstName);
        editClientInfo.add(lastName);
        editClientInfo.add(phoneNum);
        editClientInfo.add(emailString);
        editClientInfo.add(addressString);
        editClientInfo.add(stateString);
        editClientInfo.add(cityString);
        editClientInfo.add(zipInt);
        
        if (editClientInfo.contains(""))
        {
            Alert allFields = new Alert(Alert.AlertType.INFORMATION, "All of the fields must contain information, select the client and press the select button to repopulate the fields.", ButtonType.OK);
            allFields.show();
        }
        else
        {
        // Clear the information from the details section
        editClientInfo.add(clientNotes);
        notesTA.clear();
        cleintIDTF.clear();
        firstNameTF.clear();
        lastNameTF.clear();
        phoneTF.clear();
        emailTF.clear();
        addressTF.clear();
        stateComboBox.getSelectionModel().clearSelection();
        cityTF.clear();
        zipTF.clear();
        // Redisable the details boxes
        firstNameTF.setEditable(false);
        firstNameTF.setDisable(true);
        lastNameTF.setEditable(false);
        lastNameTF.setDisable(true);
        phoneTF.setEditable(false);
        phoneTF.setDisable(true);
        emailTF.setEditable(false);
        emailTF.setDisable(true);
        addressTF.setEditable(false);
        addressTF.setDisable(true);
        cityTF.setEditable(false);
        cityTF.setDisable(true);
        zipTF.setEditable(false);
        zipTF.setDisable(true);
        stateComboBox.setDisable(true);
        DBSave.saveEditedClientAdmin(editClientInfo);
        setupClientList();
        }
    }
    // Method for setting up the list of clients
    public void setupClientList()
    {
        clientList = DBRequest.getAllClients();
        ClientsLV.getItems().setAll(clientList);
    }
    
}
