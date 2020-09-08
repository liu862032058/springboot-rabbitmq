//package itheima.rabbitmq.listener;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//import org.springframework.stereotype.Component;
//
///**
// * 自定义监听器，监听到消息之后，立即执行onMessage方法
// * 2. 消费者确认(ACK)
// */
//@Component
//public class CustomAckConsumerListener implements ChannelAwareMessageListener {
//    /**
//     * 监听到消息之后执行的方法
//     * @param message 消息内容
//     * @param channel 消息所在频道
//     */
////    @Override
////    public void onMessage(Message message, Channel channel) throws Exception {
////        //获取消息内容
////        byte[] messageBody = message.getBody();
////        String msg = new String(messageBody, "utf-8");
////        System.out.println("接收到消息，执行具体业务逻辑{} 消息内容："+msg);
////        //获取投递标签
////        MessageProperties messageProperties = message.getMessageProperties();
////        long deliveryTag = messageProperties.getDeliveryTag();
////        /**
////         * 签收消息，前提条件，必须在监听器的配置中，开启手动签收模式
////         * 参数1：消息投递标签
////         * 参数2：是否批量签收：true一次性签收所有，false，只签收当前消息
////         */
////        channel.basicAck(deliveryTag,false);
////        System.out.println("手动签收完成：{}");
////    }
//
//    @Override
//    public void onMessage(Message message, Channel channel) throws Exception {
//        //获取消息内容
//        byte[] messageBody = message.getBody();
//        String msg = new String(messageBody, "utf-8");
//        System.out.println("接收到消息，执行具体业务逻辑{} 消息内容："+msg);
//        //获取投递标签
//        MessageProperties messageProperties = message.getMessageProperties();
//        long deliveryTag = messageProperties.getDeliveryTag();
//        try {
//            if (msg.contains("苹果")){
//                throw new RuntimeException("不允许卖苹果手机！！！");
//            }
//            /**
//             * 手动签收消息
//             * 参数1：消息投递标签
//             * 参数2：是否批量签收：true一次性签收所有，false，只签收当前消息
//             */
//            channel.basicAck(deliveryTag,false);
//            System.out.println("手动签收完成：{}");
//
//        } catch (Exception ex){
//            /**
//             * 手动拒绝签收 basicNack
//             * 参数1：当前消息的投递标签
//             * 参数2：是否批量签收：true一次性签收所有，false，只签收当前消息
//             * 参数3：是否重回队列，true为重回队列，false为不重回
//             */
////            channel.basicNack(deliveryTag,false,false);
//            channel.basicAck(deliveryTag,true);
////            channel.basicNack(deliveryTag,false,true);
//            System.out.println("拒绝签收，重回队列：{}"+ex);
//        }
//    }
//}