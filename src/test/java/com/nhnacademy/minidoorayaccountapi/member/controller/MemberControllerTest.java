package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidoorayaccountapi.exception.DuplicateMemberIdException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberAuthorityException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberStatusException;
import com.nhnacademy.minidoorayaccountapi.exception.ValidationFailedException;
import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.authority.repository.MemberAuthorityRepository;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member.service.MemberService;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member.status.repository.MemberStatusRepository;
import com.nhnacademy.minidoorayaccountapi.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Member Controller Test")
class MemberControllerTest {
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private MemberStatusRepository memberStatusRepository;
    @MockBean
    private MemberAuthorityRepository memberAuthorityRepository;
    @MockBean
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private GetMemberDto member1Dto;
    private GetMemberDto member2Dto;
    private MemberStatus defaultStatus;
    private MemberAuthority defaultAuthority;

    @BeforeEach
    void setUp() {
        defaultStatus = new MemberStatus();
        defaultStatus.setMemberStatusId(1);
        defaultStatus.setStatus("가입");
        defaultAuthority = new MemberAuthority();
        defaultAuthority.setMemberAuthorityId(2);
        defaultAuthority.setStatus("MEMBER");

        member1Dto = new GetMemberDto();
        member1Dto.setMemberId("member1-id");
        member1Dto.setMemberAuthorityStatus(defaultAuthority.getStatus());
        member1Dto.setPassword("member1-password");
        member1Dto.setEmail("member1@email.com");
        member1Dto.setName("member1-name");

        member2Dto = new GetMemberDto();
        member2Dto.setMemberId("member2-id");
        member2Dto.setMemberAuthorityStatus(defaultAuthority.getStatus());
        member2Dto.setPassword("member2-password");
        member2Dto.setEmail("member2@email.com");
        member2Dto.setName("member2-name");
    }

    @Test
    @Order(1)
    @DisplayName("회원 정보 목록 조회")
    void getMembers() throws Exception {
        given(memberService.getMembers()).willReturn(Arrays.asList(member1Dto, member2Dto));
        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(member1Dto, member2Dto))));
    }

    @Test
    @Order(2)
    @DisplayName("회원 정보 단건 조회 By member_id")
    void getMember() throws Exception {
        given(memberService.getMember("member1-id")).willReturn(member1Dto);
        mockMvc.perform(get("/members/member1-id"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(member1Dto)));
    }

    @Test
    @Order(3)
    @DisplayName("회원 정보 단건 조회 By member_email")
    void getMemberByEmail() throws Exception {
        given(memberService.getMemberByEmail("member1@email.com")).willReturn(member1Dto);

        mockMvc.perform(get("/members/email/member1@email.com"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(member1Dto)));
    }

    @Test
    @Order(4)
    @DisplayName("회원 정보 생성")
    void createMember() throws Exception {
        PostMemberDto memberDto = new PostMemberDto("member3-id", "member3-password", "member3@emil.com", "member3-name");

        Member mockMember = new Member();
        mockMember.setMemberId(memberDto.getMemberId());
        mockMember.setMemberStatus(defaultStatus);
        mockMember.setMemberAuthority(defaultAuthority);
        mockMember.setPassword(memberDto.getPassword());
        mockMember.setEmail(memberDto.getEmail());
        mockMember.setName(memberDto.getName());

        given(memberStatusRepository.findById(anyInt())).willReturn(Optional.of(defaultStatus));
        given(memberAuthorityRepository.findById(anyInt())).willReturn(Optional.of(defaultAuthority));
        given(memberService.createMember(any(PostMemberDto.class), any(MemberStatus.class), any(MemberAuthority.class))).willReturn(mockMember);

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(5)
    @DisplayName("회원 정보 생성 실패 - BindingResult Error")
    void createMemberFailDueToBindingResultError() throws Exception {
        PostMemberDto memberDto = new PostMemberDto("", "", "not_an_email", "");

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException()).isInstanceOfAny(ValidationFailedException.class);
                });
    }

    @Test
    @Order(6)
    @DisplayName("회원 정보 생성 실패 - DuplicateMemberId Error")
    void createMemberFailDueToDuplicateMemberIdError() throws Exception {
        PostMemberDto memberDto = new PostMemberDto("existing-id", "existing-password", "existing@email.com", "existing-name");

        given(memberRepository.existsById("existing-id")).willReturn(true);

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException()).isInstanceOfAny(DuplicateMemberIdException.class);
                });
    }

    @Test
    @Order(7)
    @DisplayName("회원 정보 생성 실패 - NotFoundMemberStatus Error")
    void createMemberFailDueToNotFoundMemberStatusError() throws Exception {
        PostMemberDto memberDto = new PostMemberDto("member3-id", "member3-password", "member3@emil.com", "member3-name");

        given(memberAuthorityRepository.findById(anyInt())).willReturn(Optional.of(defaultAuthority));

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException()).isInstanceOfAny(NotFoundMemberStatusException.class);
                });
    }

    @Test
    @Order(8)
    @DisplayName("회원 정보 생성 실패 - NotFoundMemberAuthority Error")
    void createMemberFailDueToNotFoundMemberAuthorityError() throws Exception {
        PostMemberDto memberDto = new PostMemberDto("member3-id", "member3-password", "member3@emil.com", "member3-name");

        given(memberStatusRepository.findById(anyInt())).willReturn(Optional.of(defaultStatus));

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException()).isInstanceOfAny(NotFoundMemberAuthorityException.class);
                });
    }

    @Test
    @Order(9)
    @DisplayName("회원 정보 수정")
    void updateMember() throws Exception {
        PutMemberDto memberDto = new PutMemberDto("updated-password", "updated@email.com", "updated-name");

        Member mockMember = new Member();
        mockMember.setMemberId("member-id");
        mockMember.setPassword(memberDto.getPassword());
        mockMember.setEmail(memberDto.getEmail());
        mockMember.setName(memberDto.getName());

        given(memberService.updateMember(anyString(), any(PutMemberDto.class))).willReturn(mockMember);

        mockMvc.perform(put("/members/member-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(10)
    @DisplayName("회원 정보 수정 실패 - BindingResult 오류")
    void updateMemberFailDueToBindingResultError() throws Exception {
        PutMemberDto memberDto = new PutMemberDto("", "not_an_email", "   ");

        Member mockMember = new Member();
        mockMember.setMemberId("member-id");
        mockMember.setPassword(memberDto.getPassword());
        mockMember.setEmail(memberDto.getEmail());
        mockMember.setName(memberDto.getName());

        mockMvc.perform(put("/members/member-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException()).isInstanceOfAny(ValidationFailedException.class);
                });
    }

    @Test
    @Order(11)
    @DisplayName("회원 정보 삭제")
    void deleteMember() throws Exception {
        mockMvc.perform(delete("/members/member1-id"))
                .andExpect(status().isNoContent());
    }
}
