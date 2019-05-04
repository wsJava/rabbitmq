package top.lvjp.rabbitmqproducer.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.rabbitmqmodel.entity.TOrder;
import top.lvjp.rabbitmqproducer.service.ITOrderService;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TOrderServiceImplTest {

    @Autowired
    private ITOrderService orderService;

    @Test
    public void createOrder() {

        TOrder order = new TOrder();
        order.setId(1);
        order.setName("test-order");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());

        orderService.createOrderFail(order);
    }
}