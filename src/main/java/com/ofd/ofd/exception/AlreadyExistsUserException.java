package com.ofd.ofd.exception;

public class AlreadyExistsUserException extends RuntimeException{

    public AlreadyExistsUserException() {
        super("Юзер с данным логином уже существует!");
    }

    public AlreadyExistsUserException(String message) {
        super(message);
    }
    
}
