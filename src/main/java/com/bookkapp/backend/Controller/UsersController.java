package com.bookkapp.backend.Controller;

import com.bookkapp.backend.Services.UserActions;
import com.bookkapp.backend.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST API built for handling CR(U*)D operations of the user database
//*Updating not implemented
@RestController
@RequestMapping("/api/users")
public class UsersController {

    UserActions userActions;
    public UsersController(UserActions userActions) {
        this.userActions = userActions;
//        Hard coded users for demo puproses only
//        this.userActions.addUser( new User("1", "Viliam.Williams@testemail.sk", "fasdf%%", "superuser"));
//        this.userActions.addUser( new User("2", "Jakob.Perez@testemail.sk", "sdf543%%", "user"));
//        this.userActions.addUser( new User("3", "Pedro.Raj@testemail.sk", "asfw4r4", "user"));
//        this.userActions.addUser( new User("4", "Wang.Shean@testemail.sk", "sf233rASDF@%%", "user"));
    }



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
                User _user = userActions.addUser(user);
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
