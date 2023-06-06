package com.nhnacademy.minidoorayaccountapi.member.repository;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MemberRepositoryCustom {
    List<GetMemberDto> getMembers();
    GetMemberDto getMember(String memberId);
}
