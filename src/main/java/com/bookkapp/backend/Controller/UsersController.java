package com.bookkapp.backend.Controller;

import com.bookkapp.backend.Services.Users.UserActions;
import com.bookkapp.backend.model.Password;
import com.bookkapp.backend.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// REST API built for handling CR(U*)D operations of the user database
//*Updating not implemented
@RestController
@RequestMapping("/api/users")
public class UsersController {

    UserActions userActions;
    public UsersController(UserActions userActions) {
        this.userActions = userActions;
//        Hard coded users for demo puposes only
        this.userActions.addUser( new User("634432", "admin@admin.com", "admin", "admin", new String[]{"Manage Users", "Borrow Books"}));
        this.userActions.addUser( new User("334334", "user@user.com", "user", "user", new String[]{"Borrow Books"}));
        this.userActions.addUser( new User("123415", "viliam.williams@testemail.sk", "fasdf%%", "admin", new String[]{"Manage Users", "Borrow Books"}));
        this.userActions.addUser( new User("354634", "jakob.perez@testemail.sk", "sdf543%%", "user", new String[]{"Borrow Books"}));
        this.userActions.addUser( new User("762363", "pedro.raj@testemail.sk", "asfw4r4", "user", new String[]{"Borrow Books"}));
        this.userActions.addUser( new User("465435", "wang.shean@testemail.sk", "sf233rASDF@%%", "user", new String[]{"Borrow Books"}));
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) throws Exception {
        String tempUserEmail = user.getUserEmail();
        Optional<User> _user = null;
        if (tempUserEmail != null) {
            _user = userActions.fetchUserByUserEmail(tempUserEmail);
            System.out.println(_user);
            if (_user.isPresent()) {
                return new ResponseEntity<>("User with email address " + tempUserEmail + " already exists in the system.",
                        HttpStatus.CONFLICT);
                }
            userActions.addUser(user);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) {
        String tempUserEmail = user.getUserEmail();
        String tempUserPwd = user.getUserPwd();
        Optional<User> _user = null;
        _user = userActions.fetchUserByUserEmailAndPwd(tempUserEmail, tempUserPwd);
        System.out.println(_user);
        if (!_user.isPresent()) {
            return new ResponseEntity<>("Bad request!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(_user, HttpStatus.OK);
        }
    }



    // Endpoint for creating a new user
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping
//    public ResponseEntity<String> createUser(@RequestBody User user) {
//        System.out.println(user.get_id());
//        try {
//            String userID = user.get_id();
//            if (userActions.getUserByID(userID).isEmpty()) {
//                String _user = userActions.addUser(user);
//                return new ResponseEntity<>(HttpStatus.CREATED);
//            } else {
//                return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

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

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping()
    public ResponseEntity<String> updatePassword(@RequestBody Password password) {
        Optional<User> user = userActions.getUserByPassword(password.getOldPwd());
        if (user.isPresent()) {
            User _user = user.get();
            _user.setUserPwd(password.getNewPwd());
            userActions.updateUser(_user);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
