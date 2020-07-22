package com.controller;

import com.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;

@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testAddUser(){
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nickname", "sulayman");
        paramMap.put("telephone", "13895234340");
        paramMap.put("password", "take_over");
        paramMap.put("sex", "true");
        try{
            userService.addUser(paramMap);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        mystery(1234);
    }

    public static void mystery(int x) {
        System.out.println(x % 10);
        if ((x / 10) != 0) {
            mystery(x / 10);
        }
        System.out.println(x % 10);
    }
}
