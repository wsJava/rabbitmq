package top.lvjp.rabbitmqproducer.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import top.lvjp.rabbitmqmodel.entity.BrokerMessageLog;
import top.lvjp.rabbitmqmodel.entity.TOrder;
import top.lvjp.rabbitmqproducer.constant.Constants;
import top.lvjp.rabbitmqproducer.mapper.BrokerMessageLogMapper;
import top.lvjp.rabbitmqproducer.mapper.TOrderMapper;
import top.lvjp.rabbitmqproducer.producer.RabbitOrderSender;
import top.lvjp.rabbitmqproducer.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvjp
 * @since 2019-05-03
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Override
    public void createOrder(TOrder order) {
        LocalDateTime orderTime = LocalDateTime.now();
        tOrderMapper.insert(order);

        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        brokerMessageLog.setMessage(JSON.toJSONString(order));
        brokerMessageLog.setStatus(0);
        brokerMessageLog.setNextRetry(orderTime.plusMinutes(Constants.ORDER_TIMEOUT_MIN));

        brokerMessageLogMapper.insert(brokerMessageLog);

//        发送消息
        rabbitOrderSender.sendOrder(order);
    }

    @Override
    public void createOrderFail(TOrder order) {
        LocalDateTime orderTime = LocalDateTime.now();
        tOrderMapper.insert(order);

        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        brokerMessageLog.setMessage(JSON.toJSONString(order));
        brokerMessageLog.setStatus(0);
        brokerMessageLog.setNextRetry(orderTime.plusSeconds(Constants.ORDER_TIMEOUT_SECONDE));

        brokerMessageLogMapper.insert(brokerMessageLog);

//        发送消息
        rabbitOrderSender.sendOrderFailure(order);

    }
}
