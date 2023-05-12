package com.example.demo.service;

import com.example.demo.entities.*;
import com.example.demo.repo.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findByReceiveEmail(String email) {
        return messageRepository.findByReceiveId(email);
    }
    
    public List<Message> findBySendEmail(String email) {
        return messageRepository.findBySendId(email);
    }
    
    public Message save(Message message) {
    	return messageRepository.save(message);
    }
}
