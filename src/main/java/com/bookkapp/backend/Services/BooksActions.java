package com.bookkapp.backend.Services;

import com.bookkapp.backend.model.VirtualLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
@Service
public class BooksActions {

    @Autowired
    BooksDatabaseInterface books;

    public List<VirtualLibrary> getAllBooks() {
        return books.findAll();
    }

    public Optional<VirtualLibrary> getBookByID(int id) {
        return books.findById(id);
    }

    public String addBook(VirtualLibrary bookItem) {
        books.save(bookItem);
        return "Book with ID: " + bookItem.get_id() + " was succesfully added to database";
    }

    public String deleteBook(int id) {
        books.deleteById(id);
        return "Book with ID: " + id + " was succesfully removed from database";
    }

}
