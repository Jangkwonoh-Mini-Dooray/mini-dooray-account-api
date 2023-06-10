package com.nhnacademy.minidoorayaccountapi.member_authority.repository;

import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberAuthorityRepositoryImpl extends QuerydslRepositorySupport implements MemberAuthorityRepositoryCustom {
    public MemberAuthorityRepositoryImpl() {
        super(MemberAuthority.class);
    }
}
