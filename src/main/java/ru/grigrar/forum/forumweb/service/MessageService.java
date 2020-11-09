package ru.grigrar.forum.forumweb.service;

import ru.grigrar.forum.forumweb.model.Message;
import ru.grigrar.forum.forumweb.model.Topic;

import java.util.List;

public interface MessageService {

    Message saveMessage(Message message, Integer id);
    List<Message> getMessages();
    Object deleteMessage(Integer id);
    void editMessage(Message message);
    Message getMessage(Integer id);

}
