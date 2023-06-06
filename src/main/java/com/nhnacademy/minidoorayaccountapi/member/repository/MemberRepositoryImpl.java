package com.nhnacademy.minidoorayaccountapi.member.repository;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.entity.QMember;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<GetMemberDto> getMembers() {
        QMember qMember = QMember.member;

        return from(qMember)
                .select(Projections.bean(GetMemberDto.class,
                        qMember.memberId,
                        qMember.email,
                        qMember.name))
                .fetch();
    }

    @Override
    public GetMemberDto getMember(String memberId) {
        QMember qMember = QMember.member;

        return from(qMember)
                .where(qMember.memberId.eq(memberId))
                .select(Projections.bean(GetMemberDto.class,
                        qMember.memberId,
                        qMember.email,
                        qMember.name))
                .fetchOne();
    }
}
