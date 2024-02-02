package com.nhnacademy.edu.springframework.sender;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.annotation.Dooray;
import com.nhnacademy.edu.springframework.domain.User;


public class DoorayMessageSender implements MessageSender{

    private final DoorayHookSender doorayHookSender;

    public DoorayMessageSender(DoorayHookSender doorayHookSender) {
        this.doorayHookSender = doorayHookSender;
    }

    @Override
    @Dooray
    public boolean sendMessage(User user, String message) {
        try {
            doorayHookSender.send(DoorayHook.builder()
                    .botName(user.getUserName() + " " + user.getUserNumber())
                    .text(message)
                    .build());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
