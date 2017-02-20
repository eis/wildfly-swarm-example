package example;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.ws.rs.ApplicationPath;
import org.wildfly.swarm.Swarm;

@ApplicationPath("/")
@EnableSwagger2
public class Application extends javax.ws.rs.core.Application {

}