package com.capas.parcial2.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookCreateDTO {
    @NotNull(message = "El título es obligatorio")
    private String title;

    @NotNull(message = "El autor es obligatorio")
    private String author;

    @NotNull(message = "El ISBN es obligatorio")
    private String isbn;

    @NotNull(message = "El año de publicación es obligatorio")
    private Integer publicationYear;

    private String language;

    @NotNull(message = "El número de páginas es obligatorio")
    private Integer pages;

    @NotNull(message = "El género es obligatorio")
    private String genre;
}
