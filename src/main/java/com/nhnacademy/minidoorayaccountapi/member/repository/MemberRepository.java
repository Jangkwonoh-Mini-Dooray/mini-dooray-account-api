package com.nhnacademy.minidoorayaccountapi.member.repository;

import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom {
}