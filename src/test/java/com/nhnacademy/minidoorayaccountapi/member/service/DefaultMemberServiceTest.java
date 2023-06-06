//package com.nhnacademy.minidoorayaccountapi.member.service;
//
//import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
//import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
//import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
//import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
//import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
//import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
//import com.nhnacademy.minidoorayaccountapi.member_status.service.MemberStatusService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//
//
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
//class DefaultMemberServiceTest {
//    @Autowired
//    MemberService memberService;
//    @MockBean
//    MemberRepository memberRepository;
//    @MockBean
//    MemberAuthorityRepository memberAuthorityRepository;
//    @MockBean
//    MemberStatusService memberStatusService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("회원 정보 목록 조회 service test")
//    void getMembers() {
//        Member member1 = new Member();
//        Member member2 = new Member();
//
//        member1.setMemberId("id1");
//        member1.setPassword("password1");
//        member1.setEmail("email1");
//        member1.setName("name1");
//
//        member2.setMemberId("id2");
//        member2.setPassword("password2");
//        member2.setEmail("email2");
//        member2.setName("name2");
//
//        given(memberRepository.getMembersBy()).willReturn(List.of(
//                new MemberDto() {
//                    @Override
//                    public String getMemberId() {
//                        return member1.getMemberId();
//                    }
//                    @Override
//                    public String getPassword() {
//                        return member1.getPassword();
//                    }
//                    @Override
//                    public String getEmail() {
//                        return member1.getEmail();
//                    }
//                    @Override
//                    public String getName() {
//                        return member1.getName();
//                    }},
//                new MemberDto() {
//                    @Override
//                    public String getMemberId() {
//                        return member2.getMemberId();
//                    }
//                    @Override
//                    public String getPassword() {
//                        return member2.getPassword();
//                    }
//                    @Override
//                    public String getEmail() {
//                        return member2.getEmail();
//                    }
//                    @Override
//                    public String getName() {
//                        return member2.getName();
//                    }}
//                ));
//
//        List<MemberDto> memberDtoList = memberService.getMembers();
//
//        Assertions.assertThat(memberDtoList).isNotEmpty().hasSize(2);
//
//        Assertions.assertThat(memberDtoList.get(0).getMemberId()).isEqualTo(member1.getMemberId());
//        Assertions.assertThat(memberDtoList.get(0).getEmail()).isEqualTo(member1.getEmail());
//        Assertions.assertThat(memberDtoList.get(0).getName()).isEqualTo(member1.getName());
//
//        Assertions.assertThat(memberDtoList.get(1).getMemberId()).isEqualTo(member2.getMemberId());
//        Assertions.assertThat(memberDtoList.get(1).getEmail()).isEqualTo(member2.getEmail());
//        Assertions.assertThat(memberDtoList.get(1).getName()).isEqualTo(member2.getName());
//    }
//
//    @Test
//    @DisplayName("회원 정보 단건 조회 service test")
//    void getMember() {
//        Member member = new Member();
//
//        member.setMemberId("id");
//        member.setPassword("password");
//        member.setEmail("email");
//        member.setName("name");
//
//        given(memberRepository.getMemberByMemberId(anyString())).willReturn(
//                new MemberDto() {
//                    @Override
//                    public String getMemberId() {
//                        return member.getMemberId();
//                    }
//                    @Override
//                    public String getPassword() {
//                        return member.getPassword();
//                    }
//                    @Override
//                    public String getEmail() {
//                        return member.getEmail();
//                    }
//                    @Override
//                    public String getName() {
//                        return member.getName();
//                    }}
//        );
//
//        MemberDto memberDto = memberService.getMember(member.getMemberId());
//
//        Assertions.assertThat(memberDto.getMemberId()).isEqualTo(member.getMemberId());
//        Assertions.assertThat(memberDto.getEmail()).isEqualTo(member.getEmail());
//        Assertions.assertThat(memberDto.getName()).isEqualTo(member.getName());
//    }
//
//    @Test
//    @DisplayName("회원 정보 생성 service test")
//    void createMember() {
//        MemberDto memberDto = new MemberDto() {
//            @Override
//            public String getMemberId() {
//                return "id";
//            }
//            @Override
//            public String getPassword() {
//                return "password";
//            }
//            @Override
//            public String getEmail() {
//                return "email";
//            }
//            @Override
//            public String getName() {
//                return "name";
//            }
//        };
//        MemberStatus defaultStatus = new MemberStatus(1, "가입");
//        MemberAuthority defaultAuthority = new MemberAuthority(2, "MEMBER");
//
//        memberService.createMember(memberDto);
//        given(memberRepository.save(any(Member.class))).willReturn(
//            new Member(memberDto.getMemberId(),
//                    defaultStatus,
//                    defaultAuthority,
//                    memberDto.getPassword(),
//                memberDto.getEmail(),
//                memberDto.getName())
//        );
//
//        Assertions.assertThat(createdMember).isNotNull();
//        Assertions.assertThat(createdMember.getMemberId()).isEqualTo(memberDto.getMemberId());
//        Assertions.assertThat(createdMember.getPassword()).isEqualTo(memberDto.getPassword());
//        Assertions.assertThat(createdMember.getEmail()).isEqualTo(memberDto.getEmail());
//        Assertions.assertThat(createdMember.getName()).isEqualTo(memberDto.getName());
//    }
//
//    @Test
//    void updateMember() {
//    }
//
//    @Test
//    void deleteMember() {
//    }
//}