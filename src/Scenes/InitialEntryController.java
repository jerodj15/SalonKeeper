/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import MainClass.SalonKeeper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jerod
 */
public class InitialEntryController {

    @FXML
    private Button submitButton;
    @FXML
    private TextField ipTF;
    @FXML
    private TextField uNameTF;
    @FXML
    private TextField passField;

    private SalonKeeper myKeeper;
       
    public void setupInitScreen(SalonKeeper mySalonKeeper)
    {
        this.myKeeper = mySalonKeeper;
    }
    
    public void submitButtonPressed()
    {
        String ipAddress = ipTF.getText();
        String userName = uNameTF.getText();
        String userPass = passField.getText();
        
        if (ipAddress.isEmpty() || userName.isEmpty() || userPass.isEmpty())
        {
            Alert myAlert = new Alert(Alert.AlertType.ERROR, "Please make sure all the information is filled out an try again.", ButtonType.OK);
            myAlert.show();
        }
        else
        {
            int dataConn = Database.DBConnect.connectToDB(ipAddress, userName, userPass);
            if (dataConn == 1)
            {
                myKeeper.initialScreen();
                Alert myAlert = new Alert(Alert.AlertType.ERROR, "The credentials you entered did not allow you to conenct. Please check your spelling and try again.", ButtonType.OK);
                myAlert.show();
            }
            else
            {
                myKeeper.loginScreen();
            }
            
            
            
        }
    }
    
}
