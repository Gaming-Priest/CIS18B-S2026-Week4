package edu.norcocollege.cis18b.weekx.mini10;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAlertRepository implements AlertRepository 
{
    private final List<Alert> alerts = new ArrayList<>();

    @Override
    public void save(Alert alert) throws AlertStorageException 
    {
        alerts.add(alert);
    }

    @Override
    public List<Alert> findAll() 
    {
        return new ArrayList<>(alerts);
    }
}
