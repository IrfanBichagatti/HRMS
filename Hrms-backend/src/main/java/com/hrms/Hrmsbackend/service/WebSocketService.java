package com.hrms.Hrmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(final SimpMessagingTemplate massagingTemplate){
        this.messagingTemplate=massagingTemplate;
    }

    public void sendMessage(final String topicSuffix){//here in the payload u can send the list
        messagingTemplate.convertAndSend("/topic/"+topicSuffix,"Default message from our ws services");
    }

}
