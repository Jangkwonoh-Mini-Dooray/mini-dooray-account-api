//package com.nhnacademy.minidoorayaccountapi.member.service;
//
//import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
//import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class DefaultMemberServiceTest {
//
//    @Autowired
//    private DefaultMemberService memberService;
//
//    @MockBean
//    private MemberRepository memberRepository;
//
//    @Test
//    public void getMembersTest() {
//        Member member1 = new Member();
//        member1.setMemberId("member1");
//        member1.setEmail("member1@test.com");
//        member1.setName("Member 1");
//
//        Member member2 = new Member();
//        member2.setMemberId("member2");
//        member2.setEmail("member2@test.com");
//        member2.setName("Member 2");
//
//        when(memberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));
//
//        List<MemberDto> memberDtoList = memberService.getMembers();
//
//        assertEquals(2, memberDtoList.size());
//
//        assertEquals("member1", memberDtoList.get(0).getMemberId());
//        assertEquals("member1@test.com", memberDtoList.get(0).getEmail());
//        assertEquals("Member 1", memberDtoList.get(0).getName());
//
//        assertEquals("member2", memberDtoList.get(1).getMemberId());
//        assertEquals("member2@test.com", memberDtoList.get(1).getEmail());
//        assertEquals("Member 2", memberDtoList.get(1).getName());
//    }
//
//    @Test
//    public void getMemberTest() {
//        Member member = new Member();
//        member.setMemberId("member");
//        member.setEmail("member@test.com");
//        member.setName("Member");
//
//        when(memberRepository.findById("member")).thenReturn(Optional.of(member));
//
//        MemberDto retrievedMemberDto = memberService.getMember("member");
//
//        assertEquals(retrievedMemberDto.getMemberId(), "member");
//        assertEquals(retrievedMemberDto.getEmail(), "member@test.com");
//        assertEquals(retrievedMemberDto.getName(), "Member");
//    }
//
////    @Test
////    public void createMemberTest() {
////        MemberDto memberDto = new MemberDto("member", "password", "email", "name");
////        memberService.createMember(memberDto);
////
////        MemberDto createdMemberDto = memberService.getMember("member");
////
////        assertEquals(createdMemberDto.getMemberId(), memberDto.getMemberId());
////    }
//}
//
//
