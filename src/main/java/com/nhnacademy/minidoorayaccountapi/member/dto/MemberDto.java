package com.nhnacademy.minidoorayaccountapi.member.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberDto {
    private String id;
    private String password;
    private String email;
    private String name;
}
