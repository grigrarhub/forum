package ru.grigrar.forum.forumweb.service;

import ru.grigrar.forum.forumweb.model.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {

    Theme saveThem(Theme theme);
    List<Theme> getThemes();
    Object deleteTheme(Integer id);
    Theme editTheme(Theme theme);
    Theme getTheme(Integer id);

}
