package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 自定义过滤器
 * create by qiulisun on 2021/7/26.<br>
 *
 * @author 51050
 */
@Component
@Slf4j
public class LogGatewayFilter implements GlobalFilter, Ordered {
    private static Logger logger = LoggerFactory.getLogger(LogGatewayFilter.class);

    /**
     * 过滤方法
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("global filter : " + new Date());
        ServerHttpRequest request = exchange.getRequest();
        String username = request.getQueryParams().getFirst("username");
        if (username == null) {
            logger.info("用户名为空，非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // 放行
        return chain.filter(exchange);
    }

    /**
     * 过滤器加载的顺序越小，优先级别越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
