package com.bookkapp.backend;

import com.bookkapp.backend.Services.Users.UserDatabaseInterface;
import com.bookkapp.backend.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Array;
import java.util.Optional;


@Configuration
@EnableWebMvc
class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}


@SpringBootApplication
public class BookAppBackendApplication {

    // main method of the application
    public static void main(String[] args) {
        SpringApplication.run(BookAppBackendApplication.class, args);
    }
}