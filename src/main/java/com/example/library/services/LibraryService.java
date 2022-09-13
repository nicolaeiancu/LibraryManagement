package com.example.library.services;


import com.example.library.components.Library;
import com.example.library.components.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    Library library;
    @Value("12")
    int maxReaders;

    public void addReaders(Reader r) throws Exception {

        if(library.getReaders().size() < maxReaders) library.getReaders().add(r);
        else throw new Exception("Maximum number of readers reached!");
    }

    public List<Reader> getAllReaders() {
        return library.getReaders();
    }

    public Reader getReaderById(String id) throws Exception {
        Optional<Reader> optional = library.getReaders().stream().filter(r -> r.getId().equals(id)).findAny();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new Exception("Reader with id " + id + " not found!");
    }

    public Reader getReaderByName(String name) throws Exception {
        Optional<Reader> optional = library.getReaders().stream().filter(r -> r.getName().equals(name)).findAny();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new Exception("Reader named " + name + " not found!");
    }
}
