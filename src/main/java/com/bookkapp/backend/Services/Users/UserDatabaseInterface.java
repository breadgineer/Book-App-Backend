package com.bookkapp.backend.Services.Users;

import com.bookkapp.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDatabaseInterface extends MongoRepository<User, String> {

    @Query("{userEmail: ?0}")
    Optional<User> findByUserEmail(String userEmail);
    @Query("{userPwd: ?0}")
    Optional<User> findByUserPwd(String userEmail);
    @Query("{userEmail: ?0, userPwd: ?1}")
    Optional<User> findByUserEmailAndPassword(String userEmail, String userPwd);
}
