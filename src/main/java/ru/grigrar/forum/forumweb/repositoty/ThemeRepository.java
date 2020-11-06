package ru.grigrar.forum.forumweb.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grigrar.forum.forumweb.model.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {
}
