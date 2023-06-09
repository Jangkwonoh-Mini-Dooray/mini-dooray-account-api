package com.nhnacademy.minidoorayaccountapi.member.authority.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_authority")
public class MemberAuthority {
    @Id
    @Column(name = "member_authority_id")
    private int memberAuthorityId;

    private String status;
}
