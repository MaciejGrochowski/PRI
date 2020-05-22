package com.example.PRI.exceptions;

public class CharacterSaveException extends RuntimeException {

    public CharacterSaveException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
