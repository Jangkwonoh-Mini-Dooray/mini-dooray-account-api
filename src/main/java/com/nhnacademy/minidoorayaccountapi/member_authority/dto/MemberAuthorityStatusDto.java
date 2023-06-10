package com.nhnacademy.minidoorayaccountapi.member_authority.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAuthorityStatusDto {
    @NotBlank
    private String status;
}
