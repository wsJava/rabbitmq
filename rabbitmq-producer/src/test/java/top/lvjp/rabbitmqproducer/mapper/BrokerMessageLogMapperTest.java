package top.lvjp.rabbitmqproducer.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BrokerMessageLogMapperTest {

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Test
    public void updateBrokerMessageLogStatus() {

        brokerMessageLogMapper.updateBrokerMessageLogStatus("1556896527925$c74239e1-1b26-4a2c-8531-755b40a2a524", 2, LocalDateTime.now());
    }
}