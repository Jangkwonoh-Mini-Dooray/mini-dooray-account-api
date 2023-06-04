package com.nhnacademy.minidoorayaccountapi.memberStatus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_status")
public class MemberStatus {
    @Id
    @Column(name = "member_status_id")
    private int memberStatusId;

    private String status;
}
