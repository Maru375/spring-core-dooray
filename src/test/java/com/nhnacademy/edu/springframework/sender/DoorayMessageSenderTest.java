package com.nhnacademy.edu.springframework.sender;

import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DoorayMessageSenderTest {

    private User user;
    private String message;

    @Mock
    private DoorayHookSender doorayHookSender;
    @InjectMocks
    private DoorayMessageSender doorayMessageSender;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        user = new User("testUser", "testUserNum");
        message = "테스트 메시지";
    }

    @Test
    void testSendMessageSuccessfully() {
        doNothing().when(doorayHookSender).send(any());

        boolean result = doorayMessageSender.sendMessage(user, message);

        verify(doorayHookSender).send(any());

        Assertions.assertTrue(result);
    }

    @Test
    void testSendMessageWithError() {
        doThrow(new RuntimeException()).when(doorayHookSender).send(any());

        boolean result = doorayMessageSender.sendMessage(user, message);

        verify(doorayHookSender).send(any());

        Assertions.assertFalse(result);
    }
}

