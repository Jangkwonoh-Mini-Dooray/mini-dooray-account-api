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

    private Member member1;
    private Member member2;

    @BeforeEach
    void setUp() {
        member1 = new Member();
        member1.setMemberId("member1-id");
        member1.setEmail("member1-email");
        member1.setName("member1-name");

        member2 = new Member();
        member2.setMemberId("member2-id");
        member2.setEmail("member2-email");
        member2.setName("member2-name");
    }

    @Test
    @Order(1)
    @DisplayName("회원 정보 목록 조회")
    void getMembers() {
        testEntityManager.persist(member1);
        testEntityManager.persist(member2);

        List<GetMemberDto> memberDtoList = memberRepository.getMembers();

        assertThat(memberDtoList).isNotEmpty().hasSize(2);

        assertThat(memberDtoList.get(0).getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(memberDtoList.get(0).getEmail()).isEqualTo(member1.getEmail());
        assertThat(memberDtoList.get(0).getName()).isEqualTo(member1.getName());

        assertThat(memberDtoList.get(1).getMemberId()).isEqualTo(member2.getMemberId());
        assertThat(memberDtoList.get(1).getEmail()).isEqualTo(member2.getEmail());
        assertThat(memberDtoList.get(1).getName()).isEqualTo(member2.getName());
    }

    @Test
    @Order(2)
    @DisplayName("회원 정보 단건 조회")
    void getMember() {
        testEntityManager.persist(member1);

        GetMemberDto getMemberDto = memberRepository.getMember(member1.getMemberId());

        assertThat(getMemberDto.getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(getMemberDto.getEmail()).isEqualTo(member1.getEmail());
        assertThat(getMemberDto.getName()).isEqualTo(member1.getName());
    }
}