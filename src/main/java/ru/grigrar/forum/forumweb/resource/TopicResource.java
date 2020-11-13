package ru.grigrar.forum.forumweb.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.dto.ThemeDto;
import ru.grigrar.forum.forumweb.dto.TopicDto;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.service.ThemeService;
import ru.grigrar.forum.forumweb.service.TopicService;

import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Path("/topics")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ModelMapper modelMapper;
    @Context
    private ResourceContext context;

    @GET
    public Response getTopics(@PathParam("themeId") Integer themeId) {
        List<TopicDto> topicDtos = modelMapper.map(topicService.getTopics(themeId), new TypeToken<List<ThemeDto>>() {
        }.getType());
        return Response.ok().entity(topicDtos).build();
    }

    @Path("/{id}")
    @GET
    public Response getTopic(@PathParam("id") Integer id) {
        TopicDto topicDto = modelMapper.map(topicService.getTopic(id), TopicDto.class);
        return Response.ok().entity(topicDto).build();
    }

    @POST
    public Response saveTopic(TopicDto topicDto, @PathParam("themeId") Integer id) {
        Topic topic = modelMapper.map(topicDto, Topic.class);
        topic = topicService.saveTopic(topic, id);
        topicDto = modelMapper.map(topic, TopicDto.class);
        return Response.status(Response.Status.CREATED).entity(topicDto).build();
    }

    @PUT
    public Response editTopic(TopicDto topicDto) {
        Topic topic = modelMapper.map(topicDto, Topic.class);
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
