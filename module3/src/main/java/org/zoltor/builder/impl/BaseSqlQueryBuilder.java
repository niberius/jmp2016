package org.zoltor.builder.impl;

import org.zoltor.builder.SqlQueryBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zoltor on 26/07/16.
 * Abstract class with common implementation for all SQL dialects
 */
abstract class BaseSqlQueryBuilder implements SqlQueryBuilder {

    private Map<String, String> synonymTableNames;
    private String baseTableName;
    // Base sql select query will be appended here except of rest after "... FROM table"
    StringBuilder sqlSelectQueryPart;
    StringBuilder sqlJoinQueryPart;
    StringBuilder sqlWhereQueryPart;
    StringBuilder sqlGroupByQueryPart;
    StringBuilder sqlOrderByQueryPart;
    StringBuilder sqlLatestQueryPart; // For any DB-specific additions (e.g. LIMIT in MySQL)

    /**
     * Initialize the default values at object construction
     */
    BaseSqlQueryBuilder() {
        initDefaultValues();
    }

    /** {@inheritDoc} */
    @Override
    public SqlQueryBuilder from(String baseTableName) {
        this.baseTableName = baseTableName;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public SqlQueryBuilder join(JOINS joinBy, String leftTable, String leftTableColumn,
                                String rightTable, String rightTableColumn) {
        String leftTableSynonym = getSynonym(leftTable);
        String rightTableSynonym = getSynonym(rightTable);
        sqlJoinQueryPart.append("\n").append(joinBy.getSqlSyntax()).append(" ")
                .append(rightTable).append(" ")
                .append(rightTableSynonym).append(" ON ")
                .append(leftTableSynonym).append(".").append(leftTableColumn).append(" = ")
                .append(rightTableSynonym).append(".").append(rightTableColumn);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public SqlQueryBuilder output(String table, String column, AGGREGATE aggregateFunction) {
        String tableSynonym = getSynonym(table);
        sqlSelectQueryPart.append(sqlSelectQueryPart.length() == 0 ? "SELECT " : ", ")
                .append(aggregateFunction != null ? aggregateFunction.getSqlQuery(tableSynonym + "." + column) :
                        tableSynonym + "." + column);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public SqlQueryBuilder output(String table, String column) {
        output(table, column, null);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public SqlQueryBuilder groupBy(String table, String column) {
        String tableSynonym = getSynonym(table);
        sqlGroupByQueryPart.append(sqlGroupByQueryPart.length() == 0 ? "\nGROUP BY " : ", ")
                .append(tableSynonym).append(".").append(column);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public SqlQueryBuilder orderBy(String table, String column, ORDER order) {
        String tableSynonym = getSynonym(table);
        sqlOrderByQueryPart.append(sqlOrderByQueryPart.length() == 0 ? "\nORDER BY " : ", ")
                .append(tableSynonym).append(".").append(column).append(" ").append(order.toString());
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public SqlQueryBuilder orderBy(String table, String column) {
        orderBy(table, column, ORDER.ASC);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String build() {
        String baseTableSynonym = getSynonym(baseTableName);
        String result = sqlSelectQueryPart.toString() + " FROM " + baseTableName + " " + baseTableSynonym +
                sqlJoinQueryPart.toString() + sqlWhereQueryPart.toString() + sqlGroupByQueryPart.toString() +
                sqlOrderByQueryPart.toString() + sqlLatestQueryPart.toString();
        initDefaultValues();
        return result;
    }

    /**
     * Reset / initialize default values. Method should be called on the object construction of after {@link #build}
     */
    void initDefaultValues() {
        synonymTableNames = new HashMap<>();
        baseTableName = "";
        sqlSelectQueryPart = new StringBuilder();
        sqlJoinQueryPart = new StringBuilder();
        sqlGroupByQueryPart = new StringBuilder();
        sqlOrderByQueryPart = new StringBuilder();
        sqlWhereQueryPart = new StringBuilder();
        sqlLatestQueryPart = new StringBuilder();
    }

    /**
     * Get short upper-cased synonym for the table name (e.g. C for Customer)
     *
     * @param tableName Table name
     * @return Synonym
     */
    private String getSynonym(String tableName) {
        String upperCasedTableName = tableName.toUpperCase();
        if (!synonymTableNames.containsKey(upperCasedTableName)) {
            createUniqueSynonym(upperCasedTableName);
        }
        return synonymTableNames.get(upperCasedTableName);
    }

    /**
     * Create unique short synonym and store it in the map
     * @param tableName Table name
     */
    private void createUniqueSynonym(String tableName) {
        int tableNameLength = tableName.length();
        for (int i = 0; i < tableNameLength; i++) {
            String tmpSynonym = tableName.substring(0, i + 1).toUpperCase();
            if (!synonymTableNames.containsValue(tmpSynonym)) {
                synonymTableNames.put(tableName, tmpSynonym);
                return;
            }
        }
    }
}
