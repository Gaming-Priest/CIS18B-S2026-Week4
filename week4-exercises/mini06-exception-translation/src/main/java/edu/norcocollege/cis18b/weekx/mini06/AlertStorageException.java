package edu.norcocollege.cis18b.weekx.mini06;

public class AlertStorageException extends Exception 
{
    public AlertStorageException(string message)
    {
        super(message);
    }
    
    public AlertStorageException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
