package edu.norcocollege.cis18b.weekx.mini08;

import java.util.ArrayList;
import java.util.List;

public class AlertService 
{

    private final AlertRepository repository;
    private final AlertValidator validator;
    
    private final List<AlertObserver> observers = new ArrayList<>();

    public AlertService(AlertRepository repository, AlertValidator validator) 
    {
        this.repository = repository;
        this.validator = validator;
    }

    public void addObserver(AlertObserver observer) 
    {
        if (observer != null) 
        {
            observers.add(observer);
        }
    }

    public void removeObserver(AlertObserver observer) 
    {
        observers.remove(observer);
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
        } catch (InvalidAlertException e) 
        {
            throw e;
        } catch (Exception e) {
            throw new AlertProcessingException("Unexpected validation error", e);
        }

        try 
        {
            repository.save(alert);
        } catch (AlertStorageException e) 
        {
            throw e;
        } catch (Exception e) 
        {
            throw new AlertProcessingException("Unexpected storage error", e);
        }

        notifyObservers(alert);
    }

    private void notifyObservers(Alert alert) 
    {
        for (AlertObserver observer : observers) 
            {
            try 
            {
                observer.onAlert(alert);
            } 
            catch (Exception e) 
            {
                System.err.println("Observer failed: " + e.getMessage());
            }
        }
    }
}