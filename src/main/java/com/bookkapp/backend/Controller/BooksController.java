package com.bookkapp.backend.Controller;


import com.bookkapp.backend.Services.BooksActions;
import com.bookkapp.backend.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BooksController {

    BooksActions booksActions;
    public BooksController(BooksActions booksActions) {
        this.booksActions = booksActions;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable("id") String id){
        Optional<Book> book = booksActions.getBookByID(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.FOUND);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        try {
            Book _book = booksActions.addBook(book);
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
        try {
            booksActions.deleteBook(id);
            return new ResponseEntity<>("removed",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @PutMapping("/{id}")
//    public ResponseEntity<HttpStatus> updateBook(@PathVariable String id) {
//        try {
//            booksActions.up()
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}