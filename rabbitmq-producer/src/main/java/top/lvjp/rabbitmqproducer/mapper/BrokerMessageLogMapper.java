package top.lvjp.rabbitmqproducer.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.rabbitmqmodel.entity.BrokerMessageLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lvjp
 * @since 2019-05-03
 */
public interface BrokerMessageLogMapper extends BaseMapper<BrokerMessageLog> {

//    /**
//     *  查询消息状态为 0, 且已经超时的消息集合
//     * @return
//     */
//    List<BrokerMessageLog> listByStatusAndTimeout();

    /**
     * 重新发送统计 count 发送次数 +1
     * @param messageId
     * @param updateTime
     */
    void updateForReSend(@Param("messageId") String messageId, @Param("updateTime") LocalDateTime updateTime);

    /**
     * 更新最终消息发送结果, 成功 or 失败
     * @param messageId
     * @param status
     * @param updateTime
     */
    void updateBrokerMessageLogStatus(@Param("messageId") String messageId, @Param("status") Integer status, @Param("updateTime") LocalDateTime updateTime);
}
