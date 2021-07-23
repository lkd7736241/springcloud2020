package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by qiulisun on 2021/7/14.<br>
 *
 * @author 51050
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentInfoTimeOutGlobalHandler")
public class OrderHystrixController {
    private static Logger logger = LoggerFactory.getLogger(OrderHystrixController.class);

    @Resource
    private PaymentHystrixService service;

    @GetMapping("/consumer/payment/hystrix/success/{id}")
    public String paymentInfoSuccess(@PathVariable("id") Integer id) {
        String result = service.paymentInfoSuccess(id);
        logger.info("result :" + result);
        return result;
    }

    /**
     * 需要特殊处理的方法，加注释
     *
     * @param id
     * @return
     * @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
     * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
     * })
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        String result = service.paymentInfoTimeOut(id);
        logger.info("result :" + result);
        return result;
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程名称：" + Thread.currentThread().getName() + ", 客户端80超时或异常";
    }

    public String paymentInfoTimeOutGlobalHandler(Integer id) {
        return "线程名称：" + Thread.currentThread().getName() + ", 服务端8001超时或异常, 全局处理";
    }
}
