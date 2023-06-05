package com.nhnacademy.minidoorayaccountapi.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("Member Not Found!");
    }
}
