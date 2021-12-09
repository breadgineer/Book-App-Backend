package com.bookkapp.backend.Services;


import com.bookkapp.backend.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// interface to communication with database
@Repository
public interface BooksDatabaseInterface extends MongoRepository<Book, String> {
}
