package com.atguigu.springcloud.service;

/**
 * create by qiulisun on 2021/7/14.<br>
 *
 * @author 51050
 */

public interface PaymentService {
    /**
     * 服务降级——正常访问
     *
     * @param id
     */
    String paymentInfoSuccess(Integer id);

    /**
     * 服务降级——超时访问
     *
     * @param id
     */
    String paymentInfoTimeOut(Integer id);

    /**
     * 服务熔断
     * @param id
     * @return
     */
    String paymentCircuitBreaker(Integer id);
}
