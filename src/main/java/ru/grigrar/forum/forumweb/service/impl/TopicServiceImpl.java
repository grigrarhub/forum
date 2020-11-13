package ru.grigrar.forum.forumweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.repositoty.ThemeRepository;
import ru.grigrar.forum.forumweb.repositoty.TopicRepository;
import ru.grigrar.forum.forumweb.service.TopicService;

import javax.ws.rs.WebApplicationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Transactional
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ThemeRepository themeRepository;

    @Override
    // TODO прчитать что такое ACID!!
    public Topic saveTopic(Topic topic, Integer id) {
        Optional<Theme> themeOptional = themeRepository.findById(id);
        Theme theme = themeOptional.orElseThrow(WebApplicationException::new);
        theme.addTopic(topic);
        return topic;
        //new
    }

    @Override
    public Set<Topic> getTopics(Integer themeId) {
        Theme theme = themeRepository.findById(themeId).get();
        Set<Topic> topics = new HashSet<>();
        for (Topic topic : theme.getTopics())
            if (topic.getTheme().equals(theme))
                topics.add(topic);
        return topics;
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
