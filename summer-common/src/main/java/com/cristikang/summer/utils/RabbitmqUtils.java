package com.cristikang.summer.utils;

import com.cristikang.summer.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: sunkang
 * @CreateTime: 2019-01-30 11:31
 * @Description: ${Description}
 */
@Slf4j
@Component
public class RabbitmqUtils {

    @Value("${mq.summer.enable:false}")
    private boolean isSummerMqEnabled;

    @Resource(name = "summerRabbitTemplate")
    private RabbitTemplate summerRabbitTemplate;

    /**
     * 推送消息至rabbitmq
     */
    public void sendMessage(String exchange, String routingKey, Object msg){
        if ( !isSummerMqEnabled ) {
            throw new BusinessException("mq未开启，不能推送消息！");
        }
        try {
            summerRabbitTemplate.convertAndSend(exchange, routingKey, msg);
            log.info("推送消息{}成功", msg);
        } catch (Exception e) {
            log.error("推送消息{}失败", msg);
            throw new BusinessException("消息推送失败", e);
        }


    }
}
