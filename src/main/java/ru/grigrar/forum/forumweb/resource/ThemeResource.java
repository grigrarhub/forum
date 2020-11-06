package ru.grigrar.forum.forumweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.service.ThemeService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/themes")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ThemeResource {

    private ThemeService themeService;

    @Autowired
    public ThemeResource(ThemeService themeService){
        this.themeService=themeService;
    }


    @GET
    public Response getThemes(){
        return Response.ok().entity(themeService.getThemes()).build();
    }

    @Path("/{id}")
    @GET
    public Response getTheme(@PathParam("id") Integer id){
        return Response.ok().entity(themeService.getTheme(id)).build();
    }

    @POST
    public Response saveTheme(Theme theme){
            return Response.status(Response.Status.CREATED).entity(themeService.saveThem(theme)).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteTheme(@PathParam("id") Integer id){
        return Response.noContent().entity(themeService.deleteTheme(id)).build();
    }

    @PUT
    public Response updateTheme(Theme theme){
        return  Response.noContent().entity(themeService.editTheme(theme)).build();
    }
    //TODO  метод PUT
    //TODO метод возврата по ИД

}
