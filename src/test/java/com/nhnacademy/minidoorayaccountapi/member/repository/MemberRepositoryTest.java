package com.nhnacademy.minidoorayaccountapi.member.repository;

import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

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
        MemberAuthority defaultAuthority = new MemberAuthority(2, "MEMBER");

        testEntityManager.persist(defaultAuthority);

        member1 = new Member();
        ReflectionTestUtils.setField(member1, "memberId", "member1-id");
        ReflectionTestUtils.setField(member1, "password", "member1-password");
        ReflectionTestUtils.setField(member1, "email", "member1@email.com");
        ReflectionTestUtils.setField(member1, "name", "member1-name");
        ReflectionTestUtils.setField(member1, "memberAuthority", defaultAuthority);

        member2 = new Member();
        ReflectionTestUtils.setField(member2, "memberId", "member2-id");
        ReflectionTestUtils.setField(member2, "password", "member2-password");
        ReflectionTestUtils.setField(member2, "email", "member2@email.com");
        ReflectionTestUtils.setField(member2, "name", "member2-name");
        ReflectionTestUtils.setField(member2, "memberAuthority", defaultAuthority);

        testEntityManager.persist(member1);
        testEntityManager.persist(member2);
    }

    @Test
    @Order(1)
    @DisplayName("회원 정보 목록 조회")
    void getMembers() {
        List<GetMemberDto> memberDtoList = memberRepository.getMembers();

        assertThat(memberDtoList).isNotEmpty().hasSize(2);

        assertThat(memberDtoList.get(0).getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(memberDtoList.get(0).getPassword()).isEqualTo(member1.getPassword());
        assertThat(memberDtoList.get(0).getEmail()).isEqualTo(member1.getEmail());
        assertThat(memberDtoList.get(0).getName()).isEqualTo(member1.getName());
        assertThat(memberDtoList.get(0).getMemberAuthorityStatus()).isEqualTo(member1.getMemberAuthority().getStatus());

        assertThat(memberDtoList.get(1).getMemberId()).isEqualTo(member2.getMemberId());
        assertThat(memberDtoList.get(1).getPassword()).isEqualTo(member2.getPassword());
        assertThat(memberDtoList.get(1).getEmail()).isEqualTo(member2.getEmail());
        assertThat(memberDtoList.get(1).getName()).isEqualTo(member2.getName());
        assertThat(memberDtoList.get(1).getMemberAuthorityStatus()).isEqualTo(member2.getMemberAuthority().getStatus());
    }

    @Test
    @Order(2)
    @DisplayName("회원 정보 단건 조회 By member_id")
    void getMember() {
        GetMemberDto getMemberDto = memberRepository.getMember(member1.getMemberId());

        assertThat(getMemberDto.getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(getMemberDto.getPassword()).isEqualTo(member1.getPassword());
        assertThat(getMemberDto.getEmail()).isEqualTo(member1.getEmail());
        assertThat(getMemberDto.getName()).isEqualTo(member1.getName());
        assertThat(getMemberDto.getMemberAuthorityStatus()).isEqualTo(member1.getMemberAuthority().getStatus());
    }

    @Test
    @Order(3)
    @DisplayName("회원 정보 단건 조회 By member_email")
    void getMemberByEmail() {
        GetMemberDto getMemberDto = memberRepository.getMemberByEmail(member1.getEmail());

        assertThat(getMemberDto.getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(getMemberDto.getPassword()).isEqualTo(member1.getPassword());
        assertThat(getMemberDto.getEmail()).isEqualTo(member1.getEmail());
        assertThat(getMemberDto.getName()).isEqualTo(member1.getName());
        assertThat(getMemberDto.getMemberAuthorityStatus()).isEqualTo(member1.getMemberAuthority().getStatus());
    }
}