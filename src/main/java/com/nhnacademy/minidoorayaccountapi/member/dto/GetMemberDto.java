package com.nhnacademy.minidoorayaccountapi.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMemberDto {
    private String memberId;
    private String email;
    private String name;
}
