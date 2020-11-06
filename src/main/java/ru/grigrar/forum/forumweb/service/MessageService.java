package ru.grigrar.forum.forumweb.service;

import ru.grigrar.forum.forumweb.model.Message;

import java.util.List;

public interface MessageService {

    Message saveMessage(Message message);
    List<Message> getMessages();
    Object deleteMessage(Integer id);
    void editMessage(Message message);
    Message getMessage(Integer id);

}
