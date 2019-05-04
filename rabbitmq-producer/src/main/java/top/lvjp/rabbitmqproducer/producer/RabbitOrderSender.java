package top.lvjp.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.rabbitmqmodel.entity.TOrder;
import top.lvjp.rabbitmqproducer.constant.Constants;
import top.lvjp.rabbitmqproducer.mapper.BrokerMessageLogMapper;

import java.time.LocalDateTime;

import static top.lvjp.rabbitmqmodel.constant.Constants.ORDER_EXCHANGE;
import static top.lvjp.rabbitmqmodel.constant.Constants.ROUTING_KEY;

/**
 * @author lvjp
 * @date 2019/5/3
 */
@Service
public class RabbitOrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    // 回调函数, confirm 确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            System.out.println("correlationData : " + correlationData);
            String messageId = correlationData.getId();
            if (ack){
//                如果 confirm 返回成功, 则进行更新
                brokerMessageLogMapper.updateBrokerMessageLogStatus(messageId,
                        Constants.ORDER_SEND_SUCCESS, LocalDateTime.now());
            } else {
//                失败则进行具体的后续操作: 重试或补偿手段,
                System.err.println("消息发送失败==================");
                System.err.println("message : " + s);
            }
        }
    };

    public void sendOrder(TOrder order){

        rabbitTemplate.setConfirmCallback(confirmCallback);

//        消息唯一 ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());

        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, // exchange
                ROUTING_KEY,       // routingKey
                order,      //消息内容
                correlationData);       // 消息唯一 id
    }

    public void sendOrderFailure(TOrder order){
        rabbitTemplate.setConfirmCallback(confirmCallback);

//        消息唯一 ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());

        rabbitTemplate.convertAndSend("null-order-exchange", // exchange
                "ode",       // routingKey
                order,      //消息内容
                correlationData);       // 消息唯一 id
    }
}
