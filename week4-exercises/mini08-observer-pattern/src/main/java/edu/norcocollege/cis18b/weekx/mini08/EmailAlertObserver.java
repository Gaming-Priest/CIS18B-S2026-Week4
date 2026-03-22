package edu.norcocollege.cis18b.weekx.mini08;

public class EmailNotificationObserver implements AlertObserver 
{
    @Override
    public void onAlert(Alert alert) 
    {
        System.out.println("[EMAIL] Sending notification for alert: " 
            + alert.getMessage() 
            + " (Severity: " + alert.getSeverity() + ")");
    }
}