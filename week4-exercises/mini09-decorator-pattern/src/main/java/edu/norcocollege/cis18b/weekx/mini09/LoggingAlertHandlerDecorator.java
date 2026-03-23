package edu.norcocollege.cis18b.weekx.mini09;

public class LoggingAlertHandlerDecorator implements AlertHandler 
{

    private final AlertHandler wrappedHandler;

    public LoggingAlertHandlerDecorator(AlertHandler handlerToWrap) 
    {
        if (handlerToWrap == null) 
        {
            throw new IllegalArgumentException("Wrapped handler cannot be null");
        }
        this.wrappedHandler = handlerToWrap;
    }

    @Override
    public void handle(Alert alert) 
    {
        System.out.println("[LOG] Starting to handle alert: " 
            + (alert != null ? alert.getMessage() : "null") 
            + " at " + java.time.Instant.now());

        try 
        {
            wrappedHandler.handle(alert);
            System.out.println("[LOG] Successfully handled alert: " 
                + (alert != null ? alert.getMessage() : "null"));
        } 
        catch (Exception e) 
        {
            System.out.println("[LOG] Failed to handle alert: " 
                + (alert != null ? alert.getMessage() : "null") 
                + " → " + e.getClass().getSimpleName() + ": " + e.getMessage());
            
            throw e;
        }
    }
}