package itheima.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 向消息队列发送消息
 */
@RestController
public class MessageController {

    //注入RabbitMQ模板对象
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //调用RabbitMQ模板发送消息方法，向消息队列发送消息内容
    @RequestMapping("/direct/sendMsg")
    public String sendMsg(String exchange,String routingkey,String msg){
        /**
         * 向交换机发送消息
         * 参数1：exchange：交换机
         * 参数2：routingkey：路由键
         * 参数3：msg：发送消息的内容
         */
        rabbitTemplate.convertAndSend(exchange,routingkey,msg);
        return "已投递~";
    }
}