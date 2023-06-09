package com.nhnacademy.minidoorayaccountapi.member_status.service;

import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    MemberStatusRepository memberStatusRepository;

    private MemberStatus memberStatus1;
    private MemberStatus memberStatus2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        memberStatus1 = new MemberStatus(1, "가입");
        memberStatus2 = new MemberStatus(2, "탈퇴");
    }

    @Test
    @Order(2)
    @DisplayName("회원 권한 정보 수정")
    void updateMemberStatus() {
        MemberStatusDto memberStatusDto = new MemberStatusDto(memberStatus1.getStatus());

        given(memberStatusRepository.findById(memberStatus2.getMemberStatusId())).willReturn(
                Optional.ofNullable(memberStatus2)
        );

        memberStatusService.updateMemberStatus(memberStatus2.getMemberStatusId(), memberStatusDto);

        ArgumentCaptor<MemberStatus> captor = ArgumentCaptor.forClass(MemberStatus.class);
        verify(memberStatusRepository).save(captor.capture());

        MemberStatus savedMemberStatus = captor.getValue();

        assertThat(savedMemberStatus.getStatus()).isEqualTo(memberStatusDto.getStatus());
    }
}