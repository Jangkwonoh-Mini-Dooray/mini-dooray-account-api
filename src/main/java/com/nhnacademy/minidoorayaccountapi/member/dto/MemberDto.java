package com.nhnacademy.minidoorayaccountapi.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberDto {
    private String id;
    private String password;
    private String email;
    private String name;
}
