package be.mister.m.controllers;

import com.sun.jersey.api.view.Viewable;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by marc on 03/02/15.
 */
@Path("/")
@Component
public class HomeController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response index() {
        return Response.status(Response.Status.OK).entity(new Viewable("index")).build();
    }
}
