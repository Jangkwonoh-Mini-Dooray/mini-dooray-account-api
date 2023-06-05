package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.exception.MemberNotFoundException;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MemberDto> getMembers() {
        return memberRepository.getMembersBy();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto getMember(String memberId) {
        return memberRepository.getMemberByMemberId(memberId);
    }

    @Override
    @Transactional
    public void createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setPassword(memberDto.getPassword());
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        memberRepository.save(member);
    }


    @Override
    @Transactional
    public void updateMember(String memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        member.setPassword(memberDto.getPassword());
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        memberRepository.save(member);
    }
    

    @Override
    @Transactional
    public void deleteMember(String memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberNotFoundException();
        }
        memberRepository.deleteById(memberId);
    }
}
