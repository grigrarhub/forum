package ru.grigrar.forum.forumweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grigrar.forum.forumweb.model.Message;
import ru.grigrar.forum.forumweb.repositoty.MessageRepository;
import ru.grigrar.forum.forumweb.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Object deleteMessage(Integer id) {

        messageRepository.deleteById(id);
        return null;

    }

    @Override
    public void editMessage(Message message) {
        for(Message message1:messageRepository.findAll())
            if(message.getId().equals(message1.getId()))
                saveMessage(message);
    }

    @Override
    public Message getMessage(Integer id) {
        for(Message message1:messageRepository.findAll())
            if(id.equals(message1.getId()))
                return message1;
            return null;
    }


}
