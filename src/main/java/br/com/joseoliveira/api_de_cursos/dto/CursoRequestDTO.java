package br.com.joseoliveira.api_de_cursos.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoRequestDTO (

        @NotBlank(message = "O campo 'name' não pode ficar em branco.")
        String name,
        @NotBlank(message = "O campo 'category' não pode ficar em branco.")
        String category,
        @NotBlank(message = "O campo 'professor' não pode ficar em branco.")
        String professor
) {
}