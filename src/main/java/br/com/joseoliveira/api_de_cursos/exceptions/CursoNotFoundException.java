package br.com.joseoliveira.api_de_cursos.exceptions;

public class CursoNotFoundException extends RuntimeException {
    public CursoNotFoundException(String message) {
        super(message);
    }
}