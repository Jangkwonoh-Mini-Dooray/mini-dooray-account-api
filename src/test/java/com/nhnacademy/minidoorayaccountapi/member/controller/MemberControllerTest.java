package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.service.MemberService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Member Controller Test")
class MemberControllerTest {
    @MockBean
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private GetMemberDto member1Dto;
    private GetMemberDto member2Dto;

    @BeforeEach
    void setUp() {
        member1Dto = new GetMemberDto("member1-id", "member1-email", "member1-name");
        member2Dto = new GetMemberDto("member2-id", "member2-email", "member2-name");
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
    @DisplayName("회원 정보 단건 조회")
    void getMember() throws Exception {
        given(memberService.getMember("member1-id")).willReturn(member1Dto);

        mockMvc.perform(get("/members/member1-id"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(member1Dto)));
    }

    @Test
    @Order(3)
    @DisplayName("회원 정보 생성")
    void createMember() throws Exception {
        MemberDto memberDto = new MemberDto("member3-id", "member3-email", "password", "member3-name");

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("회원 정보 수정")
    void updateMember() throws Exception {
        MemberDto memberDto = new MemberDto("member1-id", "updated-email", "updated-password", "updated-name");

        mockMvc.perform(put("/members/member1-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    @DisplayName("회원 정보 삭제")
    void deleteMember() throws Exception {
        mockMvc.perform(delete("/members/member1-id"))
                .andExpect(status().isOk());
    }
}
