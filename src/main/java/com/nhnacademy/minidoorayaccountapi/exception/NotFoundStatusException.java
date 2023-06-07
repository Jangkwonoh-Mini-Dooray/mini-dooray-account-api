package com.nhnacademy.minidoorayaccountapi.exception;

public class NotFoundStatusException extends RuntimeException {
    public NotFoundStatusException(int memberStatusId) {
        super(String.format("The status corresponding to the status ID = [%s] does not exist", memberStatusId));
    }
}
