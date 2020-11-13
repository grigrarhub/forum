package ru.grigrar.forum.forumweb.model;

import io.swagger.v3.oas.annotations.Hidden;
import ru.grigrar.forum.forumweb.repositoty.MessageRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // вытаскивает id
    private Integer id;

    @Column
    @NotBlank //без пустого имени
    private String name;

    @Hidden
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @Hidden
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "topic")
    private Set<Message> messages = new HashSet<>();

    public Set<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addMessage(Message message) {
        message.setTopic(this);
        this.getMessages().add(message);
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id.equals(topic.id) &&
                name.equals(topic.name);
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
}
