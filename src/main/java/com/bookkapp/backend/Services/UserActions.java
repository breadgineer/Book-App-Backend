package com.bookkapp.backend.Services;

import com.bookkapp.backend.Repository.RoleInterface;
import com.bookkapp.backend.Repository.UserDatabaseInterface;
import com.bookkapp.backend.model.Role;
import com.bookkapp.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@EnableMongoRepositories
@Service
public class UserActions implements UserDetailsService {

    @Autowired
    private UserDatabaseInterface userDatabaseInterface;

    @Autowired
    private RoleInterface roleInterface;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserActions() {}

    public User findUserByEmail(String userEmail) {
        return userDatabaseInterface.findByUserEmail(userEmail);
    }

    public void saveUser(User user) {
        user.setUserPwd(bCryptPasswordEncoder.encode(user.getUserPwd()));
        Role userRole = roleInterface.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userDatabaseInterface.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User user = userDatabaseInterface.findByUserEmail(userEmail);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPwd(), authorities);
    }


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
