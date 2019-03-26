package com.ddoong2.entitygraphtest.hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MyGroupRepository myGroupRepository;


    @Before
    public void setUp() throws Exception {
        MyGroup myTeam = MyGroup.builder()
                .name("MyTeam")
                .build();

        myTeam = myGroupRepository.save(myTeam);

        Customer andew = Customer.builder()
                .firstName("Andew")
                .lastName("Hong")
                .myGroup(myTeam)
                .build();
        customerRepository.save(andew);

        Customer brandon = Customer.builder()
                .firstName("Brandon")
                .lastName("Kim")
                .myGroup(myTeam)
                .build();
        customerRepository.save(brandon);

        Customer charles = Customer.builder()
                .firstName("Charles")
                .lastName("Kim")
                .myGroup(myTeam)
                .build();
        customerRepository.save(charles);
    }

    @Test
    public void test_customer_lastname_으로_조회한다() {
        List<Customer> customerList = customerRepository.findByLastName("Kim");

        assertThat(customerList.size()).isGreaterThan(0);
        assertThat(customerList.get(0).getLastName()).isEqualTo("Kim");
    }

    @Test
    public void test_customer를_조회하는데_mygroup을_eager_조회한다() {
        List<Customer> customerList = customerRepository.findByLastNameOrderById("Kim");
        assertThat(customerList.size()).isGreaterThan(0);
        assertThat(customerList.get(0).getLastName()).isEqualTo("Kim");
    }
}