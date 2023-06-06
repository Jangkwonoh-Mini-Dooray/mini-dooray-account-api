package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.repository.MemberStatusRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Member Service Test")
class DefaultMemberServiceTest {
    @Autowired
    MemberService memberService;
    @MockBean
    MemberRepository memberRepository;
    @MockBean
    MemberAuthorityRepository memberAuthorityRepository;
    @MockBean
    MemberStatusRepository memberStatusRepository;

    private Member member1;
    private Member member2;
    private MemberDto memberDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        member1 = new Member();
        member1.setMemberId("test-id1");
        member1.setEmail("test-email1");
        member1.setName("test-name1");

        member2 = new Member();
        member2.setMemberId("test-id2");
        member2.setEmail("test-email2");
        member2.setName("test-name2");

        memberDto = new MemberDto(
                "test-id",
                "test-password",
                "test-email",
                "test-name"
        );
    }

    @Test
    @Order(1)
    @DisplayName("회원 정보 목록 조회 test")
    void getMembers() {
        given(memberRepository.getMembers()).willReturn(List.of(
                new GetMemberDto(member1.getMemberId(), member1.getEmail(), member1.getName()),
                new GetMemberDto(member2.getMemberId(), member2.getEmail(), member2.getName())
        ));

        List<GetMemberDto> memberDtoList = memberService.getMembers();

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
    @DisplayName("회원 정보 단건 조회 service test")
    void getMember() {
        given(memberRepository.getMember(anyString())).willReturn(
                new GetMemberDto(member1.getMemberId(), member1.getEmail(), member1.getName())
        );

        GetMemberDto memberDto = memberService.getMember(member1.getMemberId());

        assertThat(memberDto.getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(memberDto.getEmail()).isEqualTo(member1.getEmail());
        assertThat(memberDto.getName()).isEqualTo(member1.getName());
    }

    @Test
    @Order(3)
    @DisplayName("회원 정보 생성 service test")
    void createMember() {
        MemberStatus defaultStatus = new MemberStatus(1, "가입");
        MemberAuthority defaultAuthority = new MemberAuthority(2, "MEMBER");

        given(memberStatusRepository.findById(anyInt())).willReturn(
                Optional.of(defaultStatus)
        );

        given(memberAuthorityRepository.findById(anyInt())).willReturn(
                Optional.of(defaultAuthority)
        );

        memberService.createMember(memberDto);

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository).save(captor.capture());

        Member savedMember = captor.getValue();

        assertThat(savedMember.getMemberId()).isEqualTo(memberDto.getMemberId());
        assertThat(savedMember.getPassword()).isEqualTo(memberDto.getPassword());
        assertThat(savedMember.getEmail()).isEqualTo(memberDto.getEmail());
        assertThat(savedMember.getName()).isEqualTo(memberDto.getName());
        assertThat(savedMember.getMemberStatus().getMemberStatusId()).isEqualTo(defaultStatus.getMemberStatusId());
        assertThat(savedMember.getMemberAuthority().getAuthorityId()).isEqualTo(defaultAuthority.getAuthorityId());
    }

    @Test
    @Order(4)
    @DisplayName("회원 정보 수정 test")
    void updateMember() {

    }

    @Test
    @Order(5)
    @DisplayName("회원 정보 삭제 test")
    void deleteMember() {

    }
}

