package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * create by qiulisun on 2021/2/13.<br>
 *
 * @author 51050
 */
@Mapper
public interface PaymentDao {
    /**
     * 新增
     * @param payment
     * @return
     */
    int createPayment(Payment payment);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
