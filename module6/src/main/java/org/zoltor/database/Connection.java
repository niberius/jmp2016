package org.zoltor.database;

/**
 * Created by zoltor on 8/15/16.
 */
public interface Connection {

    /**
     * Create connection to database
     *
     * @return Instance with connection
     */
    Connection connect();

    /**
     * Execute SQL statement and get result (in case if statement is SELECT)
     *
     * @param statement SQL Statement
     * @return Result of select statement or an empty string if statement is UPDATE
     */
    String executeStatement(String statement);

    /**
     * Commit transaction
     */
    void commitTransaction();

    /**
     * Rollback transaction
     */
    void rollbackTransaction();

    /**
     * Close connection
     */
    void disconnect();

}
