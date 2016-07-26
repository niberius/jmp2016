This project is an implementation for the following task:

During this task you will implement:


1. SqlQueryBuilder that allows to compose SQL-queries

2. Factory class that will create SqlQueryBuilder instance based on DB kind provided: "mysql", "mssql", "oracle". DB kind defines how "limit" method will be implemented. E.g. MySql uses "LIMIT", "MsSQL" uses "TOP", "Oracle" uses "RowNum"

Implement SQLQueryBuilder class that allow user to construct next SQL-query:


SELECT SUM(O.TotalPrice), C.FirstName, C.LastName, CI.Name

FROM Order O 

       JOIN Customer C ON O.CustomerId = C.Id

       LEFT JOIN City CI ON C.CityId = CI.Name

GROUP BY C.FirstName, C.LastName, CI.Name

ORDER BY C.LastName ASC

LIMIT 5 // show only 5 records, it may vary based on DB kind

 

with SqlQueryBuilder usage:

SqlQueryBuilder builder = SQLQueryBuilderFactory.createSqlQueryBuilder(SQLQueryBuilderFactory.DB.MYSQL);


String sqlQuery = builder.from("Order")
        .join(SqlQueryBuilder.JOINS.INNER_JOIN , "Order", "CustomerId", "Customer", "Id")
        .join(SqlQueryBuilder.JOINS.LEFT_JOIN , "Customer", "CityId", "City", "Id")
        .output("Order", "TotalPrice", SqlQueryBuilder.Aggregate.SUM) // indicates that "sum" function must be applied
        .output("Client", "FirstName")
        .output("Client", "LastName")
        .output("City", "Name")
        .groupBy("Client", "FirstName")
        .groupBy("Client", "LastName")
        .groupBy("City", "Name")
        .orderBy("Client", "LastName")
        .limit(5).build();