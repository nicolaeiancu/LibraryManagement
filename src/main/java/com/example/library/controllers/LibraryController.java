package com.example.library.controllers;

import com.example.library.components.Book;
import com.example.library.components.Library;
import com.example.library.components.Reader;
import com.example.library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class LibraryController {
    @Autowired
    LibraryService libraryService;
    Library library;


    @PutMapping("/addReader")
    public String addReaders(@RequestParam String id, @RequestParam String name) throws Exception{
        libraryService.addReaders(new Reader(id, name));
        return "Reader " + name + " was succesfully added!";}

    @GetMapping("/getAllReaders")
    public List<Reader> getReaders(){
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
    public String addBooks(@RequestParam String author, @RequestParam String title, @RequestParam int noPages, @RequestParam int noCopies){
        libraryService.addBooks(new Book(author, title, noPages), noCopies);
        return "Book named "+title+" written by "+author+" with "+noCopies+" was succesfully introduced!";
    }

    @GetMapping("/getAllBooks")
    public Map<Book, Integer> getBooks(){
        return libraryService.getAllBooks();
    }
}
