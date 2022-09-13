package com.example.library.components;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
public class Reader {
    String id;
    String name;
    List<Book> borrowed;

    public Reader(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Reader() {
    }
}
