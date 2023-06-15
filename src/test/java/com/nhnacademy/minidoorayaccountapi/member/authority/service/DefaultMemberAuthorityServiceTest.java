package com.nhnacademy.minidoorayaccountapi.member.authority.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberAuthorityException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member.authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.authority.repository.MemberAuthorityRepository;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Member Authority Service Test")
class DefaultMemberAuthorityServiceTest {
    @Autowired
    MemberAuthorityService memberAuthorityService;
    @MockBean
    MemberRepository memberRepository;
    @MockBean
    MemberAuthorityRepository memberAuthorityRepository;

    private MemberAuthority memberAuthority1;
    private Member member;
    private MemberAuthorityIdDto memberAuthorityIdDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        memberAuthority1 = new MemberAuthority(1, "ADMIN");
        MemberAuthority memberAuthority2 = new MemberAuthority(2, "MEMBER");

        member = new Member();
        ReflectionTestUtils.setField(member, "memberId", "member-id");
        ReflectionTestUtils.setField(member, "memberAuthority", memberAuthority2);

        memberAuthorityIdDto = new MemberAuthorityIdDto(memberAuthority1.getMemberAuthorityId());
    }

    @Test
    @Order(1)
    @DisplayName("회원 권한 정보 수정")
    void updateMemberAuthority() {
        given(memberRepository.findById(member.getMemberId())).willReturn(
                Optional.ofNullable(member)
        );
        given(memberAuthorityRepository.findById(memberAuthority1.getMemberAuthorityId())).willReturn(
                Optional.ofNullable(memberAuthority1)
        );
        memberAuthorityService.updateMemberAuthority(member.getMemberId(), memberAuthorityIdDto);
        assertThat(member.getMemberAuthority()).isEqualTo(memberAuthority1);
    }

    @Test
    @Order(2)
    @DisplayName("회원 권한 정보 수정 살패 - NotFoundMember")
    void updateMemberAuthorityFailDueToNotFoundMemberError() {
        given(memberRepository.findById(member.getMemberId())).willReturn(
                Optional.empty()
        );
        given(memberAuthorityRepository.findById(memberAuthority1.getMemberAuthorityId())).willReturn(
                Optional.ofNullable(memberAuthority1)
        );
        String memberId = member.getMemberId();
        assertThatThrownBy(() -> memberAuthorityService.updateMemberAuthority(memberId, memberAuthorityIdDto))
                .isInstanceOf(NotFoundMemberException.class)
                .hasMessageContaining(memberId);
    }

    @Test
    @Order(3)
    @DisplayName("회원 권한 정보 수정 실패 - NotFoundAuthority")
    void updateMemberAuthorityFailDueToNotFoundAuthorityError() {
        given(memberRepository.findById(member.getMemberId())).willReturn(
                Optional.ofNullable(member)
        );
        given(memberAuthorityRepository.findById(memberAuthority1.getMemberAuthorityId())).willReturn(
                Optional.empty()
        );
        String memberId = member.getMemberId();
        assertThatThrownBy(() -> memberAuthorityService.updateMemberAuthority(memberId, memberAuthorityIdDto))
                .isInstanceOf(NotFoundMemberAuthorityException.class)
                .hasMessageContaining(String.valueOf(memberAuthorityIdDto.getMemberAuthorityId()));
    }
}