package com.example.library.components;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Data
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "readers")
@JsonFilter("ReaderFilter")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String readerId;
    private String name;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "borrowedBooks",
            joinColumns = @JoinColumn(name = "readerID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bookID", referencedColumnName = "idBook")
    )
    private Set<Book> borrowedBooks = new TreeSet<>();

    public Reader(String readerId, String name) {
        this.readerId = readerId;
        this.name = name;
    }

}
