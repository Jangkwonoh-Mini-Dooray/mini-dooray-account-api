package com.nhnacademy.minidoorayaccountapi.memberAuthority.repository;

import com.nhnacademy.minidoorayaccountapi.memberAuthority.entity.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority, Long> {
}
