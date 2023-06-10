package com.nhnacademy.minidoorayaccountapi.member_authority.service;

import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
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
@DisplayName("Member Authority Service Test")
class DefaultMemberAuthorityServiceTest {
    @Autowired
    MemberAuthorityService memberAuthorityService;
    @MockBean
    MemberAuthorityRepository memberAuthorityRepository;
    @MockBean
    MemberRepository memberRepository;

    private MemberAuthority memberAuthority1;
    private MemberAuthority memberAuthority2;
    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        memberAuthority1 = new MemberAuthority(1, "ADMIN");
        memberAuthority2 = new MemberAuthority(2, "MEMBER");

        member = new Member();
        member.setMemberId("member-id");
        member.setMemberAuthority(memberAuthority2);

        given(memberRepository.findById(member.getMemberId())).willReturn(
                Optional.ofNullable(member)
        );

        given(memberAuthorityRepository.findById(memberAuthority1.getMemberAuthorityId())).willReturn(
                Optional.ofNullable(memberAuthority1)
        );
    }

    @Test
    @Order(1)
    @DisplayName("회원 권한 수정")
    void updateMemberAuthority() {
        MemberAuthorityIdDto memberAuthorityIdDto = new MemberAuthorityIdDto(memberAuthority1.getMemberAuthorityId());

        memberAuthorityService.updateMemberAuthority(member.getMemberId(), memberAuthorityIdDto);

        assertThat(member.getMemberAuthority()).isEqualTo(memberAuthority1);
    }
}