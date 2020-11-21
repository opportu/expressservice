package com.receiver;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
//实现对于 hello 指定对于消息的处理方法
@RabbitListener(queues = "hello")
public class MessageReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Message Receiver => " + hello);
    }
}
