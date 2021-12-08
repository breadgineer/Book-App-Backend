package com.bookkapp.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("books")
public class Book {

    @Id
    private String id;
    private String title;
    private int isbn;
    private int pageCount;
    private String publishedDate;
    private String thumbnailUrl;
    private String shortDescription;
    private String longDescription;
    private String status;
    private String[] authors;
    private String[] categories;

    public Book(String id, String title, int isbn, int pageCount, String publishedDate, String thumbnailUrl,
                String shortDescription, String longDescription, String status, String[] authors, String[] categories) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
        this.thumbnailUrl = thumbnailUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.status = status;
        this.authors = authors;
        this.categories = categories;
    }

    public Book() {}

    public String getId() {
        return id;
    }
}



