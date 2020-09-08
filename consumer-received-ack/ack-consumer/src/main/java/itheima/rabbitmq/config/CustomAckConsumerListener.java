package itheima.rabbitmq.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * **目标：演示消费端限流效果**
 */
@Component
public class CustomAckConsumerListener implements ChannelAwareMessageListener {
    /**
     * 监听到消息之后执行的方法
     * @param message 消息内容
     * @param channel 消息所在频道
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //获取消息内容
        byte[] messageBody = message.getBody();
        String msg = new String(messageBody, "utf-8");
        System.out.println("接收到消息，执行具体业务逻辑{} 消息内容："+msg);
        //获取投递标签
        MessageProperties messageProperties = message.getMessageProperties();
        long deliveryTag = messageProperties.getDeliveryTag();
        try {
            //休眠3秒
            Thread.sleep(3000);
            /**
             * 手动签收消息
             * 参数1：消息投递标签
             * 参数2：是否批量签收：true一次性签收所有，false，只签收当前消息
             */
            channel.basicAck(deliveryTag,false);
            System.out.println("手动签收完成：{}");

        } catch (Exception ex){
            /**
             * 手动拒绝签收
             * 参数1：当前消息的投递标签
             * 参数2：是否批量签收：true一次性签收所有，false，只签收当前消息
             * 参数3：是否重回队列，true为重回队列，false为不重回
             */
            channel.basicNack(deliveryTag,false,true);
            System.out.println("拒绝签收，重回队列：{}"+ex);
        }
    }
}