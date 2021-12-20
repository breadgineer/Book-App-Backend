package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

//import javax.persistence.*;
import java.util.List;

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
    private String userRole;
    private String[] userPermission;
    private boolean loggedUser;

    public User() {}

    // constructor with all arguments
    public User(String _id, String userEmail, String userPwd, String userRole,
                String[] userPermission, boolean loggedUser) {
        this._id = _id;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.userPermission = userPermission;
        this.loggedUser = loggedUser;
    }
}
