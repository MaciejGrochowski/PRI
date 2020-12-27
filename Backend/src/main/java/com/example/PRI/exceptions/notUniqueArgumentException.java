package com.example.PRI.exceptions;

public class notUniqueArgumentException extends Throwable {
    public notUniqueArgumentException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
        }

