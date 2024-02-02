package com.nhnacademy.edu.springframework.configuration;

import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.annotation.Dooray;
import com.nhnacademy.edu.springframework.aop.MethodAspect;
import com.nhnacademy.edu.springframework.sender.DoorayMessageSender;
import com.nhnacademy.edu.springframework.service.MessageSendService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("classpath:setting.properties")
public class JavaConfig {

    @Bean
    public DoorayMessageSender doorayMessageSender(DoorayHookSender doorayHookSender) {
        return new DoorayMessageSender(doorayHookSender);
    }

    @Bean
    public DoorayHookSender doorayHookSender(RestTemplate restTemplate, @Value("${hookUrl}") String hookUrl) {
        return new DoorayHookSender(restTemplate, hookUrl);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public MessageSendService messageSendService(DoorayMessageSender doorayMessageSender) {
        return new MessageSendService(doorayMessageSender);
    }

    @Bean
    public MethodAspect methodAspect(){
        return new MethodAspect();
    }
}


