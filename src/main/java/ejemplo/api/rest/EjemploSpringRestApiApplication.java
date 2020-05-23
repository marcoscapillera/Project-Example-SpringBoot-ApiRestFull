package ejemplo.api.rest;

import javax.persistence.Entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"ejemplo.api.rest.model"})
@ComponentScan(basePackages = {"ejemplo.*"})
@EnableJpaRepositories(basePackages = {"ejemplo.api.rest.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
@EnableCaching
public class EjemploSpringRestApiApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(EjemploSpringRestApiApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
	
	
	/*Mapea Global que relfeja en todo el sistema*/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/usuario/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		
		/*Se libera el mapeo de usuarios para todas las orginenes*/
		
		
		
	}

}
