package com.assignment.csvhandler.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Record with the given Code is Not found in the database");
    }

    @ExceptionHandler({UnsupportedFileFormatException.class})
    public ResponseEntity<String> handleEntityNotFoundException(UnsupportedFileFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This file format is not supported for upload");
    }

}
