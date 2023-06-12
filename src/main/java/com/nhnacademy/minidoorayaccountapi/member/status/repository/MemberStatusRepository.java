package com.nhnacademy.minidoorayaccountapi.member.status.repository;

import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStatusRepository extends JpaRepository<MemberStatus, Integer>, MemberStatusRepositoryCustom {
}
