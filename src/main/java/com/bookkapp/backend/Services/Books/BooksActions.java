package com.bookkapp.backend.Services.Books;

import com.bookkapp.backend.model.Book;
import com.bookkapp.backend.model.BorrowBook;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return books.findById(id);
    }

    // method to add a book
    public Book addBook(Book book) {
        books.save(book);
        return book;
    }
    // method to update a book
    public void updateBook(Book book) {
        books.deleteById(book.get_id());
        books.save(book);
    }

    // method to delete a book
    public void deleteBook(String id) {
        books.deleteById(id);
    }

    //method to borrow a book
    public Optional<Book> borrowBook(Book book) {
        String id = book.get_id();
        Optional<Book> _book = books.findById(id);
        if (_book.isPresent()) {
            if (_book.get().getAvailableCopies() > 0) {
                _book.get().setAvailableCopies(_book.get().getAvailableCopies() - 1);
                _book.get().setBorrowBookDetails(book.getBorrowBookDetails());
                if (_book.get().getAvailableCopies() == 0) {
                    _book.get().setBorrow(true);
                }
                books.deleteById(id);
                books.save(_book.get());
            }
        }
        return books.findById(id);
    }

    public Optional<Book> returnBook(String userName, String id) {
        Optional<Book> _book = books.findById(id);
        if (_book.isPresent()) {
            for (int i=0; i<_book.get().getBorrowBookDetails().length; i++) {
                if (_book.get().getBorrowBookDetails()[i].getUsername().equals(userName)) {
                    _book.get().setBorrowBookDetails(ArrayUtils.remove(_book.get().getBorrowBookDetails(), i));
                    _book.get().setAvailableCopies(_book.get().getAvailableCopies() + 1);
                    if (_book.get().getAvailableCopies() != 0) {
                        _book.get().setBorrow(false);
                    }
                    break;
                }
            }

            books.deleteById(id);
            books.save(_book.get());
        }
        return books.findById(id);
    }

}