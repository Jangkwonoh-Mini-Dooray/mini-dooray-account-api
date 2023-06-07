package com.nhnacademy.minidoorayaccountapi.member_authority.repository;

import com.nhnacademy.minidoorayaccountapi.member.entity.QMember;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.QMemberAuthority;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberAuthorityRepositoryImpl extends QuerydslRepositorySupport implements MemberAuthorityRepositoryCustom {
    public MemberAuthorityRepositoryImpl() {
        super(MemberAuthority.class);
    }

    @Override
    public MemberAuthorityDto getMemberAuthority(int memberAuthorityId) {
        QMember qMember = QMember.member;
    QMemberAuthority qMemberAuthority = QMemberAuthority.memberAuthority;

        return from(qMemberAuthority)
                .innerJoin(qMember.memberAuthority, qMemberAuthority)
                .where(qMember.memberAuthority.memberAuthorityId.eq(memberAuthorityId))
                .select(Projections.bean(MemberAuthorityDto.class,
                        qMemberAuthority.status))
                .fetchOne();
    }
}
