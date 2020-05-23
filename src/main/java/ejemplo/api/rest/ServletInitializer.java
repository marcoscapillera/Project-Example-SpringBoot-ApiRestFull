package ejemplo.api.rest;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import ejemplo.api.rest.EjemploSpringRestApiApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

	return application.sources(EjemploSpringRestApiApplication.class);
	

}
}