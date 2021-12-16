package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowBook {

    private String userId;
    private String username;
    private String dateOfBorrow;
    private String dateOfReturn;

    public BorrowBook(String userId, String username, String dateOfBorrow, String dateOfReturn) {
        this.userId = userId;
        this.username = username;
        this.dateOfBorrow = dateOfBorrow;
        this.dateOfReturn = dateOfReturn;
    }
}
