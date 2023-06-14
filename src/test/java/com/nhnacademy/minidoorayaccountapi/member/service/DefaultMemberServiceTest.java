package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.exception.*;
import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Member Service Test")
class DefaultMemberServiceTest {
    @InjectMocks
    DefaultMemberService memberService;
    @Mock
    MemberRepository memberRepository;

    private Member member1;
    private Member member2;
    private GetMemberDto getMember1Dto;
    private GetMemberDto getMember2Dto;
    private PostMemberDto postMemberDto;
    private PutMemberDto putMemberDto;
    private MemberStatus defaultStatus;
    private MemberAuthority defaultAuthority;

    @BeforeEach
    void setUp() {
        defaultStatus = new MemberStatus(1, "가입");
        defaultAuthority = new MemberAuthority(2, "MEMBER");

        member1 = new Member();
        member1.setMemberId("member1-id");
        member1.setMemberAuthority(defaultAuthority);
        member1.setPassword("member1-password");
        member1.setEmail("member1@email.com");
        member1.setName("member1-name");

        member2 = new Member();
        member2.setMemberId("member2-id");
        member2.setMemberAuthority(defaultAuthority);
        member2.setPassword("member1-password");
        member2.setEmail("member2@email.com");
        member2.setName("member2-name");

        getMember1Dto = new GetMemberDto();
        getMember1Dto.setMemberId(member1.getMemberId());
        getMember1Dto.setMemberAuthorityStatus(member1.getMemberAuthority().getStatus());
        getMember1Dto.setPassword(member1.getPassword());
        getMember1Dto.setEmail(member1.getEmail());
        getMember1Dto.setName(member1.getName());

        getMember2Dto = new GetMemberDto();
        getMember2Dto.setMemberId(member2.getMemberId());
        getMember2Dto.setMemberAuthorityStatus(member2.getMemberAuthority().getStatus());
        getMember2Dto.setPassword(member2.getPassword());
        getMember2Dto.setEmail(member2.getEmail());
        getMember2Dto.setName(member2.getName());

        postMemberDto = new PostMemberDto();
        postMemberDto.setMemberId("Dto-id");
        postMemberDto.setPassword("Dto-password");
        postMemberDto.setEmail("Dto@email.com");
        postMemberDto.setName("Dto-name");

        putMemberDto = new PutMemberDto();
        putMemberDto.setPassword("Dto-password");
        putMemberDto.setEmail("Dto@email.com");
        putMemberDto.setName("Dto-name");
    }

    @Test
    @Order(1)
    @DisplayName("회원 정보 목록 조회")
    void getMembers() {
        given(memberRepository.getMembers()).willReturn(
                List.of(getMember1Dto, getMember2Dto)
        );

        List<GetMemberDto> memberDtoList = memberService.getMembers();

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
        given(memberRepository.getMember(anyString())).willReturn(getMember1Dto);

        GetMemberDto getMemberDto = memberService.getMember(member1.getMemberId());

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
        given(memberRepository.getMemberByEmail(anyString())).willReturn(getMember1Dto);

        GetMemberDto getMemberDto = memberService.getMemberByEmail(member1.getEmail());

        assertThat(getMemberDto.getMemberId()).isEqualTo(member1.getMemberId());
        assertThat(getMemberDto.getPassword()).isEqualTo(member1.getPassword());
        assertThat(getMemberDto.getEmail()).isEqualTo(member1.getEmail());
        assertThat(getMemberDto.getName()).isEqualTo(member1.getName());
        assertThat(getMemberDto.getMemberAuthorityStatus()).isEqualTo(member1.getMemberAuthority().getStatus());
    }

    @Test
    @Order(4)
    @DisplayName("회원 정보 생성")
    void createMember() {
        memberService.createMember(postMemberDto, defaultStatus, defaultAuthority);

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository).saveAndFlush(captor.capture());

        Member savedMember = captor.getValue();

        assertThat(savedMember.getMemberId()).isEqualTo(postMemberDto.getMemberId());
        assertThat(savedMember.getPassword()).isEqualTo(postMemberDto.getPassword());
        assertThat(savedMember.getEmail()).isEqualTo(postMemberDto.getEmail());
        assertThat(savedMember.getName()).isEqualTo(postMemberDto.getName());
        assertThat(savedMember.getMemberStatus().getMemberStatusId()).isEqualTo(defaultStatus.getMemberStatusId());
        assertThat(savedMember.getMemberAuthority().getMemberAuthorityId())
                .isEqualTo(defaultAuthority.getMemberAuthorityId());
    }

    @Test
    @Order(5)
    @DisplayName("회원 정보 수정")
    void updateMember() {
        Member beforeUpdateMember = member1;

        given(memberRepository.findById(beforeUpdateMember.getMemberId())).willReturn(
                Optional.of(beforeUpdateMember)
        );

        memberService.updateMember(beforeUpdateMember.getMemberId(), putMemberDto);

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository).saveAndFlush(captor.capture());

        Member afterUpdateMember = captor.getValue();

        assertThat(afterUpdateMember.getPassword()).isEqualTo(postMemberDto.getPassword());
        assertThat(afterUpdateMember.getEmail()).isEqualTo(postMemberDto.getEmail());
        assertThat(afterUpdateMember.getName()).isEqualTo(postMemberDto.getName());
    }

    @Test
    @Order(6)
    @DisplayName("회원 정보 수정 실패 (회원이 존재하지 않음)")
    void updateMemberNonExistingMember() {
        String nonExistentMemberId = "non-existing-member-id";
        assertThatThrownBy(
                () -> memberService.updateMember(nonExistentMemberId, putMemberDto))
                .isInstanceOf(NotFoundMemberException.class)
                .hasMessageContaining(nonExistentMemberId);
    }

    @Test
    @Order(7)
    @DisplayName("회원 정보 삭제")
    void deleteMember() {
        given(memberRepository.existsById(member1.getMemberId())).willReturn(true);
        memberService.deleteMember(member1.getMemberId());
        verify(memberRepository).deleteById(member1.getMemberId());
    }

    @Test
    @Order(8)
    @DisplayName("회원 정보 삭제 실패 (회원이 존재하지 않음)")
    void deleteMemberNonExistingMember() {
        String nonExistentMemberId = "non-existing-member-id";
        given(memberRepository.existsById(nonExistentMemberId)).willReturn(false);
        assertThatThrownBy(() -> memberService.deleteMember(nonExistentMemberId))
                .isInstanceOf(NotFoundMemberException.class)
                .hasMessageContaining(nonExistentMemberId);
    }
}

