package br.com.joseoliveira.api_de_cursos.dto;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record CursoResponseDTO(
        Long id,
        String name,
        String category,
        String professor,
        boolean active,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {

}