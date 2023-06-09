package com.nhnacademy.minidoorayaccountapi.member_authority.service;

import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.repository.MemberStatusRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Member Authority Service Test")
class DefaultMemberAuthorityServiceTest {
    @Autowired
    MemberAuthorityService memberAuthorityService;
    @MockBean
    MemberAuthorityRepository memberAuthorityRepository;

    private MemberAuthority MemberAuthority1;
    private MemberAuthority MemberAuthority2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        MemberAuthority1 = new MemberAuthority(1, "ADMIN");
        MemberAuthority2 = new MemberAuthority(2, "MEMBER");
    }

    @Test
    @DisplayName("회원 권한 수정")
    void updateMemberAuthority() {
        MemberAuthorityDto memberAuthorityDto = new MemberAuthorityDto(MemberAuthority1.getStatus());

        given(memberAuthorityRepository.findById(MemberAuthority2.getMemberAuthorityId())).willReturn(
                Optional.ofNullable(MemberAuthority2)
        );

        memberAuthorityService.updateMemberAuthority(MemberAuthority2.getMemberAuthorityId(), memberAuthorityDto);

        ArgumentCaptor<MemberAuthority> captor = ArgumentCaptor.forClass(MemberAuthority.class);
        verify(memberAuthorityRepository).saveAndFlush(captor.capture());

        MemberAuthority savedMemberAuthority = captor.getValue();

        assertThat(savedMemberAuthority.getStatus()).isEqualTo(memberAuthorityDto.getStatus());
    }
}