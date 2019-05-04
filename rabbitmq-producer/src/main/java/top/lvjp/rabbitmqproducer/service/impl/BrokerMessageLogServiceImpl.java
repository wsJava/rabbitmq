package top.lvjp.rabbitmqproducer.service.impl;

import top.lvjp.rabbitmqmodel.entity.BrokerMessageLog;
import top.lvjp.rabbitmqproducer.mapper.BrokerMessageLogMapper;
import top.lvjp.rabbitmqproducer.service.IBrokerMessageLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvjp
 * @since 2019-05-03
 */
@Service
public class BrokerMessageLogServiceImpl extends ServiceImpl<BrokerMessageLogMapper, BrokerMessageLog> implements IBrokerMessageLogService {

}
