package com.nhnacademy.minidoorayaccountapi.member.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String memberId;
    private String password;
    private String email;
    private String name;

    public MemberDto(String memberId, String email, String name) {
        this.memberId = memberId;
        this.password = null;
        this.email =  email;
        this.name = name;
    }
}
