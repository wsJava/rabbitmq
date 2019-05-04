package top.lvjp.rabbitmqproducer.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.lvjp.rabbitmqmodel.entity.BrokerMessageLog;
import top.lvjp.rabbitmqmodel.entity.TOrder;
import top.lvjp.rabbitmqproducer.constant.Constants;
import top.lvjp.rabbitmqproducer.mapper.BrokerMessageLogMapper;
import top.lvjp.rabbitmqproducer.producer.RabbitOrderSender;

import java.time.LocalDateTime;
import java.util.List;

import static top.lvjp.rabbitmqproducer.constant.Constants.ORDER_SENDING;
import static top.lvjp.rabbitmqproducer.constant.Constants.ORDER_SEND_FAILURE;

/**
 * @author lvjp
 * @date 2019/5/3
 */
@Component
public class RetryMessageTask {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend(){
        System.out.println("----------- 定时任务开始 --------");
        List<BrokerMessageLog> logList = brokerMessageLogMapper.selectList(new QueryWrapper<BrokerMessageLog>()
                .lambda().eq(BrokerMessageLog::getStatus, ORDER_SENDING));
//                .lt(BrokerMessageLog::getNextRetry, LocalDateTime.now()));

        logList.forEach(t -> {

            if (t.getTryCount() >= Constants.TRY_COUNT){
                brokerMessageLogMapper.updateBrokerMessageLogStatus(t.getMessageId(),
                        ORDER_SEND_FAILURE, LocalDateTime.now());

//                t.setTryCount(t.getTryCount() + 1).setStatus(ORDER_SEND_FAILURE).setNextRetry(LocalDateTime.now());
//                brokerMessageLogMapper.update(t, new QueryWrapper<BrokerMessageLog>().lambda()
//                        .select(BrokerMessageLog::getMessageId));
//
            } else {
                brokerMessageLogMapper.updateForReSend(t.getMessageId(), LocalDateTime.now());
                TOrder reSendOrder = JSON.parseObject(t.getMessage(), TOrder.class);

                try {
                    rabbitOrderSender.sendOrderFailure(reSendOrder);
                } catch (Exception e){
                    System.err.println("catch exception : " + e.getMessage());
                }
            }
        });

    }
}
