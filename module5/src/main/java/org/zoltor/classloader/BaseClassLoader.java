package org.zoltor.classloader;

import org.zoltor.semaphore.Semaphore;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by zoltor on 07/08/16.
 */
public abstract class BaseClassLoader extends ClassLoader {

    // Default method in Semaphore impl class which will be invoked
    private static final String LEVER_METHOD_NAME = "lever";

    // Path to the package with the Semaphore classes
    private static final String SEMAPHORE_RESOURCE_PATH = "org/zoltor/semaphore/impl";

    public BaseClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Invoke {@link #LEVER_METHOD_NAME} method
     *
     * @param semaphoreClass Given class, which should implements {@link Semaphore} interface
     * @param <T> Class which implements {@link Semaphore} interface
     */
    public <T extends Semaphore> void invokeLever(Class<T> semaphoreClass) {
        try {
            if (!Arrays.asList(semaphoreClass.getInterfaces()).contains(Semaphore.class)) {
                System.out.println(semaphoreClass + " should implements Semaphore interface.");
                return;
            }
            System.out.println("Trying to invoke " + LEVER_METHOD_NAME + " method in " + semaphoreClass);
            Object semaphoreObject = semaphoreClass.newInstance();
            Method leverMethod = semaphoreClass.getMethod(LEVER_METHOD_NAME);
            leverMethod.invoke(semaphoreObject);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get necessary class as byte array of the class file
     *
     * @param className Class name with the a package
     * @return Byte array of a class file
     */
    byte[] getClassAsBytes(String className) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/") + ".class");
            byte[] buffer = new byte[inputStream.available()];
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(buffer);
            dataInputStream.close();
            inputStream.close();
            return buffer;
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Find the all {@link Semaphore} implementations in the {@link #SEMAPHORE_RESOURCE_PATH}
     *
     * @return List with the class names of {@link Semaphore} implementations
     */
    public List<String> getSemaphoreClasses() {
        List<String> results = new ArrayList<>();
        try {
            Enumeration<URL> semaphoreUrls = getResources(SEMAPHORE_RESOURCE_PATH);
            File semaphoreClassesDir = (semaphoreUrls.hasMoreElements()) ?
                    new File(semaphoreUrls.nextElement().toURI()) :
                    null;
            File[] semaphoreClassFiles = semaphoreClassesDir.listFiles();
            for (File semaphoreClassFile : semaphoreClassFiles) {
                results.add(SEMAPHORE_RESOURCE_PATH.replace("/", ".") + "." + semaphoreClassFile.getName().replace(".class", ""));
            }
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.out.println("Unable to get the list of semaphore classes in the path: " + SEMAPHORE_RESOURCE_PATH);
        }
        return results;
    }

}
