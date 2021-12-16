package com.bookkapp.backend.Services.Users;

import com.bookkapp.backend.model.User;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
@Service
public class UserActions {

    UserDatabaseInterface userDatabase;
    public UserActions(UserDatabaseInterface userDatabase) {
        this.userDatabase = userDatabase;
    }

    // method to list all userDatabase from db
    public List<User> getAllUsers() {
        return userDatabase.findAll();
    }

    // method to list a user by id
    public Optional<User> getUserByID(String id) {
        System.out.println(userDatabase.findById(id));
        return userDatabase.findById(id);
    }

    // method to add a user
    public User addUser(User user) {
        userDatabase.save(user);
        return user;
    }
    
    // method to add a superuser
    public User addSuperUser(User user) {
        userDatabase.save(user);
        return user;
    }

    // method to update a user
    // not required by now
//    public void updateUser(User user) {
//        userDatabase.deleteByName(user.get_name());
//        userDatabase.save(user);
//    }

    // method to delete a user
    public void deleteUser(String id) {
        userDatabase.deleteById(id);
        System.out.println("User has been deleted.");
    }

}
