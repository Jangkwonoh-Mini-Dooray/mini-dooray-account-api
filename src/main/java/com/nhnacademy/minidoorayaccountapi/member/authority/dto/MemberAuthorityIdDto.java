package com.nhnacademy.minidoorayaccountapi.member.authority.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAuthorityIdDto {
    @NotNull
    @Positive
    private int memberAuthorityId;
}
