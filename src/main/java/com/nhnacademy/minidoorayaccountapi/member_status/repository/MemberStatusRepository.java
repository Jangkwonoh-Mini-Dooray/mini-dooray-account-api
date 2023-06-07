package com.nhnacademy.minidoorayaccountapi.member_status.repository;

import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStatusRepository extends JpaRepository<MemberStatus, Integer> {
    MemberStatusDto getStatusByMemberStatusId(int memberStatusId);
}
