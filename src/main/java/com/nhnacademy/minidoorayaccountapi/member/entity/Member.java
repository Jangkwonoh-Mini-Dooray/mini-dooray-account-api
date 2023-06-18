package com.nhnacademy.minidoorayaccountapi.member.entity;

import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.UpdateMemberStatusAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    private String memberId;

    @ManyToOne
    @JoinColumn(name = "member_status_id")
    private MemberStatus memberStatus;

    @ManyToOne
    @JoinColumn(name = "member_authority_id")
    private MemberAuthority memberAuthority;

    private String password;

    private String email;

    private String name;

    public void updateMember(PutMemberDto putMemberDto) {
        this.password = putMemberDto.getPassword();
        this.email = putMemberDto.getEmail();
        this.name = putMemberDto.getName();
    }

    public void updateMemberStatusOrAuthority(UpdateMemberStatusAuthorityDto updateMemberStatusAuthorityDto) {
        if (updateMemberStatusAuthorityDto.getMemberStatus() != null) {
            this.memberStatus = updateMemberStatusAuthorityDto.getMemberStatus();
        }
        if (updateMemberStatusAuthorityDto.getMemberAuthority() != null) {
            this.memberAuthority = updateMemberStatusAuthorityDto.getMemberAuthority();
        }
    }
}
