/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SetGet;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jerod
 */
public class Appointment {
    
    private final SimpleStringProperty startingTime;
    private final SimpleStringProperty endingTime;
    private final SimpleStringProperty clientString;
    private final SimpleStringProperty serviceString;
    private final SimpleStringProperty serviceDate;
            
    
    // Method for creating the appointment object
    public Appointment(String startTime, String endTime, String clientName, String serviceName, String serviceDateString)
    {
        this.startingTime = new SimpleStringProperty(startTime);
        this.endingTime = new SimpleStringProperty(endTime);
        this.clientString = new SimpleStringProperty(clientName);
        this.serviceString = new SimpleStringProperty(serviceName);
        this.serviceDate = new SimpleStringProperty(serviceDateString);
    }
    // Method for setting the start
    public void setStart (String startString)
    {
        startingTime.set(startString);
    }
    // Method for getting the start
    public String getStart()
    {
        return startingTime.get();
    }
    // Method for setting the end
    public void setEnd (String endString)
    {
        endingTime.set(endString);
    }
    // Method for getting the end
    public String getEnd()
    {
        return endingTime.get();
    }
    // Method for setting the client name
    public void setClient(String clientNameString)
    {
        clientString.set(clientNameString);
    }
    // Method for getting the client name
    public String getClient()
    {
        return clientString.get();
    }
    // Method for setting the service
    public void setService(String serviceNameString)
    {
        serviceString.set(serviceNameString);
    }
    // Method for getting the service
    public String getService()
    {
        return serviceString.get();
    }
    // Method for setting the appointment date
    public void setServiceDate(String servDate)
    {
        serviceDate.set(servDate);
    }
    // Method for getting the appointment date
    public String getServiceDate()
    {
        return serviceDate.get();
    }
    
    
}
