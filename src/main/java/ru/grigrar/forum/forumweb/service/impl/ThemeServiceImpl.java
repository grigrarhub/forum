package ru.grigrar.forum.forumweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.repositoty.ThemeRepository;
import ru.grigrar.forum.forumweb.service.ThemeService;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {

    private ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository){
        this.themeRepository = themeRepository;
    }

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
        return themeRepository.getOne(id);
    }
}
