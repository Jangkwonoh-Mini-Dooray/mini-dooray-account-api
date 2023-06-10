package com.nhnacademy.minidoorayaccountapi.member_status.service;

import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusIdDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.repository.MemberStatusRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Member Status Service Test")
class DefaultMemberStatusServiceTest {
    @Autowired
    MemberStatusService memberStatusService;
    @MockBean
    MemberRepository memberRepository;
    @MockBean
    MemberStatusRepository memberStatusRepository;

    private MemberStatus memberStatus1;
    private MemberStatus memberStatus2;
    private Member member;
    private MemberStatusIdDto memberStatusIdDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        memberStatus1 = new MemberStatus(1, "가입");
        memberStatus2 = new MemberStatus(2, "휴면");

        member = new Member();
        member.setMemberId("member-id");
        member.setMemberStatus(memberStatus1);

        memberStatusIdDto = new MemberStatusIdDto(memberStatus2.getMemberStatusId());
    }

    @Test
    @Order(1)
    @DisplayName("회원 권한 정보 수정")
    void updateMemberStatus() {
        given(memberRepository.findById(member.getMemberId())).willReturn(
                Optional.ofNullable(member)
        );
        given(memberStatusRepository.findById(memberStatus2.getMemberStatusId())).willReturn(
                Optional.ofNullable(memberStatus2)
        );
        memberStatusService.updateMemberStatus(member.getMemberId(), memberStatusIdDto);
        assertThat(member.getMemberStatus()).isEqualTo(memberStatus2);
    }

}