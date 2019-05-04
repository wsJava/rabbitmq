package top.lvjp.rabbitmqmodel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvjp
 * @since 2019-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BrokerMessageLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息唯一ID
     */
    @TableId
    private String messageId;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 重试次数
     */
    private Integer tryCount;

    /**
     * 消息投递状态 0:投递中，1:投递成功，2:投递失败
     */
    private Integer status;

    /**
     * 下一次重试时间
     */
    private LocalDateTime nextRetry;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
