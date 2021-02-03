package com.ofd.ofd.exception;

public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException() {
        super("Неверный пароль!");
    }

    public WrongPasswordException(String message) {
        super(message);
    }
    
}
