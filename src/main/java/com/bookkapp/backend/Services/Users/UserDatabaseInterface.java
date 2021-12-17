package com.bookkapp.backend.Services.Users;

import com.bookkapp.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.QuerydslMongoPredicateExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDatabaseInterface extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
}
