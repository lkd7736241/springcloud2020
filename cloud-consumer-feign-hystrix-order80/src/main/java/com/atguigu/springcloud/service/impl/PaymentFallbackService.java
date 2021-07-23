package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * create by qiulisun on 2021/7/18.<br>
 *
 * @author 51050
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfoSuccess(Integer id) {
        return "---PaymentFallbackService fall back paymentInfoSuccess---";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "---PaymentFallbackService fall back paymentInfoTimeOut---";
    }
}
