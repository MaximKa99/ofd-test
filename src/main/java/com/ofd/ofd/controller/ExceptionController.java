package com.ofd.ofd.controller;

import java.sql.SQLException;
import java.util.UUID;

import com.ofd.ofd.exception.AlreadyExistsUserException;
import com.ofd.ofd.exception.NoSuchUserException;
import com.ofd.ofd.exception.SmthGoneWrongException;
import com.ofd.ofd.exception.WrongPasswordException;
import com.ofd.ofd.view.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        String uuid = UUID.randomUUID().toString();
        ErrorResponse res = new ErrorResponse();

        res.setUUID(uuid);
        res.setMessage(e.getMessage());
        if (e instanceof NoSuchUserException) {
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        if (e instanceof AlreadyExistsUserException) {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        if (e instanceof WrongPasswordException) {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        if (e instanceof SmthGoneWrongException) {
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
