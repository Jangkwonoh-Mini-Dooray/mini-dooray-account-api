package com.nhnacademy.minidoorayaccountapi.advice;

import com.nhnacademy.minidoorayaccountapi.exception.DuplicateMemberIdException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>("No such element : " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateMemberIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleDuplicateDepartmentIdException(DuplicateMemberIdException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<Object> handleDepartmentNotFoundException(NotFoundMemberException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

