package br.com.joseoliveira.api_de_cursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata a exceção de Curso não encontrado
    @ExceptionHandler(br.com.joseoliveira.api_de_cursos.exceptions.CursoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCursoNotFound(br.com.joseoliveira.api_de_cursos.exceptions.CursoNotFoundException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Trata os erros de validação do @Valid (@NotBlank, etc)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}