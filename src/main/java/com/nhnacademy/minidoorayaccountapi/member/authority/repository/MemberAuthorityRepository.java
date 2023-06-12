package com.nhnacademy.minidoorayaccountapi.member.authority.repository;

import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority, Integer>, MemberAuthorityRepositoryCustom {
}
