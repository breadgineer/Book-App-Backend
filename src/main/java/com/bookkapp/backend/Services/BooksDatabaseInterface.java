package com.bookkapp.backend.Services;


import com.bookkapp.backend.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksDatabaseInterface extends MongoRepository<Book, String> {
}
