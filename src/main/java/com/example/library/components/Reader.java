package com.example.library.components;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name="Readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="readerID")
    private String id;
    @Column
    private String name;
    @Column
    private List<Book> borrowed = new ArrayList<>();

    public Reader(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Reader() {
    }
}
