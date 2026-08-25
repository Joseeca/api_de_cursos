package br.com.joseoliveira.api_de_cursos.dto;

public record CursoUpdateDTO(
        String name,
        String category,
        String professor,
        Boolean active
) {

}