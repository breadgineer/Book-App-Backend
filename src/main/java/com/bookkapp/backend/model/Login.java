package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    private String userEmail;
    private String userPwd;

    public Login() {}

    public Login(String userEmail, String userPwd) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }
}
