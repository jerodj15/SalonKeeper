package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class DBSave extends DBConnect{
    
    // Save employee information
    public static void saveNewEmployee(List<Object> employeeInfo)
    {
        String saveEmployee = "insert into stylist (firstName, lastName, title, birthdate, password, active) values ('" + employeeInfo.get(0) + "', '" + employeeInfo.get(1) + "', '" +
                                                                                                                          employeeInfo.get(2) + "', '" + employeeInfo.get(3) + "', '" +
                                                                                                                          employeeInfo.get(4) + "', " + 0 + ");";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(saveEmployee);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Save changes to an existing employee
    public static void saveEditedEmployee(List<Object> employeeInfo)
    {
        String saveEdited = "update stylist set firstName = '" + employeeInfo.get(1) + "', " +
                                                "lastName = '" + employeeInfo.get(2) + "', " +
                                                "title = " + employeeInfo.get(3) + ", " +
                                                "birthdate = '" + employeeInfo.get(4) + "', " +
                                                "password = '" + employeeInfo.get(5) + "' " +
                                                " where id = " + employeeInfo.get(0) + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(saveEdited);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
                                                
    }
    
    // Move employee to inactive
    public static void moveToActive(int stylistID)
    {
        String moveActive = "update stylist set active = 1 where id = " + stylistID + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(moveActive);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Move employee to inactive
    public static void moveToInactive(int stylistID)
    {
        String moveInactive = "update stylist set active = 0 where id = " + stylistID + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(moveInactive);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Save employee service information
    public static void saveOfferedServices(List<Integer> serviceIds, List<Integer> nonOfferedList , int employeeId)
    {
        int idListSize = serviceIds.size();
        int nonOfferedSize = nonOfferedList.size();
        for (int i = 0; i < idListSize; i++)
        {
            String updateOffered = "update stylistServices set offered = 1 where serviceId = " + serviceIds.get(i) + " and stylistId = " + employeeId + ";";
            try {
                PreparedStatement pstmt = myConnection.prepareStatement(updateOffered);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        for (int i = 0; i < nonOfferedSize; i++)
        {
            String updateNonOffered = "update stylistServices set offered = 0 where serviceId = " + nonOfferedList.get(i) + " and stylistId = " + employeeId + ";";
            try {
                PreparedStatement pstmt = myConnection.prepareStatement(updateNonOffered);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    
    // Save changes to an employees offered services
    public static void saveNewOfferedServices(List<Integer> serviceIds, List<Integer> nonOfferedList , int employeeId)
    {
        int idListSize = serviceIds.size();
        for (int i = 0; i < idListSize; i++)
        {
            String saveOffered = "insert into stylistServices (stylistId, serviceId, offered) values (" + employeeId + ", " + serviceIds.get(i) + "," + 1 + ");";
            
            try {
                PreparedStatement pstmt = myConnection.prepareStatement(saveOffered);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        for (int i = 0; i < nonOfferedList.size(); i++)
        {
            String saveNotOffered = "insert into stylistServices (stylistId, serviceId, offered) values (" + employeeId + ", " + nonOfferedList.get(i) + ", " + 0 + ");";
            try {
                PreparedStatement pstmt = myConnection.prepareStatement(saveNotOffered);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    
    
    // Save client information
    public static void saveNewClientAdmin(List<Object> clientInfo)
    {
        String saveClient = "insert into clientInfo (firstName, lastName, address, phone, email, state, city, zip, notes) values ('" + clientInfo.get(0) + "', '" + clientInfo.get(1) + "', '" + clientInfo.get(2) 
                + "', '" + clientInfo.get(3) + "', '" + clientInfo.get(4) + "', '" + clientInfo.get(5) + "', '" + clientInfo.get(6) + "', '" + clientInfo.get(7) + "', '" + clientInfo.get(8) +"');";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(saveClient);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void saveEditedClientAdmin(List<Object> clientInfo)
    {
        String updateClient = "update clientInfo set firstName = '" + clientInfo.get(1) + "', "
                                                   + "lastName = '" + clientInfo.get(2) + "', "
                                                   + "phone = '" + clientInfo.get(3) + "', "
                                                   + "email = '" + clientInfo.get(4) + "', "
                                                   + "address = '" + clientInfo.get(5) + "', "
                                                   + "state = '" + clientInfo.get(6) + "', "
                                                   + "city = '" + clientInfo.get(7) + "', "
                                                   + "zip = " + clientInfo.get(8) + ", " 
                                                   + "notes = '" + clientInfo.get(9) + "' where clientID = " + clientInfo.get(0) + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(updateClient);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    
    // Save service information
    public static void saveNewService(List<Object> serviceInfo)
    {
        String saveService = "insert into services (serviceName, serviceType, serviceSubType) values ('" + serviceInfo.get(0) + "', '" + serviceInfo.get(1) + "', '" + serviceInfo.get(2) + "');";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(saveService);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Save new appointment
    public static void saveNewAppointment(List<Object> apptInformation)
    {
        String saveApt = "insert into appointments (stylistId, serviceId, clientId, date, startTime, endTime, createdBy, createDate) values (" +
                                                    apptInformation.get(0) + ", " + apptInformation.get(1) + ", " + apptInformation.get(2) + ", '" +
                                                    apptInformation.get(3) + "', '" + apptInformation.get(4) + "', '" + apptInformation.get(5) + "', " +
                                                    apptInformation.get(6) + ", '" + apptInformation.get(7) + "');";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(saveApt);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
