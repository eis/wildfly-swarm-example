package io.github.binout.jaxrsunit.resteasy;

import io.github.binout.jaxrsunit.JaxrsResponse;
import lombok.SneakyThrows;
import org.jboss.resteasy.mock.MockHttpResponse;

import javax.ws.rs.core.Response;
import java.lang.reflect.Field;

public class ResteasyResponseTestHelper {

    public static boolean isStatus(JaxrsResponse response, Response.Status status) {
        return ((RestEasyResponse)response).isStatus(status);
    }
    public static Response.Status status(JaxrsResponse response) {
        return Response.Status.fromStatusCode(
                getActualResponse(response).getStatus());
    }
    @SneakyThrows
    private static MockHttpResponse getActualResponse(JaxrsResponse jaxrsResponse) {
        RestEasyResponse restEasyResponse = (RestEasyResponse)jaxrsResponse;
        Field f = restEasyResponse.getClass().getDeclaredField("mockResponse");
        f.setAccessible(true);
        return (MockHttpResponse)f.get(restEasyResponse);
    }
}
