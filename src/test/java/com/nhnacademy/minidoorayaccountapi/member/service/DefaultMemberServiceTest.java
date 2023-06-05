package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class DefaultMemberServiceTest {
    @Autowired
    DefaultMemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    void getMembersTest() throws Exception {
        MemberDto memberDto = new MemberDto();
        memberDto.setId("testId");
        memberDto.setPassword("testPassword");
        memberDto.setEmail("testEmail");
        memberDto.setName("testName");

        given(memberRepository.findAllMemberDto()).willReturn(Collections.singletonList(memberDto));

        List<MemberDto> members = memberService.getMembers();

        assertThat(members).hasSize(1);
        MemberDto actualMemberDto = members.get(0);
        assertThat(actualMemberDto.getId()).isEqualTo("testId");
        assertThat(actualMemberDto.getPassword()).isEqualTo("testPassword");
        assertThat(actualMemberDto.getEmail()).isEqualTo("testEmail");
        assertThat(actualMemberDto.getName()).isEqualTo("testName");
    }
}
