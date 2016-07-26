package org.zoltor.builder.impl;

import org.zoltor.builder.SqlQueryBuilder;

/**
 * Created by zoltor on 26/07/16.
 * Oracle DB query builder implementation
 */
public class OracleQueryBuilder extends BaseSqlQueryBuilder implements SqlQueryBuilder {

    @Override
    public SqlQueryBuilder limit(int count) {
        sqlWhereQueryPart.append("\nWHERE ROWNUM <= ").append(count);
        return this;
    }

}
