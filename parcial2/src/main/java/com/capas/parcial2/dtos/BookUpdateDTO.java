package com.capas.parcial2.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookUpdateDTO {
    @NotNull(message = "El titulo del libro es obligatorio")
    String title;

    @NotNull(message = "El idioma del libro es obligatorio")
    String language;

    String isbn;
}
