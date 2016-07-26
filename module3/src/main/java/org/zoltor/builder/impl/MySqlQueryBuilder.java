package org.zoltor.builder.impl;

import org.zoltor.builder.SqlQueryBuilder;

/**
 * Created by zoltor on 26/07/16.
 * MySQL query builder implementation
 */
public class MySqlQueryBuilder extends BaseSqlQueryBuilder implements SqlQueryBuilder {

    @Override
    public SqlQueryBuilder limit(int count) {
        sqlLatestQueryPart.append("\nLIMIT ").append(count);
        return this;
    }
}
