package org.zoltor.communicator;

import org.junit.Ignore;
import org.junit.Test;
import org.zoltor.communicator.annotation.JsonItem;
import org.zoltor.communicator.map.JsonProcessor;
import org.zoltor.communicator.map.impl.JsonProcessorImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by zoltor on 09/07/16.
 */
public class JsonProcessorTest {

    private JsonProcessor jsonProcessor = new JsonProcessorImpl();
    private static final String EXPECTED_JSON_REQUEST = "{\"stringVar\":\"Hello \\\"test\\\"\",\"StringList\":[\"Bla1\",\"Bla2\"],\"Dummies\":[{\"dummyVal1\":\"xyz\",\"dummyVal2\":false},{\"dummyVal1\":\"xyz\",\"dummyVal2\":false}],\"intVar\":2}";
    private static final String EXPECTED_JSON_RESPONSE = "{\"stringVar\":\"Oh \\\"NO!\\\"\",\"StringList\":[\"AAA\",\"BBB\"],\"Dummies\":[{\"dummyVal1\":\"dummy1\",\"dummyVal2\":true},{\"dummyVal1\":\"dummy2\",\"dummyVal2\":true}],\"intVar\":13}";

    public static class SimpleRequest {
        @JsonItem("stringVar")
        private String stringVar = "Hello \"test\"";
        @JsonItem("intVar")
        private int intVar = 2;
        @JsonItem("Dummies")
        private List<Dummy> dummies = new ArrayList<>();
        @JsonItem("StringList")
        private List<String> strings = new ArrayList<>();

        public SimpleRequest() {
            dummies.add(new Dummy());
            dummies.add(new Dummy());
            strings.add("Bla1");
            strings.add("Bla2");
        }

        public String getStringVar() {
            return stringVar;
        }

        public void setStringVar(String stringVar) {
            this.stringVar = stringVar;
        }

        public int getIntVar() {
            return intVar;
        }

        public void setIntVar(int intVar) {
            this.intVar = intVar;
        }

        public List<Dummy> getDummies() {
            return dummies;
        }

        public void setDummies(List<Dummy> dummies) {
            this.dummies = dummies;
        }

        public List<String> getStrings() {
            return strings;
        }

        public void setStrings(List<String> strings) {
            this.strings = strings;
        }
    }

    public static class Dummy {
        @JsonItem("dummyVal1")
        private String dummyVal1 = "xyz";
        @JsonItem("dummyVal2")
        private boolean dummyVal2 = false;

        public Dummy() {
        }

        public String getDummyVal1() {
            return dummyVal1;
        }

        public void setDummyVal1(String dummyVal1) {
            this.dummyVal1 = dummyVal1;
        }

        public boolean isDummyVal2() {
            return dummyVal2;
        }

        public void setDummyVal2(boolean dummyVal2) {
            this.dummyVal2 = dummyVal2;
        }
    }

    @Test
    @Ignore
    public void testGetJson() {
        String json = jsonProcessor.getJson(new SimpleRequest());
        assertEquals(EXPECTED_JSON_REQUEST, json);
    }

    @Test
    public void testGetObjectsFromJson() {
        SimpleRequest simpleRequest = jsonProcessor.getObjectFromJson(EXPECTED_JSON_RESPONSE, SimpleRequest.class);
        assertEquals("Oh \"NO!\"", simpleRequest.getStringVar());
        assertEquals(13, simpleRequest.getIntVar());
        assertEquals("[AAA, BBB]", simpleRequest.getStrings().toString());
        assertEquals("dummy1", simpleRequest.getDummies().get(0).getDummyVal1());
        assertEquals(true, simpleRequest.getDummies().get(0).isDummyVal2());
        assertEquals("dummy2", simpleRequest.getDummies().get(1).getDummyVal1());
        assertEquals(true, simpleRequest.getDummies().get(1).isDummyVal2());
    }

}
