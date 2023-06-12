//package com.nhnacademy.minidoorayaccountapi.member.service;
//
//import com.nhnacademy.minidoorayaccountapi.exception.*;
//import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
//import com.nhnacademy.minidoorayaccountapi.member.authority.repository.MemberAuthorityRepository;
//import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
//import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
//import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
//import com.nhnacademy.minidoorayaccountapi.member.status.repository.MemberStatusRepository;
//import org.junit.jupiter.api.*;
//import org.mockito.ArgumentCaptor;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
//@DisplayName("Member Service Test")
//class DefaultMemberServiceTest {
//    @Autowired
//    MemberService memberService;
//    @MockBean
//    MemberRepository memberRepository;
//    @MockBean
//    MemberAuthorityRepository memberAuthorityRepository;
//    @MockBean
//    MemberStatusRepository memberStatusRepository;
//
//    private Member member1;
//    private Member member2;
//    private PostMemberDto memberDto;
//    private PutMemberDto putMemberDto;
//    private MemberStatus defaultStatus;
//    private MemberAuthority defaultAuthority;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        defaultStatus = new MemberStatus(1, "가입");
//        defaultAuthority = new MemberAuthority(2, "MEMBER");
//
//        member1 = new Member();
//        member1.setMemberId("member1-id");
//        member1.setMemberAuthority(defaultAuthority);
//        member1.setPassword("member1-password");
//        member1.setEmail("member1@email.com");
//        member1.setName("member1-name");
//
//        member2 = new Member();
//        member2.setMemberId("member2-id");
//        member2.setMemberAuthority(defaultAuthority);
//        member2.setPassword("member1-password");
//        member2.setEmail("member2@email.com");
//        member2.setName("member2-name");
//
//        memberDto = new PostMemberDto(
//                "Dto-id",
//                "Dto-password",
//                "Dto-email",
//                "Dto-name"
//        );
//
//        putMemberDto = new PutMemberDto(
//                "Dto-password",
//                "Dto-email",
//                "Dto-name"
//        );
//
//        given(memberStatusRepository.findById(anyInt())).willReturn(
//                Optional.of(defaultStatus)
//        );
//
//        given(memberAuthorityRepository.findById(anyInt())).willReturn(
//                Optional.of(defaultAuthority)
//        );
//    }
//
//    @Test
//    @Order(1)
//    @DisplayName("회원 정보 목록 조회")
//    void getMembers() {
//        given(memberRepository.getMembers()).willReturn(List.of(
//                new GetMemberDto(
//                        member1.getMemberId(), member1.getMemberAuthority().getStatus(),
//                        member1.getPassword(), member1.getEmail(), member1.getName()),
//                new GetMemberDto(
//                        member2.getMemberId(), member2.getMemberAuthority().getStatus(),
//                        member2.getPassword(), member2.getEmail(), member2.getName())
//        ));
//
//        List<GetMemberDto> memberDtoList = memberService.getMembers();
//
//        assertThat(memberDtoList).isNotEmpty().hasSize(2);
//
//        assertThat(memberDtoList.get(0).getMemberId()).isEqualTo(member1.getMemberId());
//        assertThat(memberDtoList.get(0).getPassword()).isEqualTo(member1.getPassword());
//        assertThat(memberDtoList.get(0).getEmail()).isEqualTo(member1.getEmail());
//        assertThat(memberDtoList.get(0).getName()).isEqualTo(member1.getName());
//        assertThat(memberDtoList.get(0).getMemberAuthorityStatus()).isEqualTo(member1.getMemberAuthority().getStatus());
//
//        assertThat(memberDtoList.get(1).getMemberId()).isEqualTo(member2.getMemberId());
//        assertThat(memberDtoList.get(1).getPassword()).isEqualTo(member2.getPassword());
//        assertThat(memberDtoList.get(1).getEmail()).isEqualTo(member2.getEmail());
//        assertThat(memberDtoList.get(1).getName()).isEqualTo(member2.getName());
//        assertThat(memberDtoList.get(1).getMemberAuthorityStatus()).isEqualTo(member2.getMemberAuthority().getStatus());
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("회원 정보 단건 조회")
//    void getMember() {
//        given(memberRepository.getMember(anyString())).willReturn(
//                new GetMemberDto(
//                        member1.getMemberId(), member1.getMemberAuthority().getStatus(),
//                        member1.getPassword(), member1.getEmail(), member1.getName())
//        );
//
//        GetMemberDto getMemberDto = memberService.getMember(member1.getMemberId());
//
//        assertThat(getMemberDto.getMemberId()).isEqualTo(member1.getMemberId());
//        assertThat(getMemberDto.getPassword()).isEqualTo(member1.getPassword());
//        assertThat(getMemberDto.getEmail()).isEqualTo(member1.getEmail());
//        assertThat(getMemberDto.getName()).isEqualTo(member1.getName());
//        assertThat(getMemberDto.getMemberAuthorityStatus()).isEqualTo(member1.getMemberAuthority().getStatus());
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("회원 정보 생성")
//    void createMember() {
//        memberService.createMember(memberDto);
//
//        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
//        verify(memberRepository).saveAndFlush(captor.capture());
//
//        Member savedMember = captor.getValue();
//
//        assertThat(savedMember.getMemberId()).isEqualTo(memberDto.getMemberId());
//        assertThat(savedMember.getPassword()).isEqualTo(memberDto.getPassword());
//        assertThat(savedMember.getEmail()).isEqualTo(memberDto.getEmail());
//        assertThat(savedMember.getName()).isEqualTo(memberDto.getName());
//        assertThat(savedMember.getMemberStatus().getMemberStatusId()).isEqualTo(defaultStatus.getMemberStatusId());
//        assertThat(savedMember.getMemberAuthority().getMemberAuthorityId())
//                .isEqualTo(defaultAuthority.getMemberAuthorityId());
//    }
//
//    @Test
//    @Order(4)
//    @DisplayName("회원 정보 생성 실패 (default status 존재하지 않음)")
//    void createMemberFailDueToNonExistingStatus() {
//        given(memberStatusRepository.findById(defaultStatus.getMemberStatusId()))
//                .willReturn(Optional.empty());
//
//        assertThatThrownBy(() -> memberService.createMember(memberDto))
//                .isInstanceOf(NotFoundMemberStatusException.class)
//                .hasMessageContaining(String.valueOf(defaultStatus.getMemberStatusId()));
//    }
//
//    @Test
//    @Order(5)
//    @DisplayName("회원 정보 생성 실패 (default authority 존재하지 않음)")
//    void createMemberFailDueToNonExistingAuthority() {
//        given(memberAuthorityRepository.findById(defaultAuthority.getMemberAuthorityId()))
//                .willReturn(Optional.empty());
//
//        assertThatThrownBy(() -> memberService.createMember(memberDto))
//                .isInstanceOf(NotFoundMemberAuthorityException.class)
//                .hasMessageContaining(String.valueOf(defaultAuthority.getMemberAuthorityId()));
//    }
//
//    @Test
//    @Order(5)
//    @DisplayName("회원 정보 생성 실패 (Member ID 중복)")
//    void createMemberFailDueToDuplicateMemberIdException() {
//        // Given
//        given(memberRepository.existsById(anyString())).willReturn(true);
//
//        // When & Then
//        assertThatThrownBy(() -> memberService.createMember(memberDto))
//                .isInstanceOf(DuplicateMemberIdException.class)
//                .hasMessageContaining("Member ID 중복");
//    }
//
//    @Test
//    @Order(6)
//    @DisplayName("회원 정보 수정")
//    void updateMember() {
//        Member beforeUpdateMember = member1;
//
//        given(memberRepository.findById(beforeUpdateMember.getMemberId())).willReturn(
//                Optional.of(beforeUpdateMember)
//        );
//
//        memberService.updateMember(beforeUpdateMember.getMemberId(), putMemberDto);
//
//        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
//        verify(memberRepository).saveAndFlush(captor.capture());
//
//        Member afterUpdateMember = captor.getValue();
//
//        assertThat(afterUpdateMember.getPassword()).isEqualTo(memberDto.getPassword());
//        assertThat(afterUpdateMember.getEmail()).isEqualTo(memberDto.getEmail());
//        assertThat(afterUpdateMember.getName()).isEqualTo(memberDto.getName());
//    }
//
//    @Test
//    @Order(7)
//    @DisplayName("회원 정보 수정 실패 (회원이 존재하지 않음)")
//    void updateMemberNonExistingMember() {
//        String nonExistentMemberId = "non-existing-member-id";
//
//        given(memberRepository.existsById(nonExistentMemberId)).willReturn(false);
//
//        assertThatThrownBy(
//                () -> memberService.updateMember(nonExistentMemberId, putMemberDto))
//                .isInstanceOf(NotFoundMemberException.class)
//                .hasMessageContaining(nonExistentMemberId);
//    }
//
//    @Test
//    @Order(8)
//    @DisplayName("회원 정보 삭제")
//    void deleteMember() {
//        given(memberRepository.existsById(member1.getMemberId())).willReturn(true);
//
//        memberService.deleteMember(member1.getMemberId());
//
//        verify(memberRepository).deleteById(member1.getMemberId());
//    }
//
//    @Test
//    @Order(9)
//    @DisplayName("회원 정보 삭제 실패 (회원이 존재하지 않음)")
//    void deleteMemberNonExistingMember() {
//        String nonExistentMemberId = "non-existing-member-id";
//
//        given(memberRepository.existsById(nonExistentMemberId)).willReturn(false);
//
//        assertThatThrownBy(() -> memberService.deleteMember(nonExistentMemberId))
//                .isInstanceOf(NotFoundMemberException.class)
//                .hasMessageContaining(nonExistentMemberId);
//    }
//}
//
