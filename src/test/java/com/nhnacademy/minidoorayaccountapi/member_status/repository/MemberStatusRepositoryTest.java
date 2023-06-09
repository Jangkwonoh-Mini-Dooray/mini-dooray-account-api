package com.nhnacademy.minidoorayaccountapi.member_status.repository;

import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Member Status Repository Test")
class MemberStatusRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    MemberStatusRepository memberStatusRepository;

    private MemberStatus memberStatus;

    @BeforeEach
    void setUp() {
        memberStatus = new MemberStatus(1, "가입");
    }

}