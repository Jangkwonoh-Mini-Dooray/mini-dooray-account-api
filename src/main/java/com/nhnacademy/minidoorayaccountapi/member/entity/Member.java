package com.nhnacademy.minidoorayaccountapi.member.entity;

import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    private String memberId;

    @ManyToOne
    @JoinColumn(name = "member_status_id")
    private MemberStatus memberStatus;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private MemberAuthority memberAuthority;

    private String password;

    private String email;

    private String name;

}
