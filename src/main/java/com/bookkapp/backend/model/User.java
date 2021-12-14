package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

// user model object

@Getter
@Setter
@Document("Users")
public class User {

    // declaration of items
    @MongoId
    private String _id;
    private String userEmail;
    private String userPwd;

    public User() {}

    // constructor with all arguments
    public User(String _id, String userEmail, String userPwd) {
        this._id = _id;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }
}
