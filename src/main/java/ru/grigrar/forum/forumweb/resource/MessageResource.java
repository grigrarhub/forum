package ru.grigrar.forum.forumweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.model.Message;
import ru.grigrar.forum.forumweb.model.Topic;
import ru.grigrar.forum.forumweb.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/messages")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Context
    private ResourceContext context;

    @Autowired
    private MessageService messageService;

    @GET
    public Response getMessages() {
        List<Message> messages = messageService.getMessages();
        return Response
                .ok()
                .entity(messages).build();
    }

    @Path("/{id}")
    @GET
    public Response getMessage(@PathParam("id") Integer id) {
        return Response.ok().entity(messageService.getMessage(id)).build();
    }

    @POST
    public Response saveMessage(Message message, @PathParam("topicId") Integer id) {
        return Response.status(Response.Status.CREATED).entity(messageService.saveMessage(message, id)).build();
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
