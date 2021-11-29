package packages.returninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** This class configures the CORS Applications for the back end server. **/
@SpringBootApplication
public class RestServiceCorsApplications {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceCorsApplications.class, args);
    }

    // Allow the ec2 instance to access this spring-boot application. This is mandatory as every ping to the
    // server endpoints is processed on the ec2 instance.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/database").allowedOrigins(System.getenv("SERVER_ROOT"));
                registry.addMapping("/traderauto-plus").allowedOrigins(System.getenv("SERVER_ROOT"));
                registry.addMapping("/signin").allowedOrigins(System.getenv("SERVER_ROOT"));
            }
        };
    }

}