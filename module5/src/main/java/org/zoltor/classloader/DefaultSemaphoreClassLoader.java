package org.zoltor.classloader;

import org.zoltor.semaphore.Semaphore;

/**
 * Created by zoltor on 07/08/16.
 */
public class DefaultSemaphoreClassLoader extends BaseClassLoader {

    public DefaultSemaphoreClassLoader() {
        super(DefaultSemaphoreClassLoader.class.getClassLoader());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Semaphore> findClass(String name) throws ClassNotFoundException {
        System.out.println("Searching for '" + name + "' class is in progress...");
        byte[] classAsBytes = getClassAsBytes(name);
        if (classAsBytes == null) {
            System.out.println("Error. Class '" + name + "' not found.");
            throw new ClassNotFoundException("Class not found: " + name);
        }
        System.out.println("Success. Class '" + name + "' found.");
        return (Class<? extends Semaphore>)defineClass(name, classAsBytes, 0, classAsBytes.length);
    }


}
