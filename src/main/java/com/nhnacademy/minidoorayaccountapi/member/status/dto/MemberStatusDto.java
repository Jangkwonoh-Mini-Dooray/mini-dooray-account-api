package com.nhnacademy.minidoorayaccountapi.member.status.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class MemberStatusDto {
    @NotBlank
    private String status;
}
