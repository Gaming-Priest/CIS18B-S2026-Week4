package edu.norcocollege.cis18b.weekx.mini03;

public class AlertValidationApp 
{

    public static void main(String[] args) 
    {
        System.out.println("=== Mini-Assignment 4: Fail-Fast Validation Demo ===\n");
        System.out.println("Test 1: Valid alert");
        Alert valid = new Alert(101, "Database replication lag detected", AlertLevel.WARNING);

        try 
        {
            AlertValidator.validate(valid);
            System.out.println("→ Validation passed: " + valid);
        } 
        catch (InvalidAlertException e) 
        {
            System.out.println("→ Validation failed: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Test 2: Null alert object");
        Alert nullAlert = null;

        try 
        {
            AlertValidator.validate(nullAlert);
            System.out.println("→ This line should NOT be reached");
        } 
        catch (InvalidAlertException e) 
        {
            System.out.println("→ Validation failed: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Test 3: Blank message");
        Alert blankMessage = new Alert(102, "   ", AlertLevel.ERROR);

        try 
        {
            AlertValidator.validate(blankMessage);
            System.out.println("→ This line should NOT be reached");
        } 
        catch (InvalidAlertException e) 
        {
            System.out.println("→ Validation failed: " + e.getMessage());
        }
        System.out.println();

        System.out.println("Test 4: Null level");
        Alert nullLevel = new Alert(103, "Server room temperature critical", null);

        try 
        {
            AlertValidator.validate(nullLevel);
            System.out.println("→ This line should NOT be reached");
        } 
        catch (InvalidAlertException e) 
        {
            System.out.println("→ Validation failed: " + e.getMessage());
        }
    }
}