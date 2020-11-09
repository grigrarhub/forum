package ru.grigrar.forum.forumweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.repositoty.ThemeRepository;
import ru.grigrar.forum.forumweb.repositoty.TopicRepository;
import ru.grigrar.forum.forumweb.service.TopicService;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ThemeRepository themeRepository;

    public TopicRepository getTopicRepository() {
        return topicRepository;
    }

    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public ThemeRepository getThemeRepository() {
        return themeRepository;
    }

    public void setThemeRepository(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    @Transactional // TODO прчитать что такое ACID!!
    public Topic saveTopic(Topic topic, Integer id) {
        Optional<Theme> theme = themeRepository.findById(id);
        if (theme.isPresent()) {
            topic.setTheme(theme.get());
            topicRepository.save(topic);
            System.out.println(topic.toString() + " Service");
            System.out.println(theme.get().toString() + " Service theme");
            return topic;
        }
        return null;
    }

    @Override
    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Object deleteTopic(Integer id) {
        topicRepository.deleteById(id);
        return null;
    }

    @Override
    public void editTopic(Topic topic) {
        for (Topic topic1 : topicRepository.findAll())
            if (topic.getId().equals(topic1.getId()))
                saveTopic(topic, 1);
    }

    @Override
    public Topic getTopic(Integer id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent())
            return topic.get();
        return null;
    }
}
