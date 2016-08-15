package org.zoltor.facade;

import org.zoltor.database.Connection;
import org.zoltor.database.impl.MsSqlConnection;
import org.zoltor.database.impl.MySqlConnection;
import org.zoltor.database.impl.OracleConnection;

/**
 * Created by zoltor on 8/15/16.
 */
public class DatabaseConnectionFacade {

    private Connection mySqlConnection;
    private Connection oracleConnection;
    private Connection msSqlConnection;

    public DatabaseConnectionFacade() {
        msSqlConnection = new MsSqlConnection();
        mySqlConnection = new MySqlConnection();
        oracleConnection = new OracleConnection();
    }

    /**
     * Connect to MySQL database and get connection instance
     *
     * @return Connection instance
     * @see Connection
     */
    public Connection connectMySql() {
        return mySqlConnection.connect();
    }

    /**
     * Connect to MSSQL database and get connection instance
     *
     * @return Conection instance
     * @see Connection
     */
    public Connection connectMsSql() {
        return msSqlConnection.connect();
    }

    /**
     * Connect to Oracle database and get connection instance
     *
     * @return Conection instance
     * @see Connection
     */
    public Connection connectOracle() {
        return oracleConnection.connect();
    }

    /**
     * Execute statement in MySQL database and get the result (if SQL statement is SELECT...
     * Otherwise, empty string will return)
     *
     * @param statement SQL Statement
     * @return Result for SELECT statement or empty String for another statement
     */
    public String execStatementMySql(String statement) {
        return mySqlConnection.executeStatement(statement);
    }

    /**
     * Execute statement in MSSQL database and get the result (if SQL statement is SELECT...
     * Otherwise, empty string will return)
     *
     * @param statement SQL Statement
     * @return Result for SELECT statement or empty String for another statement
     */
    public String execStatementMsSql(String statement) {
        return msSqlConnection.executeStatement(statement);
    }

    /**
     * Execute statement in Oracle database and get the result (if SQL statement is SELECT...
     * Otherwise, empty string will return)
     *
     * @param statement SQL Statement
     * @return Result for SELECT statement or empty String for another statement
     */
    public String execStatementOracle(String statement) {
        return oracleConnection.executeStatement(statement);
    }

    /**
     * Commit transaction to MySQL database
     */
    public void commitTransactionMySql() {
        mySqlConnection.commitTransaction();
    }

    /**
     * Commit transaction to MSSQL database
     */
    public void commitTransactionMsSql() {
        msSqlConnection.commitTransaction();
    }

    /**
     * Commit transaction to Oracle database
     */
    public void commitTransactionOracle() {
        oracleConnection.commitTransaction();
    }

    /**
     * Rollback transaction in MySQL database
     */
    public void rollbackTransactionMySql() {
        mySqlConnection.rollbackTransaction();
    }

    /**
     * Rollback transaction in MSSQL database
     */
    public void rollbackTransactionMsSql() {
        msSqlConnection.rollbackTransaction();
    }

    /**
     * Rollback transaction in Oracle database
     */
    public void rollbackTransactionOracle() {
        oracleConnection.rollbackTransaction();
    }

    /**
     * Close connection to MySQL database
     */
    public void disconnectMySql() {
        mySqlConnection. disconnect();
    }

    /**
     * Close connection to MSSQL database
     */
    public void disconnectMsSql() {
        msSqlConnection. disconnect();
    }

    /**
     * Close connection to Oracle database
     */
    public void disconnectOracle() {
        oracleConnection. disconnect();
    }

    /**
     * Execute single statement in MySQL database.
     * Recommended to use this method if there is not necessary to communicate with the database so often
     *
     * @param statement SQL Statement
     * @return Result for SELECT statement or empty String for another statement
     */
    public String connectAndExecuteMySql(String statement) {
        mySqlConnection.connect();
        String result = mySqlConnection.executeStatement(statement);
        mySqlConnection.commitTransaction();
        mySqlConnection.disconnect();
        return result;
    }

    /**
     * Execute single statement in MSSQL database.
     * Recommended to use this method if there is not necessary to communicate with the database so often
     *
     * @param statement SQL Statement
     * @return Result for SELECT statement or empty String for another statement
     */
    public String connectAndExecuteMsSql(String statement) {
        msSqlConnection.connect();
        String result = msSqlConnection.executeStatement(statement);
        msSqlConnection.commitTransaction();
        msSqlConnection.disconnect();
        return result;
    }

    /**
     * Execute single statement in Oracle database.
     * Recommended to use this method if there is not necessary to communicate with the database so often
     *
     * @param statement SQL Statement
     * @return Result for SELECT statement or empty String for another statement
     */
    public String connectAndExecuteOracle(String statement) {
        oracleConnection.connect();
        String result = oracleConnection.executeStatement(statement);
        oracleConnection.commitTransaction();
        oracleConnection.disconnect();
        return result;
    }
}
