package com.example.library.components;

import lombok.Data;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idBook;
    private String title;
    private String author;
    private int noPages;
    private int noCopies;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;

    }
    public Book(String title, String author, int noPages, int noCopies) {
        this.title = title;
        this.author = author;
        this.noPages = noPages;
        this.noCopies = noCopies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, noPages);
    }

    public Book() {
    }
}
