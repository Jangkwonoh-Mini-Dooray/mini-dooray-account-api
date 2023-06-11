package com.nhnacademy.minidoorayaccountapi.member.dto;

import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
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
    private String memberAuthorityStatus;
    private String password;
    private String email;
    private String name;
}
