package com.nhnacademy.minidoorayaccountapi.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MemberDto {
    private String id;
    private String email;
    private String name;
}
