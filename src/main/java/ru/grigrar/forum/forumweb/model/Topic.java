package ru.grigrar.forum.forumweb.model;

import ru.grigrar.forum.forumweb.repositoty.MessageRepository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // вытаскивает id
    private  Integer id;

    @Column
    @NotBlank //без пустого имени
    private String name;

    private MessageRepository messageRepository;

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
