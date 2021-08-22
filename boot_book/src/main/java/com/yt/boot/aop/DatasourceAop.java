package com.yt.boot.aop;

import com.yt.boot.config.DatasourceConfig;
import com.yt.boot.handler.DatasourceContextHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-22 12:29
 */
@Aspect
@Component
@Order(-9999)
public class DatasourceAop {

    @Before(value = "execution(* com.yt.boot.service..*.*(..))")
    public void dynamicDatasourceAop(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        Map<String, String> methodType = DatasourceContextHandler.METHOD_TYPE;
        String keys = methodType.get(DatasourceConfig.MASTER_DATASOURCE);
        String[] split = keys.split(",");
        for (String key : split) {
            if (method.startsWith(key)) {
                DatasourceContextHandler.setDatasourceBeanName(DatasourceConfig.MASTER_DATASOURCE);
                return;
            }
        }
        DatasourceContextHandler.setDatasourceBeanName(DatasourceConfig.SLAVE_DATASOURCE);
    }
}
