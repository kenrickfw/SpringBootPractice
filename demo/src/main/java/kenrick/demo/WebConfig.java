package kenrick.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all endpoints
                .allowedOrigins("http://localhost:3000")  // Allow requests from React app
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify allowed HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }
}