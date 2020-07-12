package com.ibgdn.stream.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

/**
 * 自定义消息接收
 */
@EnableBinding(CustomizeMessageChannel.class)
public class CustomizeMessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(CustomizeMessageReceiver.class);

    /**
     * 请求接口后日志输出：
     * <p>
     * Class: [com.ibgdn.stream.message.CustomizeMessageReceiver$$EnhancerBySpringCGLIB$$b261458c], Method: [customizeReceiver], Message: [Stream Message.]
     *
     * @param object 传入消息
     */
    @StreamListener(CustomizeMessageChannel.INPUT)
    public void customizeReceiver(Object object) {
        logger.info("Class: [{}], Method: [{}], Date: [{}], Message: [{}]",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), new Date(), object);
    }
}
