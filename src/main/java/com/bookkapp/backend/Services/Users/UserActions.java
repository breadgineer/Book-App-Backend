import com.bookkapp.backend.Services.Users.UserDatabaseInterface;
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
    // method to update a user
    public void updateUser(User user) {
        userDatabase.deleteById(user.get_id());
        userDatabase.save(user);
    }

    // method to delete a user
    public void deleteUser(String id) {
        userDatabase.deleteById(id);
        System.out.println("user was delete");
    }

}
