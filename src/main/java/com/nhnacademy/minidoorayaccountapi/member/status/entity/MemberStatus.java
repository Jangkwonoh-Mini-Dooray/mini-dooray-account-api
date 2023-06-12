package com.nhnacademy.minidoorayaccountapi.member.status.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_status")
public class MemberStatus {
    @Id
    @Column(name = "member_status_id")
    private int memberStatusId;

    private String status;
}
