package edu.norcocollege.cis18b.weekx.mini06;

public class RepositoryDemo 
{
    public static void main(String[] args) 
    {
        AlertRepository repository = new InMemoryAlertRepository();
        Alert alert = new Alert(1, "Disk usage critical", AlertLevel.CRITICAL);

        try 
        {
            repository.save(alert);
            System.out.println("Alert saved.");
        } 
        catch (AlertStorageException ex) 
        {
            System.out.println("Storage error: " + ex.getMessage());
            if (ex.getCause() != null) 
            {
                System.out.println("Cause: " + ex.getCause().getMessage());
            }
        }
    }
}
