package ru.grigrar.forum.forumweb.model;

import io.swagger.v3.oas.annotations.Hidden;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.Tables;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // вытаскивает id
    private Integer id;

    @Column
    @NotBlank //не пустое
    private String name;

    @Hidden
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
    private Set<Topic> topics = new HashSet<>();

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return id.equals(theme.id) &&
                name.equals(theme.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    // TODO топик и сообщения
}
