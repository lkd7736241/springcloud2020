package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
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
public class PaymentController {
    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private PaymentService service;

    @GetMapping("/payment/hystrix/success/{id}")
    public String paymentInfoSuccess(@PathVariable("id") Integer id) {
        String result = service.paymentInfoSuccess(id);
        logger.info("****result: " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        String result = service.paymentInfoTimeOut(id);
        logger.info("****result: " + result);
        return result;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = service.paymentCircuitBreaker(id);
        logger.info("*****result: " + result);
        return result;
    }
}
