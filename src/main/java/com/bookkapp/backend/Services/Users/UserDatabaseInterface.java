package com.bookkapp.backend.Services.Users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDatabaseInterface extends MongoRepository<User, String> {
}
