package com.bookkapp.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;

public class BooksActions {

    @Autowired
    BooksDatabaseInterface books;

    public BookItem[] getAllBooks() {
        return books.findAll();
    }

    public BookItem getBookByID(String id) {
        return books.findById(id);
    }

    public String addBook(BookItem bookItem) {
        books.save(bookItem);
        return "Book with ID: " + bookItem.id + " was succesfully added to database";
    }

    public String deleteBook(String id) {
        books.deleteById(id);
        return "Book with ID: " + id + " was succesfully removed from database";
    }

}
