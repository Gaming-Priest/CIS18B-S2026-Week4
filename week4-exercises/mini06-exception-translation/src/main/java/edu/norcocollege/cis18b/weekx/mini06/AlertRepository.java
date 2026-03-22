package edu.norcocollege.cis18b.weekx.mini06;

public interface AlertRepository 
{
    void save(Alert alert) throws AlertStorageException;
}
