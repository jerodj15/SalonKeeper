/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jerod
 */
public class DBRemove extends DBConnect{
    
    // Remove service
    public static void removeService(String serviceName)
    {
        String removeService = "delete from services where serviceName = '" + serviceName + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(removeService);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Remove Stylist
    public static void removeStylist(int stylistID)
    {
        String removeStylist = "delete from stylist where id = " + stylistID + ";";
        String removeStylistServices = "delete from stylistServices where stylistId = " + stylistID + ";";
        try {
            PreparedStatement pstmt1 = myConnection.prepareStatement(removeStylist);
            PreparedStatement pstmt2 = myConnection.prepareStatement(removeStylistServices);
            pstmt1.execute();
            pstmt2.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    // Remove appointment
    public static void deleteAppointment(int entryID)
    {
        String removeAppt = "delete from appointments where appointmentId = " + entryID + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(removeAppt);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
