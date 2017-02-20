package example.counter;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;

@Slf4j
@Provider
public class CounterClientExceptionMapper implements ExceptionMapper<ClientErrorException> {
    @Override
    public Response toResponse(ClientErrorException e) {

        log.warn("We got an exception: " + e);

        return Response.status(e.getResponse().getStatus())
                .entity(singletonMap("message", e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
