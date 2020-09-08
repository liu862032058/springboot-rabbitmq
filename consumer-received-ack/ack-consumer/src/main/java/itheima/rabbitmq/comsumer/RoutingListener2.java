package itheima.rabbitmq.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 路由模式：消息队列接收监听器2，接收来自路由模式发送的消息
 *
 * 消费死信队列
 */
@Component
@RabbitListener(queues = "deadQueue")
public class RoutingListener2 {

    @RabbitHandler
    public void routingHandler(String msg){
        System.out.println("=====路由模式消息接收监听器【2】=====>"+msg);
    }
}