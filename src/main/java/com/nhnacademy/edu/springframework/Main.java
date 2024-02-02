package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.domain.User;
import com.nhnacademy.edu.springframework.service.MessageSendService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class    Main {
    public static void main(String[] args) {
        User user = new User("배범익", "AIGY01-015");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/beans.xml");
        context.getBean("messageSendService", MessageSendService.class).doSendMessage(user, "테스트 메세지");
    }
}