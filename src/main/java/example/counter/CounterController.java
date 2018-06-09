package example.counter;

import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/add")
@Singleton
public class CounterController {

    private final CounterService counterService;

    // Resteasy/CDI requires @Context+@Singleton for constructor injection to work
    // https://issues.jboss.org/browse/RESTEASY-1538
    // http://stackoverflow.com/a/33658338/365237

    @Inject
    public CounterController(@Context CounterService counterService) {
        this.counterService = counterService;
    }

    @POST
    @ApiOperation(value = "add two numbers together", response = CounterResult.class)
    public void add(@Suspended final AsyncResponse asyncResponse, @Valid
            CounterRequest counterRequest) {
        asyncResponse.resume(counterService.count(counterRequest));
    }
}
