package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

//import javax.persistence.*;
import java.util.List;
import java.util.Set;

// user model object

@Document("Users")
public class User {

    // declaration of items
    @MongoId
    private String _id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String userEmail;
    private String userPwd;
    @DBRef
    private Set<Role> roles;

    public User(String _id, String userEmail, String userPwd, Set<Role> roles) {
        this._id = _id;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.roles = roles;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

