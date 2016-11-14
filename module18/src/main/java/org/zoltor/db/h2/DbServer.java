package org.zoltor.db.h2;

import org.h2.tools.Server;

import java.io.File;
import java.sql.*;

/**
 * Created by Pavel Ordenko on 13/11/2016, 11:44.
 */
public class DbServer {

    public static final String DB_USERNAME = "sa";
    public static final String DB_PASSWORD = "";
    private static final String DB_STORAGE_PATH = System.getProperty("java.io.tmpdir");
    public static final String DB_URL = "jdbc:h2:" + DB_STORAGE_PATH + "/data";
    private static final File DATA_MV_FILE = new File(DB_STORAGE_PATH + "/data.mv.db");
    private static final File DATA_TRACE_FILE = new File(DB_STORAGE_PATH + "/data.trace.db");
    private static final File DATA_H2_FILE = new File(DB_STORAGE_PATH + "/data.h2.db");
    private static Server server;
    private static Connection connection;

    public DbServer() {
        // Delete database files from a previous run
        removeDataFiles();

        // Start H2 DB Server
        startServer();

        // Connect to Database
        connectOrReconnect();
    }

    public ResultSet select(String sqlScript, Object... objects) {
        try {
            return getPrepareStatement(sqlScript, objects).executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to execute query", e);
        }
    }

    public boolean update(String sqlScript, Object... objects) {
        try {
            return getPrepareStatement(sqlScript, objects).executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to execute query", e);
        }
    }

    public void shutdown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        server.shutdown();
    }

    private PreparedStatement getPrepareStatement(String sqlScript, Object... objects) {
        try {
            connectOrReconnect();
            PreparedStatement statement = connection.prepareStatement(sqlScript);
            int parameterIndex = 1;
            for (Object object : objects) {
                statement.setObject(parameterIndex, object);
                parameterIndex++;
            }
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException("Error during statement building", e);
        }
    }

    private void connectOrReconnect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void startServer() {
        try {
            if (server == null || !server.isRunning(true)) {
                server = Server.createTcpServer("-baseDir", DB_STORAGE_PATH).start();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not start an H2 Database server", e);
        }
    }

    private void removeDataFiles() {
        if (DATA_MV_FILE.exists()) {
            DATA_MV_FILE.delete();
        }
        if (DATA_TRACE_FILE.exists()) {
            DATA_TRACE_FILE.delete();
        }
        if (DATA_H2_FILE.exists()) {
            DATA_H2_FILE.delete();
        }
    }

}
