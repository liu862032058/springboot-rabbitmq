package com.itheima.rabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者，接收消息队列消息监听器
 * 必须将当前监听器对象注入Spring的容器中
 */
@Component
@RabbitListener(queues = "simple_queue")
public class SimpleListener {

    @RabbitHandler
    public void simpleHandler(String msg){
        System.out.println("=====接收消息====>"+msg);
    }
}
