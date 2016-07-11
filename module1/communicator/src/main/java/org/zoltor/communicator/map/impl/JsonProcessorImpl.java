package org.zoltor.communicator.map.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zoltor.communicator.annotation.JsonItem;
import org.zoltor.communicator.map.JsonProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.zoltor.util.ReflectionUtil.*;

/**
 * Created by zoltor on 09/07/16.
 */
public class JsonProcessorImpl implements JsonProcessor {

    @Override
    public <R> String getJson(R annotatedRequestObject) {
        return getJsonObject(annotatedRequestObject).toString();
    }

    @Override
    public <R> String getJsonFromObjects(List<R> annotatedRequestObjects) {
        JSONArray jsonArray = new JSONArray();
        for (R annotatedRequestObject : annotatedRequestObjects) {
            jsonArray.put(getJsonObject(annotatedRequestObject));
        }
        return jsonArray.toString();
    }

    @Override
    public <T> List<T> getObjectsFromJson(String jsonString, Class<T> responseClass) {
        List<T> result = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonString);
        for (Object object : jsonArray) {
            result.add(getObjectFromJson(object.toString(), responseClass));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private <R, T> T getJsonObject(R annotatedRequestObject) {
        Object simpleObjectValue = getPrimitiveValue(annotatedRequestObject);
        if (simpleObjectValue != null) {
            return (T) simpleObjectValue;
        }
        JSONObject result = new JSONObject();
        Map<Field, JsonItem> annotatedFields = getAnnotatedFields(annotatedRequestObject);
        for (Field field : annotatedFields.keySet()) {
            Class fieldType = field.getType();
            String name = annotatedFields.get(field).value();
            Object value = getValueFromField(field, annotatedRequestObject);
            simpleObjectValue = getPrimitiveValue(value);
            if (simpleObjectValue != null) {
                result.put(name, simpleObjectValue);
            } else if (fieldType.equals(List.class)) {
                List objects = ((List) value);
                JSONArray jsonArray = new JSONArray();
                for (Object object : objects) {
                    jsonArray.put(getJsonObject(object));
                }
                result.put(name, jsonArray);
            } else {
                result.put(name, getJsonObject(value));
            }
        }
        return (T) result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObjectFromJson(String jsonString, Class<T> responseClass) {
        // Check if given string is a JSON object
        if (jsonString.startsWith("{")) {
            JSONObject jsonObject = new JSONObject(jsonString);
            T responseObject = instantiateObject(responseClass);
            Map<Field, JsonItem> annotatedFields = getAnnotatedFields(responseObject);
            for (Field field : annotatedFields.keySet()) {
                Object oneObject = jsonObject.get(annotatedFields.get(field).value());
                Object simpleValueObject = getPrimitiveValue(oneObject);
                if (simpleValueObject != null) {
                    setValueToField(field, simpleValueObject, responseObject);
                } else if (field.getType().equals(List.class)) {
                    ParameterizedType genericFieldType = (ParameterizedType) field.getGenericType();
                    Type listType = genericFieldType.getActualTypeArguments()[0];
                    List<Object> fieldList = new ArrayList<>();
                    JSONArray jsonObjects = (JSONArray) oneObject;
                    Class<?> toReturnClass = getClassForName(((Class) listType).getName());
                    // TODO Just a workaround. Refactor this
                    Object toReturnObject = instantiateObject(toReturnClass);
                    for (Object jsonObjectFromList : jsonObjects) {
                        fieldList.add(getObjectFromJson(jsonObjectFromList.toString(), toReturnObject.getClass()));
                    }
                    setValueToField(field, fieldList, responseObject);
                } else {
                    setValueToField(field, getObjectFromJson(oneObject.toString(), field.getType()), responseObject);
                }
            }
            return responseObject;
        } else {
            // Just a simple object. Return itself
            return (T) jsonString;
        }
    }

    private <R> Map<Field, JsonItem> getAnnotatedFields(R annotatedRequestObject) {
        return getFieldWithAnnotation(annotatedRequestObject, JsonItem.class);
    }

}
