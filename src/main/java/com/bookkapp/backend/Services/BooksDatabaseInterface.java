package com.bookkapp.backend.Services;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksDatabaseInterface implements MongoRepository<BookItem, String> {
}
