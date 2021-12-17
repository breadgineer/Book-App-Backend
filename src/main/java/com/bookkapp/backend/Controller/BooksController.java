package com.bookkapp.backend.Controller;


import com.bookkapp.backend.Services.BooksActions;
import com.bookkapp.backend.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// REST API built for handling CR(U*)D operations of the book database
//*Updating not implemented
@RestController
@RequestMapping("/api/books") //main API endpoint
public class BooksController {

//    bookActions service injection into the controller
    @Autowired
    BooksActions booksActions;
    public BooksController(BooksActions booksActions) {
        this.booksActions = booksActions;
    }

//    Endpoint for getting a single book by its ID
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable("id") String id){
        Optional<Book> book = booksActions.getBookByID(id);
        System.out.println("dostal som knihu");
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    Endpoint for getting all the books in the database
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        try{
            List<Book> books = booksActions.getAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Endpoint for getting a single book by its ID
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
        System.out.println(id);
        try {
            booksActions.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Endpoint for posting a new book. Data should be sent as a JSON formatted
//    as the model described on line 14
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<String> postBook(@RequestBody Book book) {
        System.out.println(book.get_id());
        try {
            String bookID = book.get_id();
            if (booksActions.getBookByID(bookID).isEmpty()){
                Book _book = booksActions.addBook(book);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("The book already exists", HttpStatus.CONFLICT);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping
    public ResponseEntity<String> updateBook(@RequestBody Book bookData) {
        Optional<Book> book = booksActions.getBookByID(bookData.get_id());
        if (book.isPresent()) {
            Book _book = book.get();
            _book.set_id(bookData.get_id());
            _book.setTitle(bookData.getTitle());
            _book.setAuthors(bookData.getAuthors());
            _book.setLongDescription(bookData.getLongDescription());
            _book.setPublishedDate(bookData.getPublishedDate());
            booksActions.updateBook(_book);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}