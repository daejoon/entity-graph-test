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
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void test_customer_lastname_으로_조회한다() {
        MyGroup myTeam = MyGroup.builder()
                .name("MyTeam")
                .build();
        myTeam = testEntityManager.persist(myTeam);

        Customer andew = Customer.builder()
                .firstName("Andew")
                .lastName("Hong")
                .myGroup(myTeam)
                .build();
        andew = testEntityManager.persist(andew);

        Customer brandon = Customer.builder()
                .firstName("Brandon")
                .lastName("Kim")
                .myGroup(myTeam)
                .build();
        brandon = testEntityManager.persist(brandon);

        Customer charles = Customer.builder()
                .firstName("Charles")
                .lastName("Kim")
                .myGroup(myTeam)
                .build();
        charles = testEntityManager.persist(charles);

        List<Customer> customerList = customerRepository.findByLastName("Kim");

        assertThat(customerList)
                .isNotEmpty()
                .hasSize(2)
                .contains(brandon, charles);
    }

    @Test
    public void test_customer를_조회하는데_mygroup을_eager_조회한다() {
        MyGroup myTeam = MyGroup.builder()
                .name("MyTeam")
                .build();
        myTeam = testEntityManager.persist(myTeam);

        Customer andew = Customer.builder()
                .firstName("Andew")
                .lastName("Hong")
                .myGroup(myTeam)
                .build();
        testEntityManager.persist(andew);

        Customer brandon = Customer.builder()
                .firstName("Brandon")
                .lastName("Kim")
                .myGroup(myTeam)
                .build();
        testEntityManager.persist(brandon);

        Customer charles = Customer.builder()
                .firstName("Charles")
                .lastName("Kim")
                .myGroup(myTeam)
                .build();
        testEntityManager.persist(charles);

        List<Customer> customerList = customerRepository.findByLastNameOrderById("Kim");

        assertThat(customerList)
                .isNotEmpty()
                .hasSize(2)
                .contains(brandon, charles);
    }
}