/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jerod
 */
public class DBRequest extends DBConnect{
    
    // Get appointment information
    public static List<List> getAppointmentsWithinTimeframe(List<Object> searchStuff)
    {
        List<List> appointmentsList = new ArrayList<>();
        String getAppointments = "select * from appointments where stylistId = " + searchStuff.get(0) + 
                                 " and date between '" + searchStuff.get(1) + "' and '" + searchStuff.get(2) + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getAppointments);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                List<Object> appointmentInfo = new ArrayList<>();
                appointmentInfo.add(rs.getInt(1));
                appointmentInfo.add(rs.getInt(2));
                appointmentInfo.add(rs.getInt(3));
                appointmentInfo.add(rs.getInt(4));
                appointmentInfo.add(rs.getString(5));
                appointmentInfo.add(rs.getString(6));
                appointmentInfo.add(rs.getString(7));
                appointmentInfo.add(rs.getInt(8));
                appointmentInfo.add(rs.getString(9));
                appointmentsList.add(appointmentInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return appointmentsList;
    }
    
    // Retrieve a list of today's appointments
    public static List<List> getTodaysAppointments(List<Object> searchStuff)
    {
        List<List> appointmentsList = new ArrayList<>();
        String getAppointments = "select * from appointments where stylistId = " + searchStuff.get(0) + 
                                 " and date = '" + searchStuff.get(1) + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getAppointments);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                List<Object> appointmentInfo = new ArrayList<>();
                appointmentInfo.add(rs.getInt(1));
                appointmentInfo.add(rs.getInt(2));
                appointmentInfo.add(rs.getInt(3));
                appointmentInfo.add(rs.getInt(4));
                appointmentInfo.add(rs.getString(5));
                appointmentInfo.add(rs.getString(6));
                appointmentInfo.add(rs.getString(7));
                appointmentInfo.add(rs.getInt(8));
                appointmentInfo.add(rs.getString(9));
                appointmentsList.add(appointmentInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return appointmentsList;
    }
    
    
    // Get Job title information
    public static List<List> getAllJobTitles()
    {
        List<List> jobTitles = new ArrayList<>();
        String getInfo = "select * from jobTitles;";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                List<String> jobTitleInfo = new ArrayList<>();
                jobTitleInfo.add(rs.getString(1));
                jobTitleInfo.add(rs.getString(2));
                jobTitles.add(jobTitleInfo);
            }
            
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return  jobTitles;
    }
    
    // Get a job title id number by the job title name
    public static int getJobTitleId(String jobTitleString)
    {
        int jobId = 0;
        String getJobId = "select * from jobTitles where title = '" + jobTitleString + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getJobId);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                jobId = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return jobId;
    }
    
    // Get the job title name by using the job title id
    public static String getJobTitle(int jobTitleId)
    {
        String jobTitle = null;
        String getJobTitle = "select title from jobTitles where titlesId = " + jobTitleId + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getJobTitle);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                jobTitle = rs.getString(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return jobTitle;
    }
    
    // Get service information

    // Get all services
    public static List<List> getAllServices()
    {
        List<List> allServices = new ArrayList<>();
        String getServiceString = "select * from services;";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getServiceString);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                List<Object> serviceInfo = new ArrayList<>();
                serviceInfo.add(rs.getInt(1));
                serviceInfo.add(rs.getString(2));
                serviceInfo.add(rs.getInt(3));
                serviceInfo.add(rs.getInt(4));
                allServices.add(serviceInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return allServices;
    }
    
    // Get all the different service types
    public static List<List> getAllServiceTypes()
    {
        List<List> serviceTypes = new ArrayList<>();
        String getInfo = "select * from serviceTypes;";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                List<String> serviceTypeInfo = new ArrayList<>();
                serviceTypeInfo.add(rs.getString(1));
                serviceTypeInfo.add(rs.getString(2));
                serviceTypes.add(serviceTypeInfo);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return serviceTypes;
    }
    
    // Get all the service subtypes
    public static List<String> getServiceSubTypeById(int serviceTypeId)
    {
        List<String> serviceTypeList = new ArrayList<>();
        String getInfo = "select * from serviceSubType where typeID = '" + serviceTypeId +"';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                serviceTypeList.add(rs.getString(2));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return serviceTypeList;
    }
    
    // Get the service type id by the service type name
    public static int getServiceTypeId(String serviceType)
    {
        int servType = 0;
        String getInfo = "select * from serviceTypes where typeName = '" + serviceType +"';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                servType = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return servType;
    }
    
    // get the service subtype id by the service subtype name
    public static int getServiceSubTypeId(String servSub)
    {
        int servType = 0;
        String getInfo = "select * from serviceSubType where subTypeName = '" + servSub +"';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                servType = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return servType;
    }
    // Get all of the service subtypes information
    public static List<List> getAllServiceSubTypes()
    {
        List<List> serviceTypes = new ArrayList<>();
        String getInfo = "select * from serviceSubType;";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                List<String> serviceTypeInfo = new ArrayList<>();
                serviceTypeInfo.add(rs.getString(1));
                serviceTypeInfo.add(rs.getString(2));
                serviceTypes.add(serviceTypeInfo);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return serviceTypes;
    }
    // Get a service name by the service id
    public static String getServiceById(int serveID)
    {
        String serviceTitle = null;
        String getServiceTitle = "select serviceName from services where serviceID = " + serveID + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getServiceTitle);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                serviceTitle = rs.getString(1);
            }
        } catch (SQLException e) {
            
        }
        return serviceTitle;
    }
    // Get the service information by the service title
    public static List<Object> getServiceInfoByTitle(String serviceTitle)
    {
        List<Object> serviceInformation = new ArrayList<>();
        String getServiceString = "select * from services where serviceName = '" + serviceTitle + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getServiceString);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                serviceInformation.add(rs.getInt(1));
                serviceInformation.add(rs.getString(2));
                serviceInformation.add(rs.getInt(3));
                serviceInformation.add(rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return serviceInformation;
    }
    // Get the service id by using the service title
    public static int getServiceIdByTitle(String serviceTitle)
    {
        int serviceID = 0;
        String getServiceId = "select serviceID from services where serviceName = '" + serviceTitle + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getServiceId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                serviceID = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return serviceID;
    }
    
    // Get client information
    
    // Get all clients and their information
    public static List<List> getAllClients()
    {
        List<List> allClientList = new ArrayList<>();
        String getAllClients = "select firstName, lastName, phone from clientInfo;";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getAllClients);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                List<String> clientInfo = new ArrayList<>();
                clientInfo.add(rs.getString(1));
                clientInfo.add(rs.getString(2));
                clientInfo.add(rs.getString(3));
                allClientList.add(clientInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return allClientList;
        
    }
    
    // Get a clients first and last name concatenated by using the client id
    public static String getClientByID(int clientID)
    {
        String clientFullName;
        String clientFirstName = null;
        String clientLastName = null;
        String getClient = "select * from clientInfo where clientID = " + clientID + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getClient);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                clientFirstName = rs.getString(2);
                clientLastName = rs.getString(3);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        clientFullName = clientFirstName + " " + clientLastName;
        return clientFullName;
    }
    
    // Get specific client information by using their first name, last name, and phone number
    public static List<Object> getSpecificClient(String lastName, String phoneNum)
    {
        List<Object> clientInfo = new ArrayList<>();
        String getInfo = "select * from clientInfo where lastName = '" + lastName + "' and phone = '" + phoneNum + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                clientInfo.add(rs.getInt(1));
                clientInfo.add(rs.getString(2));
                clientInfo.add(rs.getString(3));
                clientInfo.add(rs.getString(4));
                clientInfo.add(rs.getString(5));
                clientInfo.add(rs.getString(6));
                clientInfo.add(rs.getString(7));
                clientInfo.add(rs.getString(8));
                clientInfo.add(rs.getInt(9));
                clientInfo.add(rs.getString(10));
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return clientInfo;
    }
    
    // Get employee information
    
    // Get an employee id by using their first name, last name, and password
    public static int getEmployeeId(String firstString, String lastString, String passString)
    {
        int employeeId = 0;
        String getEmployeeId = "select id from stylist where firstName = '" + firstString + "' and lastName = '" 
                                                                            + lastString + "' and  password = '" 
                                                                            + passString + "';";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getEmployeeId);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                employeeId = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return employeeId;                                                                       
    }
    
    // Get a list of inactive employees and their information
    public static List<List> getInactiveEmployees()
    {
        List<List> inactiveEmployees = new ArrayList<>();
        String getInactiveString = "select * from stylist where active = 0";
        
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInactiveString);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                List<Object> stylistInfo = new ArrayList<>();
                stylistInfo.add(rs.getInt(1));
                stylistInfo.add(rs.getString(2));
                stylistInfo.add(rs.getString(3));
                stylistInfo.add(rs.getInt(4));
                stylistInfo.add(rs.getString(5));
                stylistInfo.add(rs.getString(6));
                stylistInfo.add(rs.getInt(7));
                inactiveEmployees.add(stylistInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return inactiveEmployees;
    }
    
    // Get a list of active employees and their information
    public static List<List> getActiveEmployees()
    {
        List<List> activeEmployees = new ArrayList<>();
        String getInactiveString = "select * from stylist where active = 1";
        
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInactiveString);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                List<Object> stylistInfo = new ArrayList<>();
                stylistInfo.add(rs.getInt(1));
                stylistInfo.add(rs.getString(2));
                stylistInfo.add(rs.getString(3));
                stylistInfo.add(rs.getInt(4));
                stylistInfo.add(rs.getString(5));
                stylistInfo.add(rs.getString(6));
                stylistInfo.add(rs.getInt(7));
                activeEmployees.add(stylistInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return activeEmployees;
    }
    
    // Get information about an employee by using their id
    public static List<Object> getEmployeeById(int empID)
    {
        List<Object> stylistInfo = new ArrayList<>();
        String getInfo = "select * from stylist where id = " + empID + ";";
        
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getInfo);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                stylistInfo.add(rs.getInt(1));
                stylistInfo.add(rs.getString(2));
                stylistInfo.add(rs.getString(3));
                stylistInfo.add(rs.getInt(4));
                stylistInfo.add(rs.getString(5));
                stylistInfo.add(rs.getString(6));
                stylistInfo.add(rs.getInt(7));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return stylistInfo;
    }
    
    // Get a list of an employees services by their id
    public static List<List> getEmployeeServices(int empId)
    {
        List<List> employeeServices = new ArrayList<>();
        String getServices = "select * from stylistServices where stylistId = " + empId + ";";
        
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getServices);
            ResultSet rs = pstmt.executeQuery();
                    
            while(rs.next())
            {
                List<Object> serviceInfo = new ArrayList<>();
                serviceInfo.add(rs.getInt(2));
                serviceInfo.add(rs.getInt(3));
                serviceInfo.add(rs.getInt(4));
                employeeServices.add(serviceInfo);
            }
                    
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return employeeServices;
    }
    
    // Get a specific service id by using it's entry id
    public static int getServiceIDByEntryID(int entryID)
    {
        int serviceID = 0;
        String getServiceID = "select serviceId from stylistServices where entryId = " + entryID + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getServiceID);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                serviceID = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return serviceID;
    }
    
    // Get a list of the services an employee currently offers by using their id
    public static List<List> getOfferedEmployeeServices(int empId)
    {
        List<List> offeredList = new ArrayList<>();
        String getOffered = "select * from stylistServices where stylistId = " + empId + " and offered = " + 1 + ";";
        try {
            PreparedStatement pstmt = myConnection.prepareStatement(getOffered);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                List<Integer> serviceInfo = new ArrayList<>();
                serviceInfo.add(rs.getInt(1));
                serviceInfo.add(rs.getInt(2));
                serviceInfo.add(rs.getInt(3));
                offeredList.add(serviceInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return offeredList;
    }
   
    
}
