package com.alexeinunez.dropwizard.swagger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/swagger")
@Produces({MediaType.TEXT_HTML})
public class SwaggerResource {

    private final SwaggerView view;

    public SwaggerResource(SwaggerView view) {
        this.view = view;
    }

    @GET
    public SwaggerView get() {
        return view;
    }

}
