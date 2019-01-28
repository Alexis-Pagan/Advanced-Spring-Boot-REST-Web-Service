package signup.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages= {"signup.service.controllers", "signup.service.error.handling", "signup.service.swagger"})
@EnableWebMvc
@EnableJpaRepositories(basePackages= {"signup.service.repository"})
@EntityScan("signup.service.models")
public class SpringMain implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringMain.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

	       registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
