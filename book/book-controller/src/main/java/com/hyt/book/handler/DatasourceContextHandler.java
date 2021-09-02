package com.hyt.book.handler;

import com.hyt.book.config.DatasourceConfig;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Mr.He
 * @description:
 * @create 2021-08-22 12:27
 */
public class DatasourceContextHandler {
    public static ThreadLocal<String> datasourceBeanName = new ThreadLocal<>();

    public static final Map<String, String> METHOD_TYPE = new HashMap<>();

    static {
        METHOD_TYPE.put(DatasourceConfig.MASTER_DATASOURCE, ",add,delete,remove,update,insert,clear");
    }

    public static String getDatasourceBeanName() {
        return datasourceBeanName.get();
    }

    public static void setDatasourceBeanName(String datasourceBeanName) {
        DatasourceContextHandler.datasourceBeanName.set(datasourceBeanName);
    }
}
