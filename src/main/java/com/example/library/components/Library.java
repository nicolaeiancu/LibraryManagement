package com.example.library.components;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class Library {
    public List<Reader> readers = new ArrayList<>();
    public Map<Book, Integer> books = new HashMap<>();


}
