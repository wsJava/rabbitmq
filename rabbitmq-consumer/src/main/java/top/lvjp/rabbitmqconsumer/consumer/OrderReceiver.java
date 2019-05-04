package top.lvjp.rabbitmqconsumer.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import top.lvjp.rabbitmqmodel.entity.TOrder;
;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lvjp
 * @date 2019/5/3
 */
//@Component
public class OrderReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(value = "order-exchange", durable = "true", type = "topic"),
            key = "order.*"))
    @RabbitHandler
    public void onOrderMessage(@Payload TOrder order,
                               @Headers Map<String, Object> headers,
                               Channel channel) throws Exception {

        System.err.println("-------------- 收到消息, 开始消费 ---------------");
        System.err.println(order.toString());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
