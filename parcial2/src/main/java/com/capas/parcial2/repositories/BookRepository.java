package com.capas.parcial2.repositories;

import com.capas.parcial2.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findByLanguage(String language);
    List<Book> findByGenre(String genre);
}
