package com.hyt.book.handler;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-22 12:31
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceContextHandler.getDatasourceBeanName();
    }
}
