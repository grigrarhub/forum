package ru.grigrar.forum.forumweb.service;

import ru.grigrar.forum.forumweb.model.Topic;

import java.util.List;

public interface TopicService {
    Topic saveTopic(Topic topic, Integer id);
    List<Topic> getTopics();
    Object deleteTopic(Integer id);
    void editTopic(Topic topic);
    Topic getTopic(Integer id);
}
