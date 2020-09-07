package itheima.rabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 通配符模式：消息队列接收监听器1，接收来自通配符模式发送的消息
 *
 */
@Component
@RabbitListener(queues = "topic_queue2")
public class TopicListener2 {

    @RabbitHandler
    public void topicHandler(String msg){
        System.out.println("=====通配符模式消息接收监听器【2】=====>"+msg);
    }
}