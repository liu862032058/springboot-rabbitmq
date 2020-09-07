package itheima.rabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 工作队列：消费者接收监听器2，接收来自消息队列中的消息
 */
@Component
@RabbitListener(queues = "work_queue")
public class WorkListener2 {

    @RabbitHandler
    public void workHandler(String msg){
        System.out.println("=====工作队列消息接收端2====>"+msg);
    }
}