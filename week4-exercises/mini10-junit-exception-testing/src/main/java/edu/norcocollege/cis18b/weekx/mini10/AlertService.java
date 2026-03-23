package edu.norcocollege.cis18b.weekx.mini10;

public class AlertService 
{
    private final AlertRepository repository;
    private final AlertValidator validator = new AlertValidator();

    public AlertService(AlertRepository repository) 
    {
        this.repository = repository;
    }

    public void processAlert(Alert alert) throws InvalidAlertException, AlertStorageException 
    {
        validator.validate(alert);

        try
        {
            repository.save(alert);
        }
        catch (RuntimeException ex)
        {
            throw new AlertStorageException("Failed to store alert", ex);
        }
    }

    public int getAlertCount() {
        return repository.findAll().size();
    }
}
