package com;


import com.sender.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = MessageBusApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MessageBusApplicationTests {


    @Autowired
    private MessageSender messageSender;

    @Test
    public void hello() throws Exception {
        messageSender.send();
    }
}
