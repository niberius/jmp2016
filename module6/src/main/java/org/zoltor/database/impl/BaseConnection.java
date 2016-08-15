package org.zoltor.database.impl;

import org.zoltor.database.Connection;

/**
 * Created by zoltor on 8/15/16.
 */
public abstract class BaseConnection implements Connection{

    protected Connection connection;

    /** {@inheritDoc} */
    @Override
    public String executeStatement(String statement) {
        if (statement.toLowerCase().trim().startsWith("select") ) {
            logMessage("Select query were successfully executed");
            return "Success";
        } else {
            logMessage("Changing data statement detected. Nothing to return");
            return "";
        }
    }

    /** {@inheritDoc} */
    @Override
    public void commitTransaction() {
        logMessage("Transaction was successfully committed");
    }

    /** {@inheritDoc} */
    @Override
    public void rollbackTransaction() {
        logMessage("Transaction was successfully rolled back");
    }

    /** {@inheritDoc} */
    @Override
    public void disconnect() {
        logMessage("Connection will be disconnect");
        this.connection = null;
    }

    /** {@inheritDoc} */
    @Override
    public Connection connect() {
        logMessage("Connected!");
        if (connection == null) {
            connection = this;
        }
        return connection;
    }

    /**
     * Log the message to console with a class name
     * @param message Message to log. E.g.: pkg.name.ConnectionImplClass: message
     */
    protected void logMessage(String message) {
        System.out.println(getClass().getName() + ": " + message);
    }

}
