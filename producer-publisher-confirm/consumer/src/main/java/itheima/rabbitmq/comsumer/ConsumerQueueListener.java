package itheima.rabbitmq.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息队列接收消息监听器
 */
@Component
@RabbitListener(queues = "order.A")
public class ConsumerQueueListener {

    //接收消息，监听器调用此方法执行业务逻辑
    @RabbitHandler
    public void queueListenerHandle(String msg){
        System.out.println("下单消息{}，内容："+msg);
    }
}