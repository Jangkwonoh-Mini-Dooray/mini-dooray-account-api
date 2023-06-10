package com.nhnacademy.minidoorayaccountapi.exception;

public class NotFoundMemberAuthorityException extends RuntimeException {
    public NotFoundMemberAuthorityException(int memberAuthorityId) {
        super(String.format("The authority corresponding to the authority ID = [%s] does not exist", memberAuthorityId));
    }
}
