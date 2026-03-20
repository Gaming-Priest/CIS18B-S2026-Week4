package edu.norcocollege.cis18b.weekx.mini01;

public class BuiltInExceptionDemo 
{
    public static void main(String[] args) 
    {
        int numerator = 10;
        int denominator = 0;
        String[] alerts = {"CPU High", "Disk Full", "Login Failure"};

        try
        {
            int result = numerator / denominator;
            system.out.println("Result of division: " + result);
        }
        catch (ArithmeticException e)
        {
            System.out.println("Cannot devide by zero!");
        }

        try
        {
            System.out.println("Alert at index 5: " + alerts[5]);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Invalid alert index: index is out of range.");
        }

        System.out.println("Program completed.");
    }
}
