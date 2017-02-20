package example.hello;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@ApplicationScoped
public class HelloController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        return "Greetings from Wildfly Swarm!";
    }

    @GET
    @Path("/{path: 500.*}")
    public Response internalError() {
        return Response.serverError()
                .entity("Internal error from Wildfly Swarm!")
                .build();
    }

    @GET
    @Path("/{path: 400.*}")
    public Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Bad request, says Wildfly Swarm!")
                .build();
    }
}