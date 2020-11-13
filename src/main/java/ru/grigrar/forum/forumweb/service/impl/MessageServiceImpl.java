package ru.grigrar.forum.forumweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grigrar.forum.forumweb.model.Message;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.repositoty.MessageRepository;
import ru.grigrar.forum.forumweb.repositoty.TopicRepository;
import ru.grigrar.forum.forumweb.service.MessageService;

import javax.ws.rs.WebApplicationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Message saveMessage(Message message, Integer id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        Topic topic = topicOptional.orElseThrow(WebApplicationException::new);
        topic.addMessage(message);
        return message;
    }

    @Override
    public Set<Message> getMessages(Integer id) {
        Topic topic = topicRepository.findById(id).get();
        Set<Message> messages = new HashSet<>();
        for (Message message : messageRepository.findAll())
            if (message.getTopic().equals(topic))
                messages.add(message);
        return messages;
    }

    @Override
    public Object deleteMessage(Integer id) {

        messageRepository.deleteById(id);
        return null;

    }

    @Override
    // TODO прчитать что такое ACID!!
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
