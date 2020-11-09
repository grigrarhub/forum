package ru.grigrar.forum.forumweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grigrar.forum.forumweb.model.Message;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.repositoty.MessageRepository;
import ru.grigrar.forum.forumweb.repositoty.TopicRepository;
import ru.grigrar.forum.forumweb.service.MessageService;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Message saveMessage(Message message, Integer id) {
        message.setTopic(topicRepository.findById(id).get());
        messageRepository.save(message);
        System.out.println(message.toString());
        return message;
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
    @Transactional // TODO прчитать что такое ACID!!
    public void editMessage(Message message) {
        Optional<Message> optionalMessage = messageRepository.findById(message.getId());
        if (optionalMessage.isPresent()) {
            Message message1 = optionalMessage.get();
            message1.setName(message.getName());
        }
    }

    @Override
    public Message getMessage(Integer id) {
        for (Message message1 : messageRepository.findAll())
            if (id.equals(message1.getId()))
                return message1;
        return null;
    }
}
