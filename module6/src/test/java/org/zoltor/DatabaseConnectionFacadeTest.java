package org.zoltor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zoltor.facade.DatabaseConnectionFacade;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by zoltor on 8/15/16.
 */
public class DatabaseConnectionFacadeTest {

    private DatabaseConnectionFacade facade = new DatabaseConnectionFacade();
    private final String expectedResultSelect = "Success";
    private PrintStream systemOut;
    private ByteArrayOutputStream dbOut;

    @Before
    public void beforeTest() {
        dbOut = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(dbOut);
        systemOut = System.out;
        System.setOut(ps);
    }

    @After
    public void afterTest() throws IOException {
        System.setOut(systemOut);
        System.out.println(dbOut);
        dbOut.close();
    }

    @Test
    public void testConnectAndExecuteMySqlSelect() {
        String result = facade.connectAndExecuteMySql("SELECT * FROM dual");
        String dbOutActual = dbOut.toString();
        assertEquals(expectedResultSelect, result);
        assertEquals("org.zoltor.database.impl.MySqlConnection: Connected!\n" +
                "org.zoltor.database.impl.MySqlConnection: Select query were successfully executed\n" +
                "org.zoltor.database.impl.MySqlConnection: Transaction was successfully committed\n" +
                "org.zoltor.database.impl.MySqlConnection: Connection will be disconnect\n", dbOutActual);
    }

    @Test
    public void testConnectAndExecuteMySqlNonSelect() {
        String result = facade.connectAndExecuteMySql("DELETE * FROM dual");
        String dbOutActual = dbOut.toString();
        assertEquals("", result);
        assertEquals("org.zoltor.database.impl.MySqlConnection: Connected!\n" +
                "org.zoltor.database.impl.MySqlConnection: Changing data statement detected. Nothing to return\n" +
                "org.zoltor.database.impl.MySqlConnection: Transaction was successfully committed\n" +
                "org.zoltor.database.impl.MySqlConnection: Connection will be disconnect\n", dbOutActual);
    }

    @Test
    public void testConnectAndExecuteMsSqlSelect() {
        String result = facade.connectAndExecuteMsSql("SELECT * FROM dual");
        String dbOutActual = dbOut.toString();
        assertEquals(expectedResultSelect, result);
        assertEquals("org.zoltor.database.impl.MsSqlConnection: Connected!\n" +
                "org.zoltor.database.impl.MsSqlConnection: Select query were successfully executed\n" +
                "org.zoltor.database.impl.MsSqlConnection: Transaction was successfully committed\n" +
                "org.zoltor.database.impl.MsSqlConnection: Connection will be disconnect\n", dbOutActual);
    }

    @Test
    public void testConnectAndExecuteMsSqlNonSelect() {
        String result = facade.connectAndExecuteMsSql("DELETE * FROM dual");
        String dbOutActual = dbOut.toString();
        assertEquals("", result);
        assertEquals("org.zoltor.database.impl.MsSqlConnection: Connected!\n" +
                "org.zoltor.database.impl.MsSqlConnection: Changing data statement detected. Nothing to return\n" +
                "org.zoltor.database.impl.MsSqlConnection: Transaction was successfully committed\n" +
                "org.zoltor.database.impl.MsSqlConnection: Connection will be disconnect\n", dbOutActual);
    }

    @Test
    public void testConnectAndExecuteOracleSelect() {
        String result = facade.connectAndExecuteOracle("SELECT * FROM dual");
        String dbOutActual = dbOut.toString();
        assertEquals(expectedResultSelect, result);
        assertEquals("org.zoltor.database.impl.OracleConnection: Connected!\n" +
                "org.zoltor.database.impl.OracleConnection: Select query were successfully executed\n" +
                "org.zoltor.database.impl.OracleConnection: Transaction was successfully committed\n" +
                "org.zoltor.database.impl.OracleConnection: Connection will be disconnect\n", dbOutActual);
    }

    @Test
    public void testConnectAndExecuteOracleNonSelect() {
        String result = facade.connectAndExecuteOracle("DELETE * FROM dual");
        String dbOutActual = dbOut.toString();
        assertEquals("", result);
        assertEquals("org.zoltor.database.impl.OracleConnection: Connected!\n" +
                "org.zoltor.database.impl.OracleConnection: Changing data statement detected. Nothing to return\n" +
                "org.zoltor.database.impl.OracleConnection: Transaction was successfully committed\n" +
                "org.zoltor.database.impl.OracleConnection: Connection will be disconnect\n", dbOutActual);
    }
}
