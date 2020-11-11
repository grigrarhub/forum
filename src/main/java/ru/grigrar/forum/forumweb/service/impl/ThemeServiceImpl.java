package ru.grigrar.forum.forumweb.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.repositoty.ThemeRepository;
import ru.grigrar.forum.forumweb.repositoty.TopicRepository;
import ru.grigrar.forum.forumweb.service.ThemeService;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public Theme saveThem(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public List<Theme> getThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Object deleteTheme(Integer id) {
        themeRepository.deleteById(id);
        return null;
    }

    @Override
    public Theme editTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme getTheme(Integer id) {
        Optional<Theme> theme = themeRepository.findById(id);
        if (theme.isPresent()) {
            return theme.get();
        }
        return null;
    }
}
