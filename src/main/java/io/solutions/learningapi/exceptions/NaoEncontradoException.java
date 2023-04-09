package io.solutions.learningapi.exceptions;

public class NaoEncontradoException extends Throwable {
    public NaoEncontradoException(Long id) {
        super(String.format("O curso com o id nº %s não foi encontrado", id));
    }
}
