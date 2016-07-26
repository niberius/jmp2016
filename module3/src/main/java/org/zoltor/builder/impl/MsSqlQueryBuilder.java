package org.zoltor.builder.impl;

import org.zoltor.builder.SqlQueryBuilder;

/**
 * Created by zoltor on 26/07/16.
 * MS SQL Query builder implementation
 */
public class MsSqlQueryBuilder extends BaseSqlQueryBuilder implements SqlQueryBuilder {

    @Override
    public SqlQueryBuilder limit(int count) {
        // Replace "SELECT" word with "SELECT TOP count"
        sqlSelectQueryPart.replace(0, "SELECT".length(), "SELECT TOP " + count);
        return this;
    }
}
