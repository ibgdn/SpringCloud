package com.ibgdn.stream.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Spring Cloud Stream 消息接收
 */
// 绑定 Sink 消息通道
@EnableBinding(Sink.class)
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receiver(Object payload) {
        logger.info("Class [{}], Method: [{}]. Received: [{}]",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), payload);
    }
}
