package ru.grigrar.forum.forumweb.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grigrar.forum.forumweb.model.Theme;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    List<Object> findAllById(Integer themeId);
}