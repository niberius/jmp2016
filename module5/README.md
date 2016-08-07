The main class is org.zoltor.main.DefaultSemaphoreMain

Solution for the tasks:

1. Implement Custom Classloader:


Custom classloader should be able to load compiled Semaphore.class file. Class path is passed via console on program start. Status progress/success/error should be output to console.
Semaphore object should be instantiated. Method  lever should be invoked. Use reflection. Google exmples on reflection API.
Expected to see "It works!" message on the console.
// soure code, compile it

package com.epam.mentoring.lessone;
public class Semaphore {
    public void lever() {
        System.out.println("It works!");
    }
}

2. Extend Custom Classloader:

Use custom class files from previous task. Extend classloader by adding ability to reload class at runtime. Program should ask continuously for a class path. When path is entered to console - class should be reloaded.

a. Prepare several versions of Semaphor class - change message string for output.

b. Expected: after entering class path status messages and correct message are displayed in console.