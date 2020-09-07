package com.itheima.rabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 发布订阅模式：消息监听1
 */
@Component
@RabbitListener(queues = "fanout_queue1")
public class PubAndSubListener1 {

    @RabbitHandler
    public void pubAndSubHandler(String msg){
        System.out.println("=====发布订阅模式接收消息端【1】=====>"+msg);
    }
}