package com.nhnacademy.edu.springframework.configuration;

import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("classpath:setting.properties")
@ComponentScan("com.nhnacademy.edu.springframework")
public class JavaConfig {

    @Bean
    public DoorayHookSender doorayHookSender(RestTemplate restTemplate, @Value("${hookUrl}") String hookUrl) {
        return new DoorayHookSender(restTemplate, hookUrl);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}


