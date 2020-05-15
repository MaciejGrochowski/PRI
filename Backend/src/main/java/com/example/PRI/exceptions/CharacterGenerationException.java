package com.example.PRI.exceptions;

public class CharacterGenerationException extends RuntimeException {
    public CharacterGenerationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
