package com.nhnacademy.minidoorayaccountapi.member.status.repository;

import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberStatusRepositoryImpl extends QuerydslRepositorySupport implements MemberStatusRepositoryCustom {
    public MemberStatusRepositoryImpl() {
        super(MemberStatus.class);
    }
}
