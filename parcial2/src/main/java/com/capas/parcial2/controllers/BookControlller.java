package com.capas.parcial2.controllers;

import com.capas.parcial2.dtos.BookCreateDTO;
import com.capas.parcial2.dtos.BookUpdateDTO;
import com.capas.parcial2.models.Book;
import com.capas.parcial2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookControlller {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    public Book create(@RequestBody BookCreateDTO dto) {
        return bookService.createBook(dto);
    }

    @PatchMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookUpdateDTO dto) {
        return bookService.updateBook(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }



}
