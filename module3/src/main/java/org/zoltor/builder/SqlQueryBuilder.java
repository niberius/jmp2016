package org.zoltor.builder;

/**
 * Created by zoltor on 26/07/16.
 * Interface for sql query builder
 */
public interface SqlQueryBuilder {

    /**
     * Enumeration with the types of joins
     */
    enum JOINS {
        INNER_JOIN("JOIN"),
        LEFT_JOIN("LEFT JOIN"),
        RIGHT_JOIN("RIGHT JOIN"),
        FULL_JOIN("FULL JOIN");

        // Part of the beginning for JOIN sql query
        private String sqlSyntax;

        JOINS(String sqlSyntax) {
            this.sqlSyntax = sqlSyntax;
        }

        public String getSqlSyntax() {
            return sqlSyntax;
        }
    }

    /**
     * Enumeration with aggregate functions
     */
    enum AGGREGATE {
        SUM{
            @Override
            public String getSqlQuery(String column) {
                return "SUM(" + column + ")";
            }
        };

        /**
         * Get the string representation of aggregation function
         *
         * @param column Table column name
         * @return String representation of the sql function
         */
        public abstract String getSqlQuery(String column);
    }

    /**
     * Direction for ORDER BY (ascending and descending)
     */
    enum ORDER {
        ASC, DESC
    }

    /**
     * SELECT from a table
     *
     * @param baseTableName Table name
     * @return Self builder
     */
    SqlQueryBuilder from(String baseTableName);

    /**
     * Join one table to another
     *
     * @param joinBy Join type from {@link JOINS}
     * @param leftTable Left table name for joining
     * @param leftTableColumn Column name in the left table for joining
     * @param rightTable Right table name for joining
     * @param rightTableColumn Column name in the right table for joining
     * @return Self builder
     */
    SqlQueryBuilder join(JOINS joinBy, String leftTable, String leftTableColumn, String rightTable, String rightTableColumn);

    /**
     * Columns which should be selected with aggregate function applying
     *
     * @param table Table name where the column came from
     * @param column Column name
     * @param aggregateFunction Aggregate function from {@link AGGREGATE}.
     *                          Pass null if you don't need this to be applied
     * @return Self build
     */
    SqlQueryBuilder output(String table, String column, AGGREGATE aggregateFunction);

    /**
     * Columns which should be selected
     *
     * @param table Table name where the column came from
     * @param column Column name
     * @return Self build
     */
    SqlQueryBuilder output(String table, String column);

    /**
     * GROUP BY condition
     *
     * @param table Table name where the column came from
     * @param column Column name for grouping
     * @return Self build
     */
    SqlQueryBuilder groupBy(String table, String column);

    /**
     * ORDER BY condition with a necessary order direction
     *
     * @param table Table name where the column came from
     * @param column Column name for grouping
     * @param order Order direction
     * @return Self build
     */
    SqlQueryBuilder orderBy(String table, String column, ORDER order);

    /**
     * ORDER BY condition with an ascending order direction by default
     *
     * @param table Table name where the column came from
     * @param column Column name for grouping
     * @return Self build
     */
    SqlQueryBuilder orderBy(String table, String column);

    /**
     * Limit the number of the result (sql dialect specific)
     *
     * @param count The number of desired results
     * @return Self build
     */
    SqlQueryBuilder limit(int count);

    /**
     * Get String representation for the full built SQL query
     *
     * @return String with SQL query
     */
    String build();

}
