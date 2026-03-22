package edu.norcocollege.cis18b.weekx.mini06;

public class InMemoryAlertRepository implements AlertRepository 
{
    @Override
    public void save(Alert alert) throws AlertStorageException 
    {
        try 
        {
            performLowLevelSave(alert);
        } 
        catch (RuntimeException ex) 
        {
            throw new AlertStorageException("Failed to save alert ID " + alert.getId() + ": " + ex.getMessage(), ex);
        }
    }

    private void performLowLevelSave(Alert alert)
    {
        throw new RuntimeException("Disk full - cannot write alert data");
    }
}
