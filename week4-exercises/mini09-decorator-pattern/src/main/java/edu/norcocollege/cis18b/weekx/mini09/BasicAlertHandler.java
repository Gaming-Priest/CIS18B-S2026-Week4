package edu.norcocollege.cis18b.weekx.mini09;

public class BasicAlertHandler implements AlertHandler 
{

    @Override
    public void handle(Alert alert) 
    {
        if (alert == null) 
        {
            throw new IllegalArgumentException("Alert cannot be null");
        }
        
        System.out.println("Handling alert: " + alert.getMessage() 
            + " [severity=" + alert.getSeverity() + "]");
        
    }
}