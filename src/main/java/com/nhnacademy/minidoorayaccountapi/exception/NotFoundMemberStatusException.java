package com.nhnacademy.minidoorayaccountapi.exception;

public class NotFoundMemberStatusException extends RuntimeException {
    public NotFoundMemberStatusException(int memberStatusId) {
        super(String.format("The status corresponding to the status ID = [%s] does not exist", memberStatusId));
    }
}
