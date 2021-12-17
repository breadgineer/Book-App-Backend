package com.bookkapp.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.bookkapp.backend.config"})
@ComponentScan({"com.bookkapp.backend.Controller"})
@ComponentScan({"com.bookkapp.backend.Services"})
@EntityScan({"com.bookkapp.backend.model"})
@EnableMongoRepositories({"com.bookkapp.backend.Repository"})

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BookAppBackendApplication.class);
    }
}
