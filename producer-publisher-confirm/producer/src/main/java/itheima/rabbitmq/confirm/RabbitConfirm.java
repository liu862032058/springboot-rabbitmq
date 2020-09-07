package itheima.rabbitmq.confirm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitConfirm implements RabbitTemplate.ConfirmCallback
    ,RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //..省略
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }



    /**
     * 当消息投递到交换机，交换机路由到消息队列中出现异常，执行returnedMessaged方法
     * @param message 投递消息内容
     * @param replyCode 返回错误状态码
     * @param replyText 返回错误内容
     * @param exchange 交换机名称
     * @param routingKey 路由键
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("交换机路由至消息队列出错：>>>>>>>");
        System.out.println("交换机："+exchange);
        System.out.println("路由键："+routingKey);
        System.out.println("错误状态码："+replyCode);
        System.out.println("错误原因："+replyText);
        System.out.println("发送消息内容："+message.toString());
        System.out.println("<<<<<<<<");

    }



}