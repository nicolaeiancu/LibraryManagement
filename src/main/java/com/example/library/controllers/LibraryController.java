package com.example.library.controllers;

import com.example.library.components.Book;
import com.example.library.components.Reader;
import com.example.library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    @PutMapping("/addReader")
    public String addReader(@RequestParam String readerId, @RequestParam String name) throws Exception {
        libraryService.addReader(new Reader(readerId, name));
        return "Reader " + name + " was successfully added!";
    }

    @GetMapping("/getAllReaders")
    public List<Reader> getReaders() {
        return libraryService.getReaders();

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
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam int noPages, @RequestParam int noCopies) {
        return libraryService.addBook(title, author, noPages, noCopies);
    }

    @GetMapping("/getAllBooks")
    public List<Book> getBooks() {
        return libraryService.getAllBooks();
    }

    @PutMapping("/borrowBook")
    public String borrowBook(@RequestParam String readerId, @RequestParam String title, @RequestParam String author) throws Exception {
        libraryService.verifyStock(title, author);
        return libraryService.borrowBook(readerId, title, author);
    }

    @PutMapping("/returnBook")
    public String returnBook(@RequestParam String readerId, @RequestParam String title, @RequestParam String author) {
        return libraryService.returnBook(readerId, title, author);
    }

}
