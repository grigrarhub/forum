package ru.grigrar.forum.forumweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.model.Message;
import ru.grigrar.forum.forumweb.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/theme/message")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    private MessageService messageService;

    @Autowired
    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GET
    public Response getMessages(){
        return Response.ok().entity(messageService.getMessages()).build();
    }

    @Path("/{id}")
    @GET
    public Response getMessage(@PathParam("id") Integer id){

        return Response.ok().entity(messageService.getMessage(id)).build();
    }

    @POST
    public Response saveMessage(Message message){
        return Response.status(Response.Status.CREATED).entity(messageService.saveMessage(message)).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteMessage(@PathParam("id") Integer id){
        return Response.noContent().entity(messageService.deleteMessage(id)).build();
    }

    @PUT
    public Response updateMessage(Message message){
        messageService.editMessage(message);
        return  Response.noContent().build();
    }
}
