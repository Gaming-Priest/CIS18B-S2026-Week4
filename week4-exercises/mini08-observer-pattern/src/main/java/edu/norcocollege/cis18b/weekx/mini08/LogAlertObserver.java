package edu.norcocollege.cis18b.weekx.mini08;

public class LogEntryObserver implements AlertObserver 
{
    @Override
    public void onAlert(Alert alert) 
    {
        System.out.println("[LOG] Recorded alert at " + new java.util.Date() 
            + " - " + alert.getSeverity() + ": " + alert.getMessage());
    }
}