package com.bookkapp.backend.Controller;

import com.bookkapp.backend.Services.Users.UserActions;
import com.bookkapp.backend.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.OperatingSystemMXBean;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

// REST API built for handling CR(U*)D operations of the user database
//*Updating not implemented
@RestController
@RequestMapping("/api/users")
public class UsersController {

    UserActions userActions;
    public UsersController(UserActions userActions) {
        this.userActions = userActions;
    }

    // Endpoint for getting a single user by its ID
    // Implementation not required for now
//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserByID(@PathVariable("id") String id) {
//        Optional<User> user = userActions.getUserByID(id);
//        System.out.println("User found.");
//        if (user.isPresent()) {
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    //    Endpoint for getting all the users from the database
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userActions.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for creating a new user
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println(user.get_id());
        try {
            String userID = user.get_id();
            if (userActions.getUserByID(userID).isEmpty()) {
                User _user = new User();
                _user.set_id(user.get_id());
                _user.setUserEmail(user.getUserEmail());
                _user.setUserPwd(user.getUserPwd());
                _user.setUserRole(user.getUserRole());
                if (_user.getUserRole().equals("superuser")) {
                    _user.setSuperUserPermission(user.getSuperUserPermission());
                } else {
                    _user.setUserPermission(user.getUserPermission());
                }
                userActions.addUser(_user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for deleting a user by its ID
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        System.out.println(id);
        try {
            userActions.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
