/*
Run main method with the following JVM options;
-Xms15m -Xmx15m -Xmn9m -XX:SurvivorRatio=1 -XX:ThreadStackSize=1m

Exception in thread "main" java.lang.StackOverflowError
	at org.zoltor.error.StackOverflowError$ClassA.<init>(StackOverflowError.java:8)
	at org.zoltor.error.StackOverflowError$ClassB.<init>(StackOverflowError.java:16)
	at org.zoltor.error.StackOverflowError$ClassB.<init>(StackOverflowError.java:14)
	at org.zoltor.error.StackOverflowError$ClassA.<init>(StackOverflowError.java:10)
	at org.zoltor.error.StackOverflowError$ClassA.<init>(StackOverflowError.java:8)
	at org.zoltor.error.StackOverflowError$ClassB.<init>(StackOverflowError.java:16)
	at org.zoltor.error.StackOverflowError$ClassB.<init>(StackOverflowError.java:14)
	at org.zoltor.error.StackOverflowError$ClassA.<init>(StackOverflowError.java:10)
	at org.zoltor.error.StackOverflowError$ClassA.<init>(StackOverflowError.java:8)
	at org.zoltor.error.StackOverflowError$ClassB.<init>(StackOverflowError.java:16)
	at org.zoltor.error.StackOverflowError$ClassB.<init>(StackOverflowError.java:14)
	at org.zoltor.error.StackOverflowError$ClassA.<init>(StackOverflowError.java:10)
	at org.zoltor.error.StackOverflowError$ClassA.<init>(StackOverflowError.java:8)
	and so on...
 */
// TODO How to do it without any recursion?
package org.zoltor.error;

/**
 * Created by zoltor on 02/08/16.
 */
public class StackOverflowError {

    private static class ClassA {

        private ClassB instanceB = new ClassB();

    }

    private static class ClassB {

        private ClassA instanceA = new ClassA();

    }

    public static void main(String[] args) {
        new ClassA();
    }

}
