package org.zoltor.communicator.map;

/**
 * Created by zoltor on 09/07/16.
 */
public interface JsonProcessor {

    /**
     * Get JSON string from the objects (recursively)
     *
     * @param annotatedRequestObject Object with {@link org.zoltor.communicator.annotation.JsonItem} annotations for
     *                               fields, which contains data for JSON
     * @param <R> Type of the object with JSON data
     * @return String representation of JSON
     */
    <R> String getJson(R annotatedRequestObject);

    /**
     * Convert JSON string to the object
     * WARNING: responseClass should have a public constructor without arguments
     * WARNING 2: responseClass should have an appropriate mapping for the JSON data, using
     * {@link org.zoltor.communicator.annotation.JsonItem} annotations
     *
     * @param jsonString JSON string
     * @param responseClass Class type for the new object, which will be return with JSON data representation
     * @param <T> Type of object, which should be returned
     * @return Object with JSNO data in fields
     */
    <T> T getObjectFromJson(String jsonString, Class<T> responseClass);

}
