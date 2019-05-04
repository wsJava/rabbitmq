package top.lvjp.rabbitmqproducer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.lvjp.rabbitmqmodel.entity.TOrder;
import top.lvjp.rabbitmqproducer.service.ITOrderService;

import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lvjp
 * @since 2019-05-03
 */
@RestController
public class TOrderController {

    @Autowired
    private ITOrderService orderService;

    @GetMapping("/order/{id}")
    public String test(@PathVariable("id") Integer id){
        TOrder order = new TOrder();
        order.setId(id);
        order.setName("test-order");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());

        orderService.createOrder(order);
        return "success";
    }

    @GetMapping("/order/failure/{id}")
    public String testFailure(@PathVariable("id") Integer id){
        TOrder order = new TOrder();
        order.setId(id);
        order.setName("test-order-failure");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());

        orderService.createOrderFail(order);
        return "success";
    }

}
