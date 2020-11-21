package com.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 消息生产者，通过注入AmqpTemplate接口实例来实现消息的发送
 */
@Component
public class MessageSender {

    /**
     * AmqpTemplate 定义了一套针对AMQP协议的基础操作
     */
    @Autowired
    private AmqpTemplate rabbitMqTemplate;

    public void send() {
        String message = "hello" + new Date();
        System.out.println("Message Sender => " + message);
        this.rabbitMqTemplate.convertAndSend("hello", message);
    }
}
