package org.zoltor.test;

import org.junit.Test;
import org.zoltor.builder.SqlQueryBuilder;
import org.zoltor.factory.SqlQueryBuilderFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by zoltor on 27/07/16.
 */
public class SqlQueryBuilderFactoryTest {

    private final String EXPECTED_MYSQL_QUERY = "SELECT SUM(O.TotalPrice), C.FirstName, C.LastName, CI.Name FROM Order O\n" +
            "JOIN Customer C ON O.CustomerId = C.Id\n" +
            "LEFT JOIN City CI ON C.CityId = CI.Id\n" +
            "GROUP BY C.FirstName, C.LastName, CI.Name\n" +
            "ORDER BY C.LastName ASC\n" +
            "LIMIT 5";

    private final String EXPECTED_ORACLE_QUERY = "SELECT SUM(O.TotalPrice), C.FirstName, C.LastName, CI.Name FROM Order O\n" +
            "JOIN Customer C ON O.CustomerId = C.Id\n" +
            "LEFT JOIN City CI ON C.CityId = CI.Id\n" +
            "WHERE ROWNUM <= 5\n" +
            "GROUP BY C.FirstName, C.LastName, CI.Name\n" +
            "ORDER BY C.LastName ASC";

    private final String EXPECTED_MSSQL_QUERY = "SELECT TOP 5 SUM(O.TotalPrice), C.FirstName, C.LastName, CI.Name FROM Order O\n" +
            "JOIN Customer C ON O.CustomerId = C.Id\n" +
            "LEFT JOIN City CI ON C.CityId = CI.Id\n" +
            "GROUP BY C.FirstName, C.LastName, CI.Name\n" +
            "ORDER BY C.LastName ASC";

    @Test
    public void testMySqlQueryBuilder() {
        String actualMySqlQuery = getQuery(SqlQueryBuilderFactory.DB.MYSQL);
        assertEquals(EXPECTED_MYSQL_QUERY, actualMySqlQuery);
    }

    @Test
    public void testOraleSqlQueryBuilder() {
        String actualOracleQuery = getQuery(SqlQueryBuilderFactory.DB.ORACLE);
        assertEquals(EXPECTED_ORACLE_QUERY, actualOracleQuery);
    }
    @Test
    public void testMsSqlQueryBuilder() {
        String actualMsSqlQuery = getQuery(SqlQueryBuilderFactory.DB.MSSQL);
        assertEquals(EXPECTED_MSSQL_QUERY, actualMsSqlQuery);
    }

    private String getQuery(SqlQueryBuilderFactory.DB db) {
        SqlQueryBuilder builder = SqlQueryBuilderFactory.createSqlQueryBuilder(db);
        return builder.from("Order")
                .join(SqlQueryBuilder.JOINS.INNER_JOIN, "Order", "CustomerId", "Customer", "Id")
                .join(SqlQueryBuilder.JOINS.LEFT_JOIN, "Customer", "CityId", "City", "Id")
                .output("Order", "TotalPrice", SqlQueryBuilder.AGGREGATE.SUM)
                .output("Customer", "FirstName")
                .output("Customer", "LastName")
                .output("City", "Name")
                .groupBy("Customer", "FirstName")
                .groupBy("Customer", "LastName")
                .groupBy("City", "Name")
                .orderBy("Customer", "LastName")
                .limit(5).build();
    }

}
