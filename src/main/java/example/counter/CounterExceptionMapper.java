package example.counter;

import static java.util.Collections.singletonMap;

import java.util.Map;


import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class CounterExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable t) {

        log.error("Request processing failed", t);

        return Response.serverError()
                .entity(singletonMap("message", "internal_error"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
