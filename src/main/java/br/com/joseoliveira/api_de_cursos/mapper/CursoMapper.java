package br.com.joseoliveira.api_de_cursos.mapper;

import br.com.joseoliveira.api_de_cursos.dto.CursoRequestDTO;
import br.com.joseoliveira.api_de_cursos.dto.CursoResponseDTO;
import br.com.joseoliveira.api_de_cursos.entity.CursoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CursoMapper {

    public static CursoEntity toCursoEntity(CursoRequestDTO dto) {
        return CursoEntity.builder()
                .name(dto.name())
                .category(dto.category())
                .professor(dto.professor())
                .active(false)
                .build();
    }

    public static CursoResponseDTO toCursoResponseDTO(CursoEntity entity) {
        return CursoResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .professor(entity.getProfessor())
                .active(entity.isActive())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .build();
    }
}