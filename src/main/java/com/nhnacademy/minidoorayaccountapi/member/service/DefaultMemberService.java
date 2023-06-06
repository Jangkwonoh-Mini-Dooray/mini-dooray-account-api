package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.exception.MemberNotFoundException;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultMemberService.class);
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
    @Transactional
    public void createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setPassword(memberDto.getPassword());
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());

//        `member_status_id`        INT DEFAULT 1,
//        `authority_id`     INT DEFAULT 2,
        MemberStatus defaultStatus = memberStatusRepository.findById(1)
                .orElseThrow(MemberNotFoundException::new);
        MemberAuthority defaultAuthority = memberAuthorityRepository.findById(2)
                .orElseThrow(MemberNotFoundException::new);

        member.setMemberStatus(defaultStatus);
        member.setMemberAuthority(defaultAuthority);
        memberRepository.save(member);
    }


    @Override
    @Transactional
    public void updateMember(String memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
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
