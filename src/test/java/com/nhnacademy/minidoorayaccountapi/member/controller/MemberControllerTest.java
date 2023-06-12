//package com.nhnacademy.minidoorayaccountapi.member.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nhnacademy.minidoorayaccountapi.exception.ValidationFailedException;
//import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
//import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
//import com.nhnacademy.minidoorayaccountapi.member.service.MemberService;
//import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(MemberController.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DisplayName("Member Controller Test")
//class MemberControllerTest {
//    @MockBean
//    private MemberService memberService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private GetMemberDto member1Dto;
//    private GetMemberDto member2Dto;
//    private MemberStatus defaultStatus;
//    private MemberAuthority defaultAuthority;
//
//    @BeforeEach
//    void setUp() {
//        defaultStatus = new MemberStatus(1, "가입");
//        defaultAuthority = new MemberAuthority(2, "MEMBER");
//
//        member1Dto = new GetMemberDto(
//                "member1-id",
//                defaultAuthority.getStatus(),
//                "member1-password",
//                "member1@email.com", "member1-name");
//        member2Dto = new GetMemberDto(
//                "member2-id",
//                defaultAuthority.getStatus(),
//                "member2-password",
//                "member2@email.com", "member2-name");
//    }
//
//    @Test
//    @Order(1)
//    @DisplayName("회원 정보 목록 조회")
//    void getMembers() throws Exception {
//        given(memberService.getMembers()).willReturn(Arrays.asList(member1Dto, member2Dto));
//
//        mockMvc.perform(get("/members"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(member1Dto, member2Dto))));
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("회원 정보 단건 조회")
//    void getMember() throws Exception {
//        given(memberService.getMember("member1-id")).willReturn(member1Dto);
//
//        mockMvc.perform(get("/members/member1-id"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(member1Dto)));
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("회원 정보 생성")
//    void createMember() throws Exception {
//        PostMemberDto memberDto = new PostMemberDto("member3-id", "member3-password", "member3@emil.com", "member3-name");
//        Member mockMember = new Member();
//        mockMember.setMemberId(memberDto.getMemberId());
//        mockMember.setMemberStatus(defaultStatus);
//        mockMember.setMemberAuthority(defaultAuthority);
//        mockMember.setPassword(memberDto.getPassword());
//        mockMember.setEmail(memberDto.getEmail());
//        mockMember.setName(memberDto.getName());
//
//        given(memberService.createMember(any(PostMemberDto.class))).willReturn(mockMember);
//
//        mockMvc.perform(post("/members")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(memberDto)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @Order(4)
//    @DisplayName("회원 정보 생성 실패 - BindingResult 오류")
//    void createMemberFailDueToBindingResultError() throws Exception {
//        PostMemberDto memberDto = new PostMemberDto("", "", "not_an_email", "");
//
//        mockMvc.perform(post("/members")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(memberDto)))
//                .andExpect(status().isBadRequest())
//                .andExpect(result -> {
//                    assertThat(result.getResolvedException()).isInstanceOfAny(ValidationFailedException.class);
//                });
//    }
//
//    @Test
//    @Order(5)
//    @DisplayName("회원 정보 수정")
//    void updateMember() throws Exception {
//        PutMemberDto memberDto = new PutMemberDto("updated-password", "updated@email.com", "updated-name");
//
//        Member mockMember = new Member();
//        mockMember.setMemberId("member-id");
//        mockMember.setPassword(memberDto.getPassword());
//        mockMember.setEmail(memberDto.getEmail());
//        mockMember.setName(memberDto.getName());
//
//        given(memberService.updateMember(anyString(), any(PutMemberDto.class))).willReturn(mockMember);
//
//        mockMvc.perform(put("/members/member-id")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(memberDto)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @Order(6)
//    @DisplayName("회원 정보 수정 실패 - BindingResult 오류")
//    void updateMemberFailDueToBindingResultError() throws Exception {
//        PutMemberDto memberDto = new PutMemberDto("", "not_an_email", "   ");
//
//        Member mockMember = new Member();
//        mockMember.setMemberId("member-id");
//        mockMember.setPassword(memberDto.getPassword());
//        mockMember.setEmail(memberDto.getEmail());
//        mockMember.setName(memberDto.getName());
//
//        mockMvc.perform(post("/members")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(memberDto)))
//                .andExpect(status().isBadRequest())
//                .andExpect(result -> {
//                    assertThat(result.getResolvedException()).isInstanceOfAny(ValidationFailedException.class);
//                });
//    }
//
//    @Test
//    @Order(7)
//    @DisplayName("회원 정보 삭제")
//    void deleteMember() throws Exception {
//        mockMvc.perform(delete("/members/member1-id"))
//                .andExpect(status().isNoContent());
//    }
//}
