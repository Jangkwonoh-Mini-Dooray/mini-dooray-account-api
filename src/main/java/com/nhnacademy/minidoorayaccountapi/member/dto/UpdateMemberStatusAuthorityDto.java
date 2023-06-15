package com.nhnacademy.minidoorayaccountapi.member.dto;

import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import lombok.Getter;

@Getter
public class UpdateMemberStatusAuthorityDto {
    private MemberStatus memberStatus;
    private MemberAuthority memberAuthority;

    public UpdateMemberStatusAuthorityDto(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public UpdateMemberStatusAuthorityDto(MemberAuthority memberAuthority) {
        this.memberAuthority = memberAuthority;
    }
}
