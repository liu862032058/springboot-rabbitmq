package itheima.rabbitmq.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//发送消息回调确认类，实现回调接口ConfirmCallback,重写其中confirm()方法
@Component
public class MessageConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 创建RabbitTemplate对象之后执行当前方法，为模板对象设置回调确认方法
     * 设置消息确认回调方法
     * 设置消息回退回调方法
     */
    @PostConstruct
    public void initRabbitTemplate(){
        //设置消息确认回调方法
        rabbitTemplate.setConfirmCallback(this::confirm);

        //设置消息回退回调方法
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }
    /**
     * 投递到交换机，不论投递成功还是失败都回调次方法
     * @param correlationData 投递相关数据
     * @param ack 是否投递到交换机
     * @param cause 投递失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            System.out.println("消息进入交换机成功{}");
        } else {
            System.out.println("消息进入交换机失败{} ， 失败原因：" + cause);
        }
    }

    /**
     * 当消息投递到交换机，交换机路由到消息队列中出现异常，执行returnedMessaged方法
     * @param message 投递消息内容
     * @param replyCode 返回错误状态码
     * @param replyText 返回错误内容
     * @param exchange 交换机名称
     * @param routingKey 路由键
     */

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