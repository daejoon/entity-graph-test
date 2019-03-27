package com.ddoong2.entitygraphtest.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void test_member를_lastname_으로_조회한다() {
        Team myTeam = Team.builder()
                .name("MyTeam")
                .build();
        myTeam = entityManager.persist(myTeam);

        Member andew = Member.builder()
                .firstName("Andew")
                .lastName("Hong")
                .team(myTeam)
                .build();
        andew = entityManager.persist(andew);

        Member brandon = Member.builder()
                .firstName("Brandon")
                .lastName("Kim")
                .team(myTeam)
                .build();
        brandon = entityManager.persist(brandon);

        Member charles = Member.builder()
                .firstName("Charles")
                .lastName("Kim")
                .team(myTeam)
                .build();
        charles = entityManager.persist(charles);

        List<Member> memberList = memberRepository.findByLastName("Kim");

        assertThat(memberList)
                .isNotEmpty()
                .hasSize(2)
                .contains(brandon, charles);
    }

    @Test
    public void test_member를_조회하는데_team도_같이_조회한다() {
        Team myTeam = Team.builder()
                .name("MyTeam")
                .build();
        myTeam = entityManager.persist(myTeam);

        Member andew = Member.builder()
                .firstName("Andew")
                .lastName("Hong")
                .team(myTeam)
                .build();
        entityManager.persist(andew);

        Member brandon = Member.builder()
                .firstName("Brandon")
                .lastName("Kim")
                .team(myTeam)
                .build();
        entityManager.persist(brandon);

        Member charles = Member.builder()
                .firstName("Charles")
                .lastName("Kim")
                .team(myTeam)
                .build();
        entityManager.persist(charles);

        List<Member> memberList = memberRepository.findByLastNameOrderById("Kim");

        assertThat(memberList)
                .isNotEmpty()
                .hasSize(2)
                .contains(brandon, charles);
    }
}