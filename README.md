
# Week 4 Lesson: Exceptions, Debugging, and Error Handling Patterns

# How to use this README.md

This repository contains the lesson for this week (`README.md`, the file you're reading now), as well as your assignments and exercises for this week (the `exercises/` folder).

Please read the lesson content and then complete the exercises to receive full credit.

Additional details about how to use each exercise are in the `README.md` file in the `exercises/` folder.

## Learning Objectives

By the end of this lesson, you will be able to:

* Explain how Java handles runtime errors using exceptions
* Use `try`, `catch`, `finally`, and `throw` appropriately
* Create and use custom exceptions
* Use a debugger in VS Code or GitHub Codespaces to trace program execution
* Apply common **exception handling patterns**
* Recognize how **design patterns** such as Observer and Decorator support robust error handling

---

# Part 1 — Exceptions Overview

## Reading
Read the **Exceptions** lesson on the Java learning site:

[https://dev.java/learn/exceptions/](https://dev.java/learn/exceptions/)

Focus especially on:

* Checked vs unchecked exceptions
* The `Throwable` hierarchy
* The purpose of `try`, `catch`, and `finally`
* Creating custom exceptions

---

## Why Exceptions Exist

Exceptions allow a program to handle **unexpected situations** without crashing the entire application.

Examples:

* File not found
* Network timeout
* Invalid user input
* Database connection failure

Without exception handling, your program might terminate immediately. With exception handling, you can **recover or fail gracefully**.

---

## The Java Exception Hierarchy

```
Throwable
 ├── Error
 │     └── OutOfMemoryError
 │
 └── Exception
       ├── RuntimeException
       │     ├── NullPointerException
       │     ├── IllegalArgumentException
       │     └── IndexOutOfBoundsException
       │
       └── Checked Exceptions
             ├── IOException
             ├── SQLException
             └── FileNotFoundException
```

Key rule:

* **Checked exceptions must be handled or declared**
* **Runtime exceptions do not have to be handled**

---

## Example: Basic Exception Handling

```java
public class DivisionExample {

    public static void main(String[] args) {

        int numerator = 10;
        int denominator = 0;

        try {
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException ex) {
            System.out.println("Cannot divide by zero!");
        }

        System.out.println("Program continues running...");
    }
}
```

Output:

```
Cannot divide by zero!
Program continues running...
```

---

## The `finally` Block

`finally` runs **no matter what happens**.

Example:

```java
public class FinallyExample {

    public static void main(String[] args) {

        try {
            System.out.println("Opening connection...");
            throw new RuntimeException("Connection failed");

        } catch (RuntimeException ex) {
            System.out.println("Handling error: " + ex.getMessage());

        } finally {
            System.out.println("Closing resources");
        }
    }
}
```

Output:

```
Opening connection...
Handling error: Connection failed
Closing resources
```

---

## Example: Custom Exception

Custom exceptions help express **domain-specific errors**.

Example: Security monitoring system.

### Step 1 — Create a Custom Exception

```java
public class SecurityAlertException extends Exception {

    public SecurityAlertException(String message) {
        super(message);
    }
}
```

---

### Step 2 — Throw the Exception

```java
public class LoginMonitor {

    public static void checkLoginAttempts(int attempts) throws SecurityAlertException {

        if (attempts > 5) {
            throw new SecurityAlertException("Too many login attempts detected");
        }

        System.out.println("Login attempts acceptable");
    }

    public static void main(String[] args) {

        try {
            checkLoginAttempts(7);
        } catch (SecurityAlertException e) {
            System.out.println("SECURITY ALERT: " + e.getMessage());
        }
    }
}
```

Output:

```
SECURITY ALERT: Too many login attempts detected
```

---

# Part 2 — Debugging

## Reading
Read the **Debugging** lesson:

[https://dev.java/learn/debugging/](https://dev.java/learn/debugging/)

You should learn how to:

* Set breakpoints
* Step through code
* Inspect variables
* Examine the call stack

---

## Why Debugging Matters

When exceptions occur, debugging helps you understand:

* Where the error occurred
* What values were involved
* Which methods led to the problem

---

## Example Program to Debug

```java
public class AlertProcessor {

    public static void main(String[] args) {

        int[] alerts = {2, 4, 6, 8};

        for (int i = 0; i <= alerts.length; i++) {
            System.out.println("Processing alert level: " + alerts[i]);
        }
    }
}
```

This program throws an exception.

```
ArrayIndexOutOfBoundsException
```

---

## Debugging Steps

1. Set a breakpoint on the `for` loop
2. Run in **Debug Mode**
3. Step through each iteration
4. Watch `i` change
5. Observe when `i` becomes invalid

The bug is here:

```java
i <= alerts.length
```

Correct version:

```java
i < alerts.length
```

---

# Part 3 — Exception Handling Patterns

## Reading
Read the Exception Patterns Wiki:

[https://wiki.c2.com/?ExceptionPatterns](https://wiki.c2.com/?ExceptionPatterns)

Below are several patterns you should understand.

---

## Pattern 1 — Fail Fast

Detect errors immediately and stop execution.

Example:

```java
public class UserValidator {

    public static void validateUsername(String username) {

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }
}
```

Benefits:

* Problems appear early
* Easier debugging

---

## Pattern 2 — Exception Translation

Convert low-level exceptions into **domain-specific exceptions**.

Example:

```java
public class AlertRepository {

    public void saveAlert(String message) throws AlertStorageException {

        try {
            throw new RuntimeException("Database connection failed");

        } catch (RuntimeException ex) {
            throw new AlertStorageException("Failed to save alert", ex);
        }
    }
}
```

Custom exception:

```java
public class AlertStorageException extends Exception {

    public AlertStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

Benefit:

Application layers remain **clean and meaningful**.

---

## Pattern 3 — Retry Pattern

Useful for **temporary failures** like network calls.

Example:

```java
public class NetworkService {

    public void connect() throws Exception {

        int attempts = 0;

        while (attempts < 3) {
            try {
                attempts++;

                if (attempts < 3) {
                    throw new Exception("Connection failed");
                }

                System.out.println("Connected successfully");
                return;

            } catch (Exception e) {
                System.out.println("Retrying...");
            }
        }

        throw new Exception("Connection failed after retries");
    }
}
```

---

## Pattern 4 — Logging and Rethrowing

Log the error but still propagate it.

```java
public class AlertService {

    public void processAlert() throws Exception {

        try {
            throw new Exception("Processing error");

        } catch (Exception e) {

            System.err.println("ERROR: " + e.getMessage());

            throw e;
        }
    }
}
```

---

# Part 4 — Design Patterns for Error Handling

Some **design patterns improve how errors are propagated and handled** in large systems.

---

# Observer Pattern

## Reading
[https://refactoring.guru/design-patterns/observer](https://refactoring.guru/design-patterns/observer)

The **Observer pattern** allows objects to react when events occur.

This is useful for:

* Logging systems
* Monitoring systems
* Error notification systems

---

## Example: Alert Monitoring System

### Observer Interface

```java
public interface AlertObserver {
    void onAlert(String message);
}
```

---

### Concrete Observer

```java
public class EmailAlertObserver implements AlertObserver {

    @Override
    public void onAlert(String message) {
        System.out.println("Sending email alert: " + message);
    }
}
```

---

### Subject

```java
import java.util.ArrayList;
import java.util.List;

public class AlertSystem {

    private List<AlertObserver> observers = new ArrayList<>();

    public void addObserver(AlertObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (AlertObserver o : observers) {
            o.onAlert(message);
        }
    }
}
```

---

### Main Program

```java
public class App {

    public static void main(String[] args) {

        AlertSystem system = new AlertSystem();

        system.addObserver(new EmailAlertObserver());

        system.notifyObservers("Server CPU exceeded threshold");
    }
}
```

---

# Decorator Pattern

## Reading
[https://refactoring.guru/design-patterns/decorator](https://refactoring.guru/design-patterns/decorator)

The **Decorator pattern** allows behavior to be added dynamically.

This is often used for:

* Logging
* Validation
* Retry logic
* Monitoring

---

## Example: Alert Processing Decorator

### Interface

```java
public interface AlertHandler {
    void handle(String alert);
}
```

---

### Base Implementation

```java
public class BasicAlertHandler implements AlertHandler {

    public void handle(String alert) {
        System.out.println("Handling alert: " + alert);
    }
}
```

---

### Logging Decorator

```java
public class LoggingAlertDecorator implements AlertHandler {

    private AlertHandler handler;

    public LoggingAlertDecorator(AlertHandler handler) {
        this.handler = handler;
    }

    public void handle(String alert) {

        System.out.println("LOG: Alert received");

        handler.handle(alert);

        System.out.println("LOG: Alert processed");
    }
}
```

---

### Usage

```java
public class App {

    public static void main(String[] args) {

        AlertHandler handler =
                new LoggingAlertDecorator(
                        new BasicAlertHandler());

        handler.handle("Unauthorized login detected");
    }
}
```

Output:

```
LOG: Alert received
Handling alert: Unauthorized login detected
LOG: Alert processed
```

---

# Putting It All Together

Robust Java systems combine:

| Technique          | Purpose                             |
| ------------------ | ----------------------------------- |
| Exceptions         | Represent errors                    |
| Debugging          | Diagnose issues                     |
| Exception patterns | Structure error handling            |
| Design patterns    | Scale error handling across systems |

Professional systems often include:

* Logging frameworks
* Retry systems
* Monitoring systems
* Event notifications

---

# Reflection Questions

Answer the following:

1. What is the difference between checked and unchecked exceptions?
2. When should you create a custom exception?
3. Why is exception translation useful in layered systems?
4. How can the Observer pattern improve error reporting?


1. Checked Exceptions are erros that must be handled or declared at compile time. Unchecked exceptions occur at runtime and do not haveto be handled explicitly.

2.You should create a custom exception when you need to represent a specific type of error that is meaningful to your application.

3.Exception translation is useful because it allows you to convert low-level exceptions into higher-level, more meaningful ones.

4.The Observer pattern improves error reporting by allowing multiple components to be notified when an error occurs.