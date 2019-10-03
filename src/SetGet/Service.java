package SetGet;

import java.util.List;


public class Service {
    
    private int serviceId;
    private String serviceName;
    private int serviceType;
    private int serviceSubtype;
    
    public Service(List<Object> serviceList)
    {
        this.serviceId = (int) serviceList.get(0);
        this.serviceName = (String) serviceList.get(1);
        this.serviceType = (int) serviceList.get(2);
        this.serviceSubtype = (int) serviceList.get(3);
    }
    
    public void setServiceId(int serviceID)
    {
        this.serviceId = serviceID;
    }
    public int getServiceId()
    {
        return this.serviceId;
    }
    public void setServiceName(String nameService)
    {
        this.serviceName = nameService;
    }
    public String getServiceName()
    {
        return this.serviceName;
    }
    public void setServiceType(int typeService)
    {
        this.serviceType = typeService;
    }
    public int getServiceType()
    {
        return this.serviceType;
    }
    public void setServiceSubtype(int subtype)
    {
        this.serviceSubtype = subtype;
    }
    public int getServiceSubtype()
    {
        return this.serviceSubtype;
    }
    
}
