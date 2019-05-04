package top.lvjp.rabbitmqproducer.constant;

/**
 * @author lvjp
 * @date 2019/5/3
 */
public interface Constants {

    /**
     * 超时时间, min
     */
    int ORDER_TIMEOUT_MIN = 1;

    int ORDER_TIMEOUT_SECONDE= 5;

    /**
     * 重试次数
     */
    int TRY_COUNT = 3;


    /**
     * 消息发送中
     */
    int ORDER_SENDING = 0;

    /**
     * 消息成功
     */
    int ORDER_SEND_SUCCESS = 1;

    /**
     * 消息失败
     */
    int ORDER_SEND_FAILURE = 2;
}
