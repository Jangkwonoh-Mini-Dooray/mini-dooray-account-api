package com.nhnacademy.minidoorayaccountapi.member.repository;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Member Repository Test")
class MemberRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Order(1)
    @DisplayName("회원 정보 목록 조회 test")
    void getMembers() {
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setMemberId("id1");
        member1.setEmail("email1");
        member1.setName("name1");

        member2.setMemberId("id2");
        member2.setEmail("email1");
        member2.setName("name2");

        testEntityManager.persist(member1);
        testEntityManager.persist(member2);

        List<GetMemberDto> getMemberDtoList = memberRepository.getMembers();

        assertThat(getMemberDtoList).isNotEmpty().hasSize(2);

        assertThat(getMemberDtoList.get(0).getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(getMemberDtoList.get(0).getEmail()).isEqualTo(member1.getEmail());
        assertThat(getMemberDtoList.get(0).getName()).isEqualTo(member1.getName());

        assertThat(getMemberDtoList.get(1).getMemberId()).isEqualTo(member2.getMemberId());
        assertThat(getMemberDtoList.get(1).getEmail()).isEqualTo(member2.getEmail());
        assertThat(getMemberDtoList.get(1).getName()).isEqualTo(member2.getName());
    }

    @Test
    @DisplayName("회원 정보 단건 조회 test")
    void getMemberByMemberId() {
        Member member = new Member();

        member.setMemberId("id");
        member.setEmail("email");
        member.setName("name");

        testEntityManager.persist(member);

        GetMemberDto getMemberDto = memberRepository.getMember(member.getMemberId());

        assertThat(getMemberDto.getMemberId()).isEqualTo(member.getMemberId());
        assertThat(getMemberDto.getEmail()).isEqualTo(member.getEmail());
        assertThat(getMemberDto.getName()).isEqualTo(member.getName());
    }
}