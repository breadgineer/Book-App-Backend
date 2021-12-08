package com.bookkapp.backend;

import com.bookkapp.backend.Services.BooksActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class BookAppBackendApplication {



    public static void main(String[] args) {
        SpringApplication.run(BookAppBackendApplication.class, args);
    }

}
