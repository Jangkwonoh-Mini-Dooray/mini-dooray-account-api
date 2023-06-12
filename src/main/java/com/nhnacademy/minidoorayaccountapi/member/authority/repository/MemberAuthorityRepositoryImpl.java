package com.nhnacademy.minidoorayaccountapi.member.authority.repository;

import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberAuthorityRepositoryImpl extends QuerydslRepositorySupport implements MemberAuthorityRepositoryCustom {
    public MemberAuthorityRepositoryImpl() {
        super(MemberAuthority.class);
    }
}
