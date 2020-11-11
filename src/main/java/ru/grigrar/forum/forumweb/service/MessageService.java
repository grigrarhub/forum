package ru.grigrar.forum.forumweb.service;

import ru.grigrar.forum.forumweb.model.Message;

import java.util.Set;

public interface MessageService {

    Message saveMessage(Message message, Integer id);
    Set<Message> getMessages(Integer id);
    Object deleteMessage(Integer id);
    void editMessage(Message message);
    Message getMessage(Integer id);

}
