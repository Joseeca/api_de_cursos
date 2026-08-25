package br.com.joseoliveira.api_de_cursos.controller;

import br.com.joseoliveira.api_de_cursos.dto.CursoRequestDTO;
import br.com.joseoliveira.api_de_cursos.dto.CursoResponseDTO;
import br.com.joseoliveira.api_de_cursos.dto.CursoUpdateDTO;
import br.com.joseoliveira.api_de_cursos.entity.CursoEntity;
import br.com.joseoliveira.api_de_cursos.mapper.CursoMapper;
import br.com.joseoliveira.api_de_cursos.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoResponseDTO> createCurso(@Valid @RequestBody CursoRequestDTO cursoRequestDTO) {
        CursoEntity cursoSalvo = cursoService.saveCurso(CursoMapper.toCursoEntity(cursoRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(CursoMapper.toCursoResponseDTO(cursoSalvo));
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> getAllCursos(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {

        List<CursoEntity> cursos = cursoService.findAllFiltered(name, category);

        List<CursoResponseDTO> responseDTOs = cursos.stream()
                .map(CursoMapper::toCursoResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> updateCurso(
            @PathVariable Long id,
            @RequestBody CursoUpdateDTO cursoUpdateDTO) {

        CursoEntity cursoAtualizado = cursoService.updateCurso(id, cursoUpdateDTO);
        return ResponseEntity.ok(CursoMapper.toCursoResponseDTO(cursoAtualizado));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CursoResponseDTO> toggleActive(@PathVariable Long id) {
        CursoEntity cursoAtualizado = cursoService.toggleActive(id);
        return ResponseEntity.ok(CursoMapper.toCursoResponseDTO(cursoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}