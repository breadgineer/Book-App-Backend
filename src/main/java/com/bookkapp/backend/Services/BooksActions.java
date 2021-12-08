package com.bookkapp.backend.Services;

import com.bookkapp.backend.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
@Service
public class BooksActions {

    BooksDatabaseInterface books;

    public BooksActions(BooksDatabaseInterface books) {
        this.books = books;
    }

    public List<Book> getAllBooks() {
        return books.findAll();
    }

    public Optional<Book> getBookByID(String id) {
        return books.findById(id);
    }

    public Book addBook(Book book) {
        books.save(book);
        return book;
    }

    public void deleteBook(String id) {
        books.deleteById(id);
    }

}