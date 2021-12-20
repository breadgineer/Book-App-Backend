package com.bookkapp.backend.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBook implements Serializable {

    private String username;
    private String bookID;


}
