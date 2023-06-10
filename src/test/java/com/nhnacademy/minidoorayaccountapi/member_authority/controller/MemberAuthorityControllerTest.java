package com.nhnacademy.minidoorayaccountapi.member_authority.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.service.MemberAuthorityService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberAuthorityController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Member Authority Controller Test")
class MemberAuthorityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private MemberAuthorityService memberAuthorityService;

    private String testMemberId;
    private MemberAuthority memberAuthority;

    @BeforeEach
    void setUp() {
        testMemberId = "test-id";

        memberAuthority = new MemberAuthority();
        memberAuthority.setMemberAuthorityId(1);
        memberAuthority.setStatus("ADMIN");

        Member member = new Member();
        member.setMemberId(testMemberId);
        member.setMemberAuthority(memberAuthority);

        given(memberRepository.findById(testMemberId)).willReturn(Optional.of(member));
    }

    @Test
    @Order(1)
    @DisplayName("멤버 권한 정보 조회")
    void getMemberAuthority() throws Exception {
        mockMvc.perform(get("/members/" + testMemberId + "/authority"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new MemberAuthorityStatusDto(memberAuthority.getStatus()))));
    }

    @Test
    @Order(2)
    @DisplayName("멤버 권한 정보 수정")
    void updateMemberAuthority() throws Exception {
        MemberAuthorityIdDto memberAuthorityIdDto = new MemberAuthorityIdDto(2);

        doNothing().when(memberAuthorityService).updateMemberAuthority(anyString(), any(MemberAuthorityIdDto.class));

        mockMvc.perform(put("/members/" + testMemberId + "/authority")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberAuthorityIdDto)))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value()));
    }
    @Test
    @Order(3)
    @DisplayName("회원 권한 정보 수정 실패 - BindingResultError 오류")
    void updateMemberAuthorityFailDueToBindingResultError() throws Exception {
        MemberAuthorityIdDto memberAuthorityIdDto = new MemberAuthorityIdDto(-1);

        doNothing().when(memberAuthorityService).updateMemberAuthority(anyString(), any(MemberAuthorityIdDto.class));

        mockMvc.perform(put("/members/" + testMemberId + "/authority")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberAuthorityIdDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()));
    }
}
