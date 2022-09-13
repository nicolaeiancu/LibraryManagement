package com.example.library.controllers;

import com.example.library.components.Library;
import com.example.library.components.Reader;
import com.example.library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
