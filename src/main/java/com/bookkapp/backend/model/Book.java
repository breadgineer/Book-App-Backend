package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

// model object

@Getter
@Setter
@Document("books")
public class Book {

    // declaration of items
    @MongoId
    private String _id;
    private String title;
    private Date publishedDate;
    private String shortDescription;
    private String longDescription;
    private String[] authors;
    private boolean isBorrow = false;
    private int copies;
    private int availableCopies;
    private BorrowBook[] borrowBookDetails;
    //    private String[] categories;
    //    private String isbn;
    //    private int pageCount;
    //    private String thumbnailUrl;
    //    private String status;



    public Book() {
    }

    // constructor with all arguments
    public Book(String _id,
                String title,
                Date publishedDate,
                String shortDescription,
                String longDescription,
                String[] authors,
                int copies
//                String[] categories
//                String isbn,
//                int pageCount,
//                String thumbnailUrl,
//                String status,

    ) {
        this._id = _id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.authors = authors;
        this.copies = copies;
        this.availableCopies = copies;
//        this.categories = categories;
//        this.isbn = isbn;
//        this.pageCount = pageCount;
//        this.thumbnailUrl = thumbnailUrl;
//        this.status = status;


    }
}



