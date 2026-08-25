package br.com.joseoliveira.api_de_cursos.service;

import br.com.joseoliveira.api_de_cursos.dto.CursoUpdateDTO;
import br.com.joseoliveira.api_de_cursos.entity.CursoEntity;
import br.com.joseoliveira.api_de_cursos.exceptions.CursoNotFoundException;
import br.com.joseoliveira.api_de_cursos.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoEntity saveCurso(CursoEntity cursoEntity) {
        return cursoRepository.save(cursoEntity);
    }

    // Busca com filtros opcionais
    public List<CursoEntity> findAllFiltered(String name, String category) {
        return cursoRepository.findByNameAndCategory(name, category);
    }

    public CursoEntity updateCurso(Long id, CursoUpdateDTO dto) {
        CursoEntity cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso não encontrado com o ID: " + id));

        // Atualização PARCIAL: Só atualiza se o campo foi enviado no JSON
        if (dto.name() != null) cursoExistente.setName(dto.name());
        if (dto.category() != null) cursoExistente.setCategory(dto.category());
        if (dto.professor() != null) cursoExistente.setProfessor(dto.professor());

        // Regra do desafio: se o active for enviado no PUT, é ignorado (não fazemos .setActive aqui)

        return cursoRepository.save(cursoExistente);
    }

    // Lógica da Rota PATCH (Toggle Active)
    public CursoEntity toggleActive(Long id) {
        CursoEntity cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso não encontrado com o ID: " + id));

        // Inverte o valor (se true vira false, se false vira true)
        cursoExistente.setActive(!cursoExistente.isActive());

        return cursoRepository.save(cursoExistente);
    }

    public void deleteCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new CursoNotFoundException("Curso não encontrado com o ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
}