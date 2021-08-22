package com.yt.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.yt.boot.handler.DynamicDatasource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-22 12:26
 */
@Configuration
public class DatasourceConfig {
    public static final String MASTER_DATASOURCE = "masterDatasource";
    public static final String SLAVE_DATASOURCE = "slaveDatasource";

    @Bean(name = "dynamicDatasource")
    public DataSource dynamicDatasource(@Qualifier(MASTER_DATASOURCE) DataSource masterDatasource,
                                        @Qualifier(SLAVE_DATASOURCE) DataSource slaveDatasource) {
        DynamicDatasource datasource = new DynamicDatasource();
        HashMap<Object, Object> map = new HashMap<>();
        map.put(MASTER_DATASOURCE,masterDatasource);
        map.put(SLAVE_DATASOURCE,slaveDatasource);
        datasource.setTargetDataSources(map);
        datasource.setDefaultTargetDataSource(slaveDatasource);
        return datasource;
    }

    @Bean(name = MASTER_DATASOURCE)
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDatasource() {
        return new DruidDataSource();
    }

    @Bean(name = SLAVE_DATASOURCE)
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDatasource() {
        return new DruidDataSource();
    }


}
