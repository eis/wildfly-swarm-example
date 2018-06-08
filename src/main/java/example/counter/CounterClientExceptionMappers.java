package example.counter;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;

public class CounterClientExceptionMappers {
    @Slf4j
    @Provider
    public static class JsonExceptionMapper implements ExceptionMapper<JsonProcessingException> {

        @Override
        public Response toResponse(JsonProcessingException e) {

            log.warn("not able to process json: " + e);

            return Response.status(400)
                    .entity(singletonMap("message", "json payload was not what was expected"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Slf4j
    @Provider
    public static class ClientErrorMapper implements ExceptionMapper<ClientErrorException> {
        @Override
        public Response toResponse(ClientErrorException e) {

            log.warn("We got an exception: " + e);

            return Response.status(e.getResponse().getStatus())
                    .entity(singletonMap("message", e.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Slf4j
    @Provider
    public static class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

        @Override
        public Response toResponse(ConstraintViolationException e) {

            log.warn("constraint violated " + e);

            return Response.status(400)
                    .entity(singletonMap("message", "constraint violation"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

}
