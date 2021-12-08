package com.bookkapp.backend.Services;


import com.bookkapp.backend.model.VirtualLibrary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksDatabaseInterface extends MongoRepository<VirtualLibrary, String> {
}
