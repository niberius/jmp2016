package org.zoltor.main;

import org.zoltor.classloader.DefaultSemaphoreClassLoader;
import org.zoltor.semaphore.Semaphore;

import java.util.List;
import java.util.Scanner;

/**
 * Created by zoltor on 07/08/16.
 */
public class DefaultSemaphoreMain {

    private static final String AUTO_INPUT = "auto";
    private static final String DEFAULT_SEMAPHORE_CLASS_NAME = "org.zoltor.semaphore.impl.DefaultSemaphore";

    public static void main(String[] args) {
        System.out.println("Default Semaphore Class Loader App");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                DefaultSemaphoreClassLoader defaultSemaphoreClassLoader = new DefaultSemaphoreClassLoader();
                List<String> semaphoreClasses = defaultSemaphoreClassLoader.getSemaphoreClasses();
                System.out.println("Please, specify a class name (with a package name) to load (e.g., " +
                        "'" + DEFAULT_SEMAPHORE_CLASS_NAME + "'. Available classes: '" +
                        semaphoreClasses.toString().replace("[", "").replace("]", "")+ "') or type 'auto' to auto " +
                        "load the default Semaphore implementation and lever() method invocation:");
                String className = scanner.next();
                Class<? extends Semaphore> defaultSemaphoreClass = (className.equalsIgnoreCase(AUTO_INPUT)) ?
                        defaultSemaphoreClassLoader.findClass(DEFAULT_SEMAPHORE_CLASS_NAME) :
                        defaultSemaphoreClassLoader.findClass(className);
                defaultSemaphoreClassLoader.invokeLever(defaultSemaphoreClass);
            } catch (ClassNotFoundException e) {
                System.out.println("ERROR");
            }
        }
    }

}
