package com.capas.parcial2.services;

import com.capas.parcial2.dtos.BookCreateDTO;
import com.capas.parcial2.dtos.BookUpdateDTO;
import com.capas.parcial2.models.Book;
import com.capas.parcial2.repositories.BookRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    private String regex = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";
    String regexLetras = "^(?!\\d+$).+$";

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book createBook(BookCreateDTO dto) {
        Book bookEntity = new Book();

        if(dto.getPages() <= 10 ) {
            throw new IllegalArgumentException("El número de páginas debe ser mayor a 10.");
        }

        if(!dto.getTitle().matches(regexLetras)) {
            throw new IllegalArgumentException("El título del libro debe contener al menos una letra.");
        }
        bookEntity.setTitle(dto.getTitle());

        bookEntity.setAuthor(dto.getAuthor());

        if (!dto.getIsbn().matches(regex)) {
            throw new IllegalArgumentException("El ISBN debe ser un número válido de 13 dígitos.");
        }
        bookEntity.setIsbn(dto.getIsbn());

        if(dto.getPublicationYear() <= 1900 || dto.getPublicationYear() > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("El año de publicación debe ser mayor a 1900 y no puede ser mayor al año actual.");
        }
        bookEntity.setPublicationYear(dto.getPublicationYear());
        bookEntity.setLanguage(dto.getLanguage());
        bookEntity.setPages(dto.getPages());
        bookEntity.setGenre(dto.getGenre());

        return bookRepository.save(bookEntity);
    }

    public Book updateBook(Long id, BookUpdateDTO dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El libro no existe."));

        existingBook.setLanguage(dto.getLanguage());

        if(!dto.getTitle().matches(regexLetras)) {
            throw new IllegalArgumentException("El título del libro debe contener al menos una letra.");
        }
        existingBook.setTitle(dto.getTitle());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El libro no existe."));

        bookRepository.delete(book);
    }

    public List<Book> filterByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> filterByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> filterByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public List<Book> filterByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
}
