package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * create by qiulisun on 2021/7/14.<br>
 *
 * @author 51050
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfoSuccess(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "，paymentInfoSuccess: " + id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeOut(Integer id) {
        int number = 3;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + ", paymentInfoTimeOut: " + id + ", 耗时： " + number;
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程名称：" + Thread.currentThread().getName() + ", 服务端8001超时或异常";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enable", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),         //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMillisecnods", value = "10000"),   //时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")        //通过率
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功， 流水号：" + uuid;
    }

    /**
     * 服务熔断的降级处理
     *
     * @param id
     * @return
     */
    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试";
    }
}
