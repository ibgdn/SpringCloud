package com.ibgdn.stream.controller;

import com.ibgdn.stream.message.CustomizeMessageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Stream Controller
 */
@RestController
public class StreamController {
    @Autowired
    CustomizeMessageChannel messageChannel;

    @GetMapping("/sendMessage")
    public void messageSender() {
        messageChannel.output().send(MessageBuilder.withPayload("Stream Customize Message.").build());
    }
}
