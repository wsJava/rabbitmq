package top.lvjp.rabbitmqproducer.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * 消息发送状态
 * @author lvjp
 * @date 2019/5/3
 */
@Getter
public enum MessageStatus implements Serializable {

    /**
     * 消息正在发送中
     */
    SENDING(0),

    /**
     * 消息发送成功
     */
    SUCCESS(1),

    /**
     * 消息发送失败
     */
    FAILURE(2);

    private Integer status;

    MessageStatus(Integer status) {
        this.status = status;
    }
}
