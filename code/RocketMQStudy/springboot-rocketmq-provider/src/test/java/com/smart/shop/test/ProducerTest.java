package com.smart.shop.test;

import com.smart.shop.springboot.rocketmq.ProducerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yk
 * @Date: 2020/2/21 10:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProducerApplication.class})
@Slf4j
public class ProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void testSendMessage() {
        rocketMQTemplate.convertAndSend("springboot-rocketmq", "Hello springboot-rocketmq!");
        log.info("消息发送成功！ ");
    }

}