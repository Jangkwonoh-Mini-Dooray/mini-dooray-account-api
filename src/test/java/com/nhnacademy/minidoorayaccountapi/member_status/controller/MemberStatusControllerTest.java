package com.nhnacademy.minidoorayaccountapi.member_status.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.service.MemberStatusService;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberStatusController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Member Status Controller Test")
class MemberStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private MemberStatusService memberStatusService;

    private String testMemberId;
    private MemberStatus memberStatus;

    @BeforeEach
    void setUp() {
        testMemberId = "test-id";

        memberStatus = new MemberStatus();
        memberStatus.setMemberStatusId(1);
        memberStatus.setStatus("가입");

        Member member = new Member();
        member.setMemberId(testMemberId);
        member.setMemberStatus(memberStatus);

        given(memberRepository.findById(testMemberId)).willReturn(Optional.of(member));
    }

    @Test
    @Order(1)
    @DisplayName("멤버 상태 정보 조회")
    void getMemberStatus() throws Exception {
        mockMvc.perform(get("/members/" + testMemberId + "/status"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new MemberStatusDto(memberStatus.getStatus()))));
    }

    @Test
    @Order(2)
    @DisplayName("멤버 상태 정보 수정")
    void updateMemberStatus() throws Exception {
        MemberStatusDto memberStatusDto = new MemberStatusDto("휴면");

        doNothing().when(memberStatusService).updateMemberStatus(anyInt(), any(MemberStatusDto.class));

        mockMvc.perform(put("/members/" + testMemberId + "/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberStatusDto)))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value()));
    }

    @Test
    @Order(3)
    @DisplayName("회원 상태 정보 수정 실패 - NotFoundMember 오류")
    void updateMemberStatusFailDueToNotFoundMemberException() throws Exception {
        MemberStatusDto memberStatusDto = new MemberStatusDto("휴면");
        String nonMemberId = "non-member-id";

        mockMvc.perform(put("/members/" + nonMemberId + "/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberStatusDto)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @Order(4)
    @DisplayName("회원 상태 정보 수정 실패 - BindingResultError 오류")
    void updateMemberStatusFailDueToBindingResultError() throws Exception {
        MemberStatusDto memberStatusDto = new MemberStatusDto("   ");

        mockMvc.perform(put("/members/" + testMemberId + "/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberStatusDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()));
    }
}
