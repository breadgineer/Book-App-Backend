package com.bookkapp.backend;

import com.bookkapp.backend.Repository.RoleInterface;
import com.bookkapp.backend.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Bean
    CommandLineRunner init(RoleInterface roleInterface) {

        return args -> {

            Role adminRole = roleInterface.findByRole("ADMIN");
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setRole("ADMIN");
                roleInterface.save(newAdminRole);
            }

            Role userRole = roleInterface.findByRole("USER");
            if (userRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole("USER");
                roleInterface.save(newUserRole);
            }
        };
    }
}