package ru.grigrar.forum.forumweb.service;

import ru.grigrar.forum.forumweb.model.Topic;

import java.util.Set;

public interface TopicService {
    Topic saveTopic(Topic topic, Integer id);
    Set<Topic> getTopics(Integer themeId);
    Object deleteTopic(Integer id);
    void editTopic(Topic topic);
    Topic getTopic(Integer id);
}
