package com.nhnacademy.minidoorayaccountapi.member.status.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberStatusIdDto {
    @NotNull
    @Positive
    private int memberStatusId;
}
