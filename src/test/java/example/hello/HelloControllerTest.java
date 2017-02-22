package example.hello;

import io.github.binout.jaxrsunit.JaxrsResource;
import io.github.binout.jaxrsunit.JaxrsResponse;
import io.github.binout.jaxrsunit.JaxrsUnit;
import org.junit.Before;
import org.junit.Test;
import io.github.binout.jaxrsunit.JaxrsServer;

import javax.ws.rs.core.Response;

import static io.github.binout.jaxrsunit.resteasy.ResteasyResponseTestHelper.isStatus;
import static org.fest.assertions.Assertions.assertThat;

public class HelloControllerTest {
    private JaxrsServer server;

    @Before
    public void init() {
        server = JaxrsUnit.newServer(HelloController.class);
    }

    @Test
    public void testHello() {
        JaxrsResource resource = server.resource("/");

        JaxrsResponse response = resource.get();

        assertThat(response.ok()).isTrue();
        assertThat(response.content()).isEqualTo("Greetings from Wildfly Swarm!");
    }
    @Test
    public void testClientProblem() {
        JaxrsResource resource = server.resource("/400");

        JaxrsResponse response = resource.get();

        assertThat(isStatus(response, Response.Status.BAD_REQUEST)).isTrue();
        assertThat(response.content()).isEqualTo("Bad request, says Wildfly Swarm!");
    }
    @Test
    public void testServerProblem() {
        JaxrsResource resource = server.resource("/500");

        JaxrsResponse response = resource.get();

        assertThat(isStatus(response, Response.Status.INTERNAL_SERVER_ERROR)).isTrue();
        assertThat(response.content()).isEqualTo("Internal error from Wildfly Swarm!");
    }

}
