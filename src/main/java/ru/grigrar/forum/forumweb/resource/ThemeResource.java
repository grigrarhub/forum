package ru.grigrar.forum.forumweb.resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grigrar.forum.forumweb.dto.ThemeDto;
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
    @Autowired
    private ModelMapper modelMapper;


    @GET
    public Response getThemes() {
        List<Theme> themes = themeService.getThemes();
        List<ThemeDto> themeDtos = modelMapper.map(themes, new TypeToken<List<ThemeDto>>(){}.getType());
        return Response.ok().entity(themeDtos).build();
    }

    @Path("/{id}")
    @GET
    public Response getTheme(@PathParam("id") Integer id) {
        Theme theme = themeService.getTheme(id);
        ThemeDto themeDto = modelMapper.map(theme,ThemeDto.class);
        return Response.ok().entity(themeDto).build();
    }

    @POST
    public Response saveTheme(ThemeDto themeDto) {
            Theme theme = modelMapper.map(themeDto,Theme.class);
        return Response.status(Response.Status.CREATED).entity(themeService.saveThem(theme)).build();
    }

    @Path("/{themeId}")
    @DELETE
    public Response deleteTheme(@PathParam("themeId") Integer id) {
        return Response.noContent().entity(themeService.deleteTheme(id)).build();
    }

    @PUT
    public Response updateTheme(ThemeDto themeDto) {
        Theme theme = modelMapper.map(themeDto,Theme.class);
        return Response.noContent().entity(themeService.editTheme(theme)).build();
    }
    //TODO  метод PUT
    //TODO метод возврата по ИД

    @Path("/{themeId}/topics")
    public TopicResource topics() {
        return context.getResource(TopicResource.class);
    }
}
