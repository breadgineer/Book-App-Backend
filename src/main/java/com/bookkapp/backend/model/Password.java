package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class Password {
    String oldPwd;
    String newPwd;

    public Password(String oldPwd, String newPwd) {
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
    }

    public Password(){

    }
}
