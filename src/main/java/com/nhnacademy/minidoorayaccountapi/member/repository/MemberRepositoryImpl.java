package com.nhnacademy.minidoorayaccountapi.member.repository;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.entity.QMember;
import com.nhnacademy.minidoorayaccountapi.authority.entity.QMemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.authority.repository.MemberAuthorityRepository;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    private MemberAuthorityRepository memberAuthorityRepository;

    @Override
    public List<GetMemberDto> getMembers() {
        QMember qMember = QMember.member;
        QMemberAuthority qMemberAuthority = QMemberAuthority.memberAuthority;

        return from(qMember)
                .innerJoin(qMember.memberAuthority, qMemberAuthority)
                .select(Projections.constructor(GetMemberDto.class,
                        qMember.memberId,
                        qMember.memberAuthority.status,
                        qMember.password,
                        qMember.email,
                        qMember.name))
                .fetch();
    }

    @Override
    public GetMemberDto getMember(String memberId) {
        QMember qMember = QMember.member;
        QMemberAuthority qMemberAuthority = QMemberAuthority.memberAuthority;

        return from(qMember)
                .where(qMember.memberId.eq(memberId))
                .innerJoin(qMember.memberAuthority, qMemberAuthority)
                .select(Projections.constructor(GetMemberDto.class,
                        qMember.memberId,
                        qMember.memberAuthority.status,
                        qMember.password,
                        qMember.email,
                        qMember.name))
                .fetchOne();
    }
}
