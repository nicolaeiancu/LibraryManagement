package com.example.library.components;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
    public Reader getReaderByReaderId(String readerId);
    public Reader getReaderByName(String name);

}
