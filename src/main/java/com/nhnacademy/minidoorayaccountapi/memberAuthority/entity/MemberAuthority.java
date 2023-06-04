package com.nhnacademy.minidoorayaccountapi.memberAuthority.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_authority")
public class MemberAuthority {
    @Id
    @Column(name = "member_authority_id")
    private int authorityId;

    private String status;
}
