package com.nhnacademy.minidoorayaccountapi.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMemberDto {
    private String memberId;
    private String memberAuthorityStatus;
    private String password;
    private String email;
    private String name;
}
