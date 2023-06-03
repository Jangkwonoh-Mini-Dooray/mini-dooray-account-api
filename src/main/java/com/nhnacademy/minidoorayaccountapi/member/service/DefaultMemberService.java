package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MemberDto> getMembers() {
        return memberRepository.findAllMemberDto();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberDto> getMember(Long memberId) {
        return memberRepository.findMemberDto(memberId);
    }
}
