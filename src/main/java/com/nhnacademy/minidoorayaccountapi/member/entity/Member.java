package com.nhnacademy.minidoorayaccountapi.member.entity;

import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
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
}
