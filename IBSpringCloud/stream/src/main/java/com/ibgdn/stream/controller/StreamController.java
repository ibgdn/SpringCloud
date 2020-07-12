package com.ibgdn.stream.controller;

import com.ibgdn.stream.message.CustomizeMessageChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Stream Controller
 */
@RestController
public class StreamController {

    private static final Logger logger = LoggerFactory.getLogger(StreamController.class);

    @Autowired
    CustomizeMessageChannel messageChannel;

    /**
     * 发送自定义消息
     */
    @GetMapping("/sendMessage")
    public void messageSender() {
        messageChannel.output().send(MessageBuilder.withPayload("Stream Customize Message.").build());
    }

    /**
     * 发送定时消息
     */
    @GetMapping("/sendDelayMessage")
    public void messageDelaySender() {
        logger.info("Send Delay Message: [{}]", new Date());
        messageChannel.output()
                .send(MessageBuilder.withPayload("Stream Customize Delay Message.")
                        .setHeader("x-delay", 3000).build());
    }
}
