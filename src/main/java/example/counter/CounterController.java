package example.counter;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;


import io.swagger.annotations.ApiOperation;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/add")
public class CounterController {

    private CounterService counterService;

    // Resteasy requires also an empty constructor for constructor injection to work
    // https://issues.jboss.org/browse/RESTEASY-1538
    public CounterController() {}

    @Inject
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @POST
    @ApiOperation(value = "add two numbers together", response = CounterResult.class)
    public void add(@Suspended final AsyncResponse asyncResponse, @Valid
            CounterRequest counterRequest) {
        asyncResponse.resume(counterService.count(counterRequest));
    }
}
