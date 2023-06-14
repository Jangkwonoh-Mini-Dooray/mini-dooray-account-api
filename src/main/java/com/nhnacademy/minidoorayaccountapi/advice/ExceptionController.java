package com.nhnacademy.minidoorayaccountapi.advice;

import com.nhnacademy.minidoorayaccountapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DuplicateMemberIdException.class)
    public ResponseEntity<Object> handleDuplicateDepartmentIdException(DuplicateMemberIdException ex) {
        return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundMemberException.class, NotFoundMemberStatusException.class, NotFoundMemberAuthorityException.class})
    public ResponseEntity<Object> handleNotFoundMemberException(Exception ex) {
        return buildResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<Object> handleMemberBindingResultException(ValidationFailedException ex) {
        return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", message);
        body.put("status", status.value());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, status);
    }
}