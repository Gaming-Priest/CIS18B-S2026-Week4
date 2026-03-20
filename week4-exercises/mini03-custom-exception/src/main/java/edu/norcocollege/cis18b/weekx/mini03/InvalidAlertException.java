package edu.norcocollege.cis18b.weekx.mini03;

public class InvalidAlertException extends Exception 
{
    public InvalidAlertException(String message) 
    {
        super(message);
    }

    public InvalidAlertException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}