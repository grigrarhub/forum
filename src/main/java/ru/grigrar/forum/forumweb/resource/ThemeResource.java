package ru.grigrar.forum.forumweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.model.Theme;
import ru.grigrar.forum.forumweb.service.ThemeService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/themes")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ThemeResource {

    @Context
    private ResourceContext context;
    @Autowired
    private ThemeService themeService;

    @GET
    public Response getThemes() {
        List<Theme> themes = themeService.getThemes();
        System.out.println(themes.toString() + "fwefffffff");
        return Response.ok().entity(themes).build();
    }

    @Path("/{id}")
    @GET
    public Response getTheme(@PathParam("id") Integer id) {
        Theme theme = themeService.getTheme(id);
        return Response.ok().entity(theme).build();
    }

    @POST
    public Response saveTheme(Theme theme) {
        return Response.status(Response.Status.CREATED).entity(themeService.saveThem(theme)).build();
    }

    @Path("/{themeId}")
    @DELETE
    public Response deleteTheme(@PathParam("themeId") Integer id) {
        return Response.noContent().entity(themeService.deleteTheme(id)).build();
    }

    @PUT
    public Response updateTheme(Theme theme) {
        return Response.noContent().entity(themeService.editTheme(theme)).build();
    }
    //TODO  метод PUT
    //TODO метод возврата по ИД

    @Path("/{themeId}/topics")
    public TopicResource topics() {
        return context.getResource(TopicResource.class);
    }
}
