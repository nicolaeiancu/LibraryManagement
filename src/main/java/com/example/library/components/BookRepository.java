package com.example.library.components;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
    public Book findByTitleAndAuthor(String title, String author);
}
