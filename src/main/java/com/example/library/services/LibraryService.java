package com.example.library.services;


import com.example.library.components.Book;
import com.example.library.components.Library;
import com.example.library.components.Reader;
import com.example.library.components.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    Library library;

    @Value("12")
    int maxReaders;

    public void addReader(Reader r) throws Exception {

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

    public void addBook(Book b, int noCopies) {
        library.getBooks().put(b, noCopies);

    }

    public Map<Book, Integer> getAllBooks() {
        return library.getBooks();
    }

    public boolean isUserPresent(String id, String title) throws Exception{
        return library.getReaders().stream().filter(r -> r.getId().equals(id)).findAny().isPresent();
    }

    public void verifyStock(String title) throws Exception {
        Map<Book, Integer> books = library.getBooks();
        Optional<Map.Entry<Book, Integer>> bookEntry = books.entrySet().stream()
                .filter(be -> be.getKey().getTitle().equals(title))
                .findAny();
        if(!bookEntry.isPresent()){
            throw new Exception("Book not found!");
        }
    }

    public boolean isBookAlreadyBorrowed(String readerId, String title) {
        Reader reader = library.getReaders()
                .stream()
                .filter(r -> r.getId().equals(readerId))
                .findAny()
                .get();
        Optional<Book> bookOptional = reader.getBorrowed()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findAny();
        return bookOptional.isPresent();
    }

    public void borrowBook(String readerId, String title) {
        Map<Book, Integer> books = library.getBooks();
        Map.Entry<Book, Integer> bookInventoryEntry = books.entrySet().stream()
                .filter(bookEntry -> bookEntry.getKey().getTitle().equals(title))
                .findAny()
                .get();
        books.put(bookInventoryEntry.getKey(), bookInventoryEntry.getValue()-1);
    }

    public void returnBook(String readerId, String title) {
        Map<Book, Integer> books = library.getBooks();
        Map.Entry<Book, Integer> bookInventoryEntry = books.entrySet().stream()
                .filter(bookEntry -> bookEntry.getKey().getTitle().equals(title))
                .findAny()
                .get();
        books.put(bookInventoryEntry.getKey(), bookInventoryEntry.getValue() + 1);

    }
}
