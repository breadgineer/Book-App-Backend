package com.bookkapp.backend.Services;

import com.bookkapp.backend.model.VirtualLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public class BooksActions {

    @Autowired
    BooksDatabaseInterface books;

    public List<VirtualLibrary> getAllBooks() {
        return books.findAll();
    }

    public Optional<VirtualLibrary> getBookByID(String id) {
        return books.findById(id);
    }

    public String addBook(VirtualLibrary bookItem) {
        books.save(bookItem);
        return "Book with ID: " + bookItem.getId() + " was succesfully added to database";
    }

    public String deleteBook(String id) {
        books.deleteById(id);
        return "Book with ID: " + id + " was succesfully removed from database";
    }

}
