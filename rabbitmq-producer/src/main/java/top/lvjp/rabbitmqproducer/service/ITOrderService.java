package top.lvjp.rabbitmqproducer.service;

import top.lvjp.rabbitmqmodel.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lvjp
 * @since 2019-05-03
 */
public interface ITOrderService extends IService<TOrder> {

    void createOrder(TOrder tOrder);

    void createOrderFail(TOrder order);
}
