package ru.grigrar.forum.forumweb.dto;

import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

public class TopicDto {

    private Integer id;
    @NotBlank
    private String name;

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
        return "TopicDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
