package com.example.library.components;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Book {
    private String author;
    private String title;
    private int noPages;

    public Book(String author, String title, int noPages) {
        this.author = author;
        this.title = title;
        this.noPages = noPages;
    }

    public Book() {
    }
}
