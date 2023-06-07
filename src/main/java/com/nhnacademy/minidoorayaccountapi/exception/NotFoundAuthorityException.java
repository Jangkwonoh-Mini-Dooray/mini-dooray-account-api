package com.nhnacademy.minidoorayaccountapi.exception;

public class NotFoundAuthorityException extends RuntimeException {
    public NotFoundAuthorityException(int authorityId) {
        super(String.format("The authority corresponding to the authority ID = [%s] does not exist", authorityId));
    }
}
