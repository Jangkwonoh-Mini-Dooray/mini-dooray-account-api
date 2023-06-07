package com.nhnacademy.minidoorayaccountapi.exception;

public class NotFoundMemberException extends RuntimeException {
    public NotFoundMemberException(String memberId) {
        super(String.format("The member corresponding to the member ID = %s does not exist", memberId));
    }
}
