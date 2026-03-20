package edu.norcocollege.cis18b.weekx.mini03;

public class Alert 
{
    
    private int id;
    private String message;
    private AlertLevel level;
    
    public Alert(int id, String message, AlertLevel level) 
    {
        this.id = id;
        this.message = message;
        this.level = level;
    }
    
    public int getId() 
    {
        return id;
    }
    
    public String getMessage() 
    {
        return message;
    }
    
    public AlertLevel getLevel() 
    {
        return level;
    }
    
    public void setId(int id) 
    {
        this.id = id;
    }
    
    public void setMessage(String message) 
    {
        this.message = message;
    }
    
    public void setLevel(AlertLevel level) 
    {
        this.level = level;
    }
}