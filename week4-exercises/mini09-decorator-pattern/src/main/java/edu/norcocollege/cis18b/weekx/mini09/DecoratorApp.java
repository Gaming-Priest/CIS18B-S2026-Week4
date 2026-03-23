package edu.norcocollege.cis18b.weekx.mini09;

public class DecoratorApp 
{

    public static void main(String[] args) 
    {
        AlertHandler basicHandler = new BasicAlertHandler();

        AlertHandler loggingHandler = new LoggingAlertHandlerDecorator(basicHandler);

        Alert alert = new Alert("Server CPU at 98%", "CRITICAL", System.currentTimeMillis());
        Alert nullAlert = null;

        System.out.println("=== Using decorated handler ===\n");
        
        loggingHandler.handle(alert);
        
        System.out.println("\n=== Trying with null (should still log) ===\n");
        try 
        {
            loggingHandler.handle(nullAlert);
        } 
        catch (Exception e) 
        {

        }

        System.out.println("\n=== Using plain basic handler (no extra logs) ===\n");
        basicHandler.handle(alert);
    }
}
