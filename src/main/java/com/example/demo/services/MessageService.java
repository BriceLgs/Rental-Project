package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Message;
import com.example.demo.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByRentalId(Long rentalId) {
        return messageRepository.findByRentalId(rentalId);
    }

    // Ajoutez d'autres méthodes selon vos besoins
} 