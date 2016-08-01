package org.zoltor.common;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zoltor on 01/08/2016.
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classAsBytes = getClassAsBytes(name);
        if (classAsBytes == null) {
            throw new ClassNotFoundException("Class not found: " + name);
        }
        return defineClass(name, classAsBytes, 0, classAsBytes.length);
    }

    private byte[] getClassAsBytes(String className) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/") + ".class");
            byte[] buffer = new byte[inputStream.available()];
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(buffer);
            dataInputStream.close();
            inputStream.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
