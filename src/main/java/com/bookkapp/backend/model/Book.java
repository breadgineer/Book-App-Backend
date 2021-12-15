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
    //    private String isbn;
//    private int pageCount;
    private Date publishedDate;
    //    private String thumbnailUrl;
    private String shortDescription;
    private String longDescription;
    //    private String status;
    private String[] authors;
//    private String[] categories;


    public Book() {
    }

    // constructor with all arguments
    public Book(String _id,
                String title,
//                String isbn,
//                int pageCount,
                Date publishedDate,
//                String thumbnailUrl,
                String shortDescription,
                String longDescription,
//                String status,
                String[] authors
//                String[] categories
    ) {
        this._id = _id;
        this.title = title;
//        this.isbn = isbn;
//        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
//        this.thumbnailUrl = thumbnailUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
//        this.status = status;
        this.authors = authors;
//        this.categories = categories;
    }
}



