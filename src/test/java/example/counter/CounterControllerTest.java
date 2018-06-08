package example.counter;

import io.github.binout.jaxrsunit.JaxrsResource;
import io.github.binout.jaxrsunit.JaxrsResponse;
import io.github.binout.jaxrsunit.JaxrsServer;
import io.github.binout.jaxrsunit.JaxrsUnit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.github.binout.jaxrsunit.resteasy.ResteasyResponseTestHelper.isStatus;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CounterControllerTest {
    private JaxrsServer server;

    @Before
    public void init() {
        server = JaxrsUnit.newServer(CounterController.class);
    }

    @Test
    public void testInvalidParams() {
        JaxrsResource resource = server.resource("/add");

        JaxrsResponse response = resource.post(MediaType.APPLICATION_JSON, "{\"int1\": 1}");

        assertThat(isStatus(response, Response.Status.BAD_REQUEST), is(true));
        assertThat(response.content(), is(""));
    }
    @Test
    public void testProperParams() {
        CounterController cc = new CounterController(new CounterService());

        AsyncResponse resp = mock(AsyncResponse.class);
        ArgumentCaptor<CounterResult> res = ArgumentCaptor.forClass(CounterResult.class);

        cc.add(resp, new CounterRequest(1, 2));

        verify(resp).resume(res.capture());

        assertEquals(new CounterResult(3), res.getValue());
    }
}
