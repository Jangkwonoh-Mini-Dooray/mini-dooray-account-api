package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.authority.repository.MemberAuthorityRepository;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member.status.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberStatusRepository memberStatusRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GetMemberDto> getMembers() {
        return memberRepository.getMembers();
    }

    @Override
    @Transactional(readOnly = true)
    public GetMemberDto getMember(String memberId) {
        return memberRepository.getMember(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public GetMemberDto getMemberByEmail(String email) {
        return memberRepository.getMemberByEmail(email);
    }

    @Override
    @Transactional
    public Member createMember(PostMemberDto postMemberDto, MemberStatus defaultStatus, MemberAuthority defaultAuthority) {
        Member member = new Member();
        member.setMemberId(postMemberDto.getMemberId());
        member.setPassword(postMemberDto.getPassword());
        member.setEmail(postMemberDto.getEmail());
        member.setName(postMemberDto.getName());
        member.setMemberStatus(defaultStatus);
        member.setMemberAuthority(defaultAuthority);
        return memberRepository.saveAndFlush(member);
    }

    @Override
    @Transactional
    public Member updateMember(String memberId, PutMemberDto memberDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId));
        member.setPassword(memberDto.getPassword());
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        return memberRepository.saveAndFlush(member);
    }

    @Override
    @Transactional
    public void deleteMember(String memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new NotFoundMemberException(memberId);
        }
        memberRepository.deleteById(memberId);
    }
}
