package ru.grigrar.forum.forumweb.resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.dto.MessageDto;
import ru.grigrar.forum.forumweb.model.Message;
import ru.grigrar.forum.forumweb.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/messages")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Context
    private ResourceContext context;

    @Autowired
    private MessageService messageService;
    @Autowired
    private ModelMapper modelMapper;

    @GET
    public Response getMessages(@PathParam("topicId") Integer id) {
        List<MessageDto> messageDtos = modelMapper.map(messageService.getMessages(id), new TypeToken<List<MessageDto>>(){}.getType());

        return Response
                .ok()
                .entity(messageDtos).build();
    }

    @Path("/{id}")
    @GET
    public Response getMessage(@PathParam("id") Integer id) {
        return Response.ok().entity(messageService.getMessage(id)).build();
    }

    @POST
    public Response saveMessage(MessageDto messageDto, @PathParam("topicId") Integer id) {
        Message message = modelMapper.map(messageDto,Message.class);
        message = messageService.saveMessage(message,id);
        messageDto = modelMapper.map(message, MessageDto.class);
        return Response.status(Response.Status.CREATED).entity(messageDto).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteMessage(@PathParam("id") Integer id) {
        return Response.noContent().entity(messageService.deleteMessage(id)).build();
    }

    @Path("/{id}")
    @PUT
    public Response updateMessage(Message message, @PathParam("id") Integer id) {
        message.setId(id);
        messageService.editMessage(message);
        return Response.ok().build();
    }

}
