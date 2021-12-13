package com.bookkapp.backend.Services;

import com.bookkapp.backend.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// class to serve the model object
@EnableMongoRepositories
@Service
public class BooksActions {

    BooksDatabaseInterface books;

    public BooksActions(BooksDatabaseInterface books) {
        this.books = books;
    }

// method to list all books from db
    public List<Book> getAllBooks() {
        return books.findAll();
    }

// method to list a book by id
    public Optional<Book> getBookByID(String id) {
        System.out.println(books.findById(id));
        return books.findById(id);
    }

// method to add a book
    public Book addBook(Book book) {
        books.save(book);
        return book;
    }

// method to delete a book
    public void deleteBook(String id) {
        books.deleteById(id);
        System.out.println("book was delete");
    }

}