package ru.grigrar.forum.forumweb.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest") // url
@Configuration
public class RestApplicationConfig  extends ResourceConfig {

    public RestApplicationConfig(){
        super();
        packages("ru.grigrar.forum.forumweb.resource");
    }
}


//TODO Maping