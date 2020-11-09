package ru.grigrar.forum.forumweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.service.ThemeService;
import ru.grigrar.forum.forumweb.service.TopicService;

import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/topics")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {
    @Autowired
    private TopicService topicService;

    @Context
    private ResourceContext context;

    @GET
    public Response getTopics() {
        return Response.ok().entity(topicService.getTopics()).build();
    }

    @Path("/{id}")
    @GET
    public Response getTopic(@PathParam("id") Integer id) {
        return Response.ok().entity(topicService.getTopic(id)).build();
    }

    @POST
    public Response saveTopic(Topic topic, @PathParam("themeId") Integer id) {
        System.out.println("Resource save topic");
        return Response.status(Response.Status.CREATED).entity(topicService.saveTopic(topic, id)).build();
    }

    @PUT
    public Response editTopic(Topic topic) {
        topicService.editTopic(topic);
        return Response.noContent().build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteTopic(@PathParam("id") Integer id) {
        return Response.noContent().entity(topicService.deleteTopic(id)).build();
    }

    @Path("/{topicId}/messages")
    public MessageResource messages() {
        return context.getResource(MessageResource.class);
    }

}
