package ru.grigrar.forum.forumweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ForumWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumWebApplication.class, args);
	}

}
