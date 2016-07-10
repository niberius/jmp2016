package org.zoltor.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zoltor on 10/07/16.
 */
@SuppressWarnings("unchecked")
public final class ReflectionUtil {

    private static final List<Class> SIMPLE_CLASSES = new ArrayList<>();
    static {
        SIMPLE_CLASSES.add(Boolean.class);
        SIMPLE_CLASSES.add(Byte.class);
        SIMPLE_CLASSES.add(Character.class);
        SIMPLE_CLASSES.add(Double.class);
        SIMPLE_CLASSES.add(Float.class);
        SIMPLE_CLASSES.add(Integer.class);
        SIMPLE_CLASSES.add(Long.class);
        SIMPLE_CLASSES.add(Short.class);
        SIMPLE_CLASSES.add(String.class);
    }

    private ReflectionUtil() {
        // Prevent from class instantiation
    }

    /**
     * Check if the current object is a simple object (is primitive or one of {@link #SIMPLE_CLASSES}) and get it value
     *
     * @param object Given object
     * @param <T> Type of the object
     * @return Simple value of object or null - if this is a complex object
     */
    public static <T> Object getPrimitiveValue(T object) {
        Class annotatedRequestClass = object.getClass();
        if (annotatedRequestClass.isPrimitive() || SIMPLE_CLASSES.contains(annotatedRequestClass)) {
            return object;
        } else {
            return null;
        }
    }

    /**
     * Get value from an object field
     *
     * @param field Given field
     * @param object Given object
     * @param <T> Type of the object
     * @param <E> Type of value which will return
     * @return Value from the object field or new empty {@link Object} in error case
     */
    public static <T, E> E getValueFromField(Field field, T object) {
        field.setAccessible(true);
        try {
            return (E) field.get(object);
        } catch (IllegalAccessException e) {
            // TODO Logger
            e.printStackTrace();
            System.out.println("Unable to access to field " + field.getName() + ", class " + object.getClass().getName());
            return (E) new Object();
        }
    }

    /**
     * Set value for an object field
     *
     * @param field Give field
     * @param value Given value
     * @param object Object to set it field value
     */
    public static void setValueToField(Field field, Object value, Object object) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            // TODO Logger
            e.printStackTrace();
            System.out.println("Unable to access to field " + field.getName() + ", class " + object.getClass().getName());
        }
    }

    /**
     * Get class for given name
     *
     * @param name Full class name including a package name
     * @return Class if found or just a simple {@link Class}
     */
    public static Class getClassForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            // TODO Logger
            e.printStackTrace();
            return Class.class;
        }
    }

    /**
     * Get fields annotated with annotation.
     *
     * @param object Given object with fields
     * @param expectedAnnotation Annotation, which should be placed for fields
     * @param <T> Object type
     * @param <E> Annotations type
     * @return Map with fields and their annotations
     */
    public static <T, E extends Annotation> Map<Field, E> getFieldWithAnnotation(T object, Class<E> expectedAnnotation) {
        Map<Field, E> result = new LinkedHashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(expectedAnnotation);
            if (annotation != null) {
                result.put(field, (E)annotation);
            }
        }
        return result;
    }

    /**
     * Create new object from class
     *
     * @param clazz Class
     * @param <T> Type of created object
     * @return New instance of object with given Class type
     * @throws IllegalArgumentException In case when object was not created
     */
    public static <T> T instantiateObject(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Logger
            e.printStackTrace();
            // There is no sense to continue without object. Terminate operation
            throw new IllegalArgumentException("Unable to create object for class " + clazz.getName() +
                    ". Please, make sure that given class has a public constructor without arguments.");
        }
    }

}
