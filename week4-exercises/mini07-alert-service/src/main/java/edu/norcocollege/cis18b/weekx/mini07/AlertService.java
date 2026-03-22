package edu.norcocollege.cis18b.weekx.mini07;

public class AlertService 
{

    private final AlertRepository repository;
    private final AlertValidator validator;

    public AlertService(AlertRepository repository, AlertValidator validator) 
    {
        this.repository = repository;
        this.validator = validator;
    }

    public void processAlert(Alert alert) 
    {
        if (alert == null) 
        {
            throw new InvalidAlertException("Alert cannot be null");
        }
        try 
        {
            validator.validate(alert);
        } 
        catch (InvalidAlertException e) 
        {
            throw e;
        } 
        catch (Exception e) 
        {
            throw new AlertProcessingException("Unexpected error during alert validation", e);
        }
        try 
        {
            repository.save(alert);
        } 
        catch (AlertStorageException e) 
        {
            throw e;
        } 
        atch (Exception e) 
        {
            throw new AlertProcessingException("Failed to process alert due to unexpected storage error", e);
        }
    }
}