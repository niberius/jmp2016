package org.zoltor.communicator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zoltor on 09/07/16.
 * This annotation uses for mapping classes fields to the JSON data
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonItem {

    /**
     * Name of JSON item
     */
    String value();
}
