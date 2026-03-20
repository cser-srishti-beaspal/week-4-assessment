package com.capgemini.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.entity.Book;
import com.capgemini.model.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    // SAVE ONE
    public void saveBook(Book book) {
        if (book.getAvailableCopies() < 0) {
            throw new RuntimeException("Available copies cannot be negative");
        }

        book.setBorrowedCopies(0);

        bookRepository.save(book);
        System.out.println("Book Saved Successfully!");
    }

    // SAVE MULTIPLE
    public void saveBooks(List<Book> books) {
        for (Book book : books) {
            if (book.getAvailableCopies() < 0) {
                throw new RuntimeException("Available copies cannot be negative");
            }
            book.setBorrowedCopies(0);
        }

        bookRepository.saveAll(books);
        System.out.println("Books Saved Successfully!");
    }

    // GET ONE
    public Book getBook(Integer id) {
        return bookRepository.findById(id).get();
    }

    // GET ALL
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // UPDATE
    public Book updateBook(Integer id, Book newBook) {
        Book existing = bookRepository.findById(id).get();

        if (newBook != null && existing != null) {

            if (newBook.getAvailableCopies() < 0) {
                throw new RuntimeException("Available copies cannot be negative");
            }

            existing.setTitle(newBook.getTitle());
            existing.setAuthor(newBook.getAuthor());
            existing.setAvailableCopies(newBook.getAvailableCopies());
        }

        return bookRepository.save(existing);
    }

    // DELETE
    public void deleteBook(Integer id) {
        Book existing = bookRepository.findById(id).get();
        bookRepository.deleteById(id);
    }

    // BORROW BOOK
    public String borrowBook(Integer id) {
        Book book = bookRepository.findById(id).get();

        if (book.getAvailableCopies() == 0) {
            throw new RuntimeException("No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        book.setBorrowedCopies(book.getBorrowedCopies() + 1);

        bookRepository.save(book);

        return "Book borrowed successfully";
    }

    // RETURN BOOK
    public String returnBook(Integer id) {
        Book book = bookRepository.findById(id).get();

        if (book.getBorrowedCopies() == 0) {
            throw new RuntimeException("No borrowed books to return");
        }

        book.setBorrowedCopies(book.getBorrowedCopies() - 1);
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);

        return "Book returned successfully";
    }
}