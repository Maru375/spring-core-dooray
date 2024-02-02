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

    @Mock
    private DoorayHookSender doorayHookSender;

    @Mock
    private User user;

    private String message;

    @InjectMocks
    private DoorayMessageSender doorayMessageSender;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(user.getUserName()).thenReturn("testUser");
        when(user.getUserNumber()).thenReturn("testUserNum");
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

