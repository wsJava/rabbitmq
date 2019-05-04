package top.lvjp.rabbitmqmodel.constant;

/**
 * @author lvjp
 * @date 2019/5/4
 */
public interface Constants {

    String ORDER_EXCHANGE = "order-exchange";

    String ORDER_QUEUE = "order-queue";

    String ROUTING_KEY = "order.*";
}
