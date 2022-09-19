package com.example.library.controllers;

import com.example.library.components.Book;
import com.example.library.components.Library;
import com.example.library.components.Reader;
import com.example.library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

public class LibraryController {
    @Autowired
    LibraryService libraryService;
    Library library;


    @PutMapping("/addReader")
    public String addReader(@RequestParam String id, @RequestParam String name) throws Exception {
        libraryService.addReader(new Reader(id, name));
        return "Reader " + name + " was successfully added!";
    }

    @GetMapping("/getAllReaders")
    public List<Reader> getReaders() {
        return libraryService.getAllReaders();
    }

    @GetMapping("/getReaderById/{id}")
    public Reader getReaderWithId(@PathVariable String id) throws Exception {
        return libraryService.getReaderById(id);
    }

    @GetMapping("/getReaderByName/{name}")
    public Reader getReaderWithName(@PathVariable String name) throws Exception {
        return libraryService.getReaderByName(name);
    }

    @PutMapping("/addBook")
    public String addBook(@RequestParam String author, @RequestParam String title, @RequestParam int noPages, @RequestParam int noCopies) {
        libraryService.addBook(new Book(author, title, noPages), noCopies);
        return "Book named " + title + " written by " + author + " with " + noCopies + " copies was successfully introduced!";
    }

    @GetMapping("/getAllBooks")
    public Map<Book, Integer> getBooks() {
        return libraryService.getAllBooks();
    }

    @PutMapping("/borrowBook")
    public String borrowBook(@RequestParam String readerId, @RequestParam String title) throws Exception {
        if (libraryService.isUserPresent(readerId, title)) {
            libraryService.verifyStock(title);
            checkIfBookAlreadyBorrowed(readerId, title);
            libraryService.borrowBook(readerId, title);
        } else {
            throw new Exception("User not found!");
        }
        return "Book " + title + " was borrowed by reader with id " + readerId;
    }

    @PutMapping("/returnBook")
    public String returnBook(@RequestParam String readerId, @RequestParam String title){
        libraryService.returnBook(readerId, title);


        return "Book named " + title + " was returned by reader with id " + readerId;
    }


    private void checkIfBookAlreadyBorrowed(String readerId, String title) throws Exception {
        if (libraryService.isBookAlreadyBorrowed(readerId, title)) {
            throw new Exception(String.format("Book %s already borrowed by reader with id %s", title, readerId));
        }
    }

}
