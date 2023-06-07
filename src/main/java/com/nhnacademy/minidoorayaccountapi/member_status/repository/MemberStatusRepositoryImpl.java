package com.nhnacademy.minidoorayaccountapi.member_status.repository;

import com.nhnacademy.minidoorayaccountapi.member.entity.QMember;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.QMemberStatus;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberStatusRepositoryImpl extends QuerydslRepositorySupport implements MemberStatusRepositoryCustom {
    public MemberStatusRepositoryImpl() {
        super(MemberStatus.class);
    }

    @Override
    public MemberStatusDto getMemberStatus(int memberStatusId) {
        QMemberStatus qMemberStatus = QMemberStatus.memberStatus;

        return from(qMemberStatus)
                .where(qMemberStatus.memberStatusId.eq(memberStatusId))
                .select(Projections.bean(MemberStatusDto.class,
                        qMemberStatus.status))
                .fetchOne();
    }
}
