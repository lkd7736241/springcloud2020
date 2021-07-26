package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by qiulisun on 2021/7/26.<br>
 *
 * @author 51050
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个路由规则
     * 当访问本服务"/guonei"时，
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("customRouteLocator1", route -> route.path("/guonei").
                uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
