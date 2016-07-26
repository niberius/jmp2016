package org.zoltor.factory;

import org.zoltor.builder.SqlQueryBuilder;
import org.zoltor.builder.impl.MsSqlQueryBuilder;
import org.zoltor.builder.impl.MySqlQueryBuilder;
import org.zoltor.builder.impl.OracleQueryBuilder;

/**
 * Created by zoltor on 27/07/16.
 * SQL Query factory
 */
public final class SqlQueryBuilderFactory {

    /**
     * Supported database types
     */
    public enum DB {
        MSSQL, MYSQL, ORACLE
    }

    /**
     * Prevent factory instantiation
     */
    private SqlQueryBuilderFactory() {
        // Do not instantiate
    }

    /**
     * Factory method to get sql query builder
     *
     * @param db Database type {@link DB}
     * @return Sql uery builder
     * @throws IllegalArgumentException in case when there is no sql query builder implementation for given DB type
     */
    public static SqlQueryBuilder createSqlQueryBuilder(DB db) {
        switch (db) {
            case MSSQL:
                return new MsSqlQueryBuilder();
            case MYSQL:
                return new MySqlQueryBuilder();
            case ORACLE:
                return new OracleQueryBuilder();
            default:
                throw new IllegalArgumentException("There is no sql query builder implementation for DB " + db);
        }
    }

}
