package com.ddoong2.entitygraphtest.hello;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager em;
    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void setUp() throws Exception {
        Team team = Team.builder()
                .name("MyTeam")
                .build();
        em.persist(team);

        Member andew = Member.builder()
                .firstName("Andew")
                .lastName("Hong")
                .team(team)
                .build();
        em.persist(andew);

        Member brandon = Member.builder()
                .firstName("Brandon")
                .lastName("Kim")
                .team(team)
                .build();
        em.persist(brandon);

        Member charles = Member.builder()
                .firstName("Charles")
                .lastName("Kim")
                .team(team)
                .build();
        em.persist(charles);

        em.flush();
        em.clear();
    }

    @Test
    public void test_member를_lastname_으로_조회한다() {
        List<Member> memberList = memberRepository.findByLastName("Kim");

        assertThat(memberList)
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    public void test_member를_lastname으로_조회하는데_team도_같이_조회한다() {
        List<Member> members = memberRepository.findByLastNameOrderById("Kim");

        assertThat(Hibernate.isInitialized(members.get(0).getTeam())).isEqualTo(true);
    }
}