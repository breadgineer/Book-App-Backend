package com.bookkapp.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Document("books")
public class Book {

    @MongoId
    private Integer _id;
    private String title;
    private String isbn;
    private int pageCount;
//    private Timestamp publishedDate;
    private String thumbnailUrl;
    private String shortDescription;
    private String longDescription;
    private String status;
    private String[] authors;
    private String[] categories;

    public Book(Integer _id,
                String title,
                String isbn,
                int pageCount,
//                Timestamp publishedDate,
                String thumbnailUrl,
                String shortDescription,
                String longDescription,
                String status,
                String[] authors,
                String[] categories)
    {
        this._id = _id;
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
//        this.publishedDate = publishedDate;
        this.thumbnailUrl = thumbnailUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.status = status;
        this.authors = authors;
        this.categories = categories;
    }
}



