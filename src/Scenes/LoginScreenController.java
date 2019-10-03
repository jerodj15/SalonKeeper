/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Database.DBRequest;
import MainClass.SalonKeeper;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginScreenController{

    @FXML
    private TextField idTF;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    
    private SalonKeeper myKeeper;

    // Method for initially setting up the login screen
    public void setupLoginScreen(SalonKeeper mySalonKeeper)
    {
        this.myKeeper = mySalonKeeper;
        
    }
    
    // Enter key for password
    public void onEnterPressed()
    {
        loginPressed();
    }
    
    // Setup the the login Button
    public void loginPressed()
    {
        boolean isFound = false;
        String userEnteredId = idTF.getText();
        String userEnteredPassword = passwordField.getText();
        int userId = Integer.parseInt(userEnteredId);
        List<List> currentEmployees = DBRequest.getActiveEmployees();
        // need 0 = id and 5= password
        
        for (int i = 0; i < currentEmployees.size(); i++)
        {
            int empID = (int) currentEmployees.get(i).get(0);
            String empPass = (String) currentEmployees.get(i).get(5);
            if (empID == userId && empPass.equals(userEnteredPassword))
            {
               isFound = true;
            }
        }
        if (isFound == true)
        {
            myKeeper.userID = userId;
            myKeeper.mainScreen();
        }
        else
        {
            Alert incAlert = new Alert(Alert.AlertType.INFORMATION, "The username and / or password combination you have entered is invalid", ButtonType.OK);
            incAlert.show();
        }
        
        
        
        
    }
      
    
}
