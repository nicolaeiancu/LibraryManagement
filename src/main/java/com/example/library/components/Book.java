package com.example.library.components;

import lombok.Data;
import java.util.Objects;

@Data
public class Book {
    private String author;
    private String title;
    private int noPages;

    public Book(String author, String title, int noPages) {
        this.author = author;
        this.title = title;
        this.noPages = noPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return noPages == book.noPages && Objects.equals(author, book.author) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, noPages);
    }

    public Book() {
    }
}
