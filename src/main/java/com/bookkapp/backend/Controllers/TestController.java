package com.bookkapp.backend.Controllers;


import com.bookkapp.backend.Services.BooksActions;
import com.bookkapp.backend.model.VirtualLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    BooksActions booksActions;

    @GetMapping(value = "/books")
    public List<VirtualLibrary> getAllBooks() {
        List<VirtualLibrary> books = booksActions.getAllBooks();
        //books.forEach(value -> System.out.println(value.getTitle()));
        return books;
    }

}
