package com.nhnacademy.minidoorayaccountapi.member.authority.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAuthorityIdDto {
    @NotNull
    @Positive
    private int memberAuthorityId;
}
