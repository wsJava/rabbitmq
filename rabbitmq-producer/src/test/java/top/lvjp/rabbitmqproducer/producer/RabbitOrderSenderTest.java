package top.lvjp.rabbitmqproducer.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.rabbitmqmodel.entity.TOrder;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitOrderSenderTest {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Test
    public void sendOrder() {
        TOrder order = new TOrder();
        order.setId(1);
        order.setName("test-order");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());
        rabbitOrderSender.sendOrder(order);
    }
}