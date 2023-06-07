package com.nhnacademy.minidoorayaccountapi.member_status.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundStatusException;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMemberStatusService implements MemberStatusService{
    private final MemberStatusRepository memberStatusRepository;
    @Override
    public MemberStatusDto getMemberStatus(int memberStatusId) {
        return memberStatusRepository.getMemberStatus(memberStatusId);
    }

    @Override
    public void updateMemberStatus(int memberStatusId, MemberStatusDto memberStatusDto) {
        MemberStatus memberStatus = memberStatusRepository.findById(memberStatusId)
                .orElseThrow(() -> new NotFoundStatusException(memberStatusId));
        memberStatus.setStatus(memberStatusDto.getStatus());
        memberStatusRepository.save(memberStatus);
    }
}
