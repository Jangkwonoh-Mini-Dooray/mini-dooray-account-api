package com.nhnacademy.minidoorayaccountapi.member.authority.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class MemberAuthorityStatusDto {
    @NotBlank
    private String status;
}
