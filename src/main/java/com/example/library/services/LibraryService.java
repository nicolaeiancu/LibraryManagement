package com.example.library.services;

import com.example.library.components.Book;
import com.example.library.components.BookRepository;
import com.example.library.components.Reader;
import com.example.library.components.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReaderRepository readerRepository;
    @Value("${libraryService.config.maxReaders}")
    int maxReaders;

    public void addReader(Reader r) throws Exception {
        if (readerRepository.count() < maxReaders) readerRepository.save(r);
        else throw new Exception("Maximum number of readers reached!");
    }

    public List<Reader> getReaders() {
        return readerRepository.findAll();
    }

    public String addBook(String title, String author, int noPages, int noCopies) {
        Book b = new Book(title, author, noPages, noCopies);
        if (bookRepository.findAll()
                .stream().filter(book -> book.equals(b)).toList().contains(b)) {
            Book foundBook = bookRepository.findAll()
                    .stream().filter(book -> book.equals(b)).toList().get(0);
            foundBook.setNoCopies(foundBook.getNoCopies() + noCopies);
            bookRepository.save(foundBook);
            return noCopies + " copies were added to the existent " + title + " book.";
        } else {
            bookRepository.save(b);
            return "Book named " + title + " written by " + author + " with " + noCopies + " copies was successfully introduced!";
        }

    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Reader getReaderById(String id) {
        return readerRepository.getReaderByReaderId(id);
    }

    public Reader getReaderByName(String name) {
        return readerRepository.getReaderByName(name);
    }

    public String borrowBook(String readerId, String title, String author) {
        Reader reader = getReaderById(readerId);
        Book bookVerified = new Book(title, author);
        if (reader.getBorrowedBooks().
                stream().
                filter(bookBorrowed -> bookBorrowed.equals(bookVerified)).
                collect(Collectors.toList()).isEmpty()) {
            Book bookWanted = bookRepository.findByTitleAndAuthor(title, author);
            bookWanted.setNoCopies(bookWanted.getNoCopies() - 1);
            bookRepository.save(bookWanted);
            reader.getBorrowedBooks().add(bookWanted);
            readerRepository.save(reader);
            return "Book " + title + " was borrowed successfully by reader with id: " + readerId;

        } else return "Book already borrowed.";
    }

    public String returnBook(String readerId, String title, String author) {
        Reader r = getReaderById(readerId);
        Book b = new Book(title, author);
        if (r.getBorrowedBooks().stream().filter(bookBorrowed -> bookBorrowed.equals(b)).
                toList().contains(b)) {
            Book bookReturned = bookRepository.findByTitleAndAuthor(title, author);
            bookReturned.setNoCopies(bookReturned.getNoCopies() + 1);
            bookRepository.save(bookReturned);
            r.getBorrowedBooks().remove(bookReturned);
            readerRepository.save(r);
            return "Book successfully returned!";
        } else return "The book is not borrowed.";
    }

    public void verifyStock(String title, String author) throws Exception {
        Book b = bookRepository.findByTitleAndAuthor(title, author);
        if (b.getNoCopies() <= 0) throw new Exception("Book not available!");
    }
    /*public void addBook(Book b, int noCopies) {
        library.getBooks().put(b, noCopies);

    }

    public Map<Book, Integer> getAllBooks() {
        return library.getBooks();
    }*/
/*
    public boolean isUserPresent(String id) throws Exception{
        if(readerRepository.getReaderByReaderId(id) != null) return true;
        return false;
    }

    public void verifyStock(String title) throws Exception {
        bookRepository.findByTitle(title);
        /*Map<Book, Integer> books = library.getBooks();
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
        Optional<Book> bookOptional = reader.getBorrowedBooks()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findAny();
        return bookOptional.isPresent();
    }
*/


        /*Map<Book, Integer> books = library.getBooks();
        Map.Entry<Book, Integer> bookInventoryEntry = books.entrySet().stream()
                .filter(bookEntry -> bookEntry.getKey().getTitle().equals(title))
                .findAny()
                .get();
        books.put(bookInventoryEntry.getKey(), bookInventoryEntry.getValue()-1);
    }*/

    /*public void returnBook(String readerId, String title) {
        Map<Book, Integer> books = library.getBooks();
        Map.Entry<Book, Integer> bookInventoryEntry = books.entrySet().stream()
                .filter(bookEntry -> bookEntry.getKey().getTitle().equals(title))
                .findAny()
                .get();
        books.put(bookInventoryEntry.getKey(), bookInventoryEntry.getValue() + 1);

    }*/
}
