/*
Run main method with the following JVM options;
-Xms15m -Xmx15m -Xmn9m -XX:SurvivorRatio=1 -XX:MaxMetaspaceSize=10m

Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:642)
	at org.zoltor.common.CustomClassLoader.findClass(CustomClassLoader.java:18)
	at org.zoltor.error.MetaOutOfMemoryError.main(MetaOutOfMemoryError.java:25)
 */
package org.zoltor.error;

import org.zoltor.common.CustomClassLoader;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zoltor on 01/08/2016.
 */
public class MetaOutOfMemoryError {

    private static final Map<Integer, Class> classes = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        System.out.println("PID: " + ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        int i = 0;
        while (true) {
            CustomClassLoader classLoader = new CustomClassLoader();
            classes.put(i, classLoader.findClass("org.zoltor.common.StaticClass"));
            i++;
        }
    }

}
