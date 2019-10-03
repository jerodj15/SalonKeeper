/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
    static java.sql.Connection myConnection; 
    private static final String beginDbUrl = "jdbc:mysql://";
    private static final String endDbUrl = ":3306/SalonKeeper";
    static String dbUrl;
    
    // Connect to the database
    public static int connectToDB(String ipString, String useString, String pasString) {
        String ipAdd = ipString;
        String userName = useString;
        String passwordString = pasString;
        dbUrl = beginDbUrl + ipAdd + endDbUrl;
        try {
            java.sql.Connection dbConn = DriverManager.getConnection(dbUrl, userName, passwordString);
            myConnection = dbConn;
            return 0;

        } catch (SQLException e) {
            System.out.println("hereherehereh");
            System.out.println(e.getLocalizedMessage());
            return 1;
        }
    }
    // Getter for connection to the database
    public static java.sql.Connection getConnection()
    {
        return myConnection;
    }
    // Method to stop the connection
    public static void stopConnection()
    {
        try {
            myConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
}
