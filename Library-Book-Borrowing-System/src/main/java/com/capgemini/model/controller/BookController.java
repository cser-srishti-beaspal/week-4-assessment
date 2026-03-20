package com.capgemini.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capgemini.model.entity.Book;
import com.capgemini.model.service.BookService;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    // SAVE ONE
    @PostMapping("/saveBook")
    public void postBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    // SAVE MULTIPLE
    @PostMapping("/saveBooks")
    public void postBooks(@RequestBody List<Book> books) {
        bookService.saveBooks(books);
    }

    // GET ONE
    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookService.getBook(id);
    }

    // GET ALL
    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    // COMPLETE UPDATE
    @PutMapping("/completeUpdateBook/{id}")
    public Book updateBook(@PathVariable Integer id,
                           @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    // DELETE
    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    // BORROW BOOK
    @PostMapping("/borrowBook/{id}")
    public String borrowBook(@PathVariable Integer id) {
        return bookService.borrowBook(id);
    }

    // RETURN BOOK
    @PostMapping("/returnBook/{id}")
    public String returnBook(@PathVariable Integer id) {
        return bookService.returnBook(id);
    }
}