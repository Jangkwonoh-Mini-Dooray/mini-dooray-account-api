package com.nhnacademy.minidoorayaccountapi.member.entity;

import com.nhnacademy.minidoorayaccountapi.memberAuthority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.memberStatus.entity.MemberStatus;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "mamber_id")
    private Long memberId;

    @Column(name = "mamber_status_id")
    @ManyToOne
    @JoinColumn(name = "mamber_status_id")
    private MemberStatus memberStatus;

    @Column(name = "member_authority_id")
    @ManyToOne
    @JoinColumn(name = "member_authority_id")
    private MemberAuthority memberAuthority;

    private String password;

    private String email;

    private String name;
}
