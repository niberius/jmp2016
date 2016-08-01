/*
-XX:MetaspaceSize=1m
 */
package org.zoltor.error;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoltor on 01/08/2016.
 */
public class MetaOutOfMemoryError {

    private static List<Class<?>> classes = new ArrayList<Class<?>>();

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Class clazz = Class.forName("org.zoltor.error.StaticClass");
        while (true) {
            classes.add(clazz);
        }
        /*CustomClassLoader classLoader = new CustomClassLoader();
        List<String> classNames = classLoader.getClassNamesInPkg("java.util");
        for (String className : classNames) {
            classes.add(classLoader.findClass(className));
        }*/
    }



}
