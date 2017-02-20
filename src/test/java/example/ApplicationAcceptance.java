package example;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Arquillian.class)
public class ApplicationAcceptance {
    @Drone
    HtmlUnitDriver browser;

    @Test
    public void testIt() {
        browser.navigate().to("http://localhost:8080/");
        assertThat(browser.getPageSource()).contains("Greetings from Wildfly Swarm!");
    }
}
