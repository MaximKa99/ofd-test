package com.ofd.ofd.exception;

public class NoSuchUserException extends RuntimeException{

    public NoSuchUserException() {
        super("Такой юзер не был найден!");
    }

    public NoSuchUserException(String message) {
        super(message);
    }
    
}
