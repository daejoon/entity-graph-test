package com.ddoong2.entitygraphtest.hello;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    @EntityGraph(attributePaths = "myGroup")
    List<Customer> findByLastNameOrderById(String lastName);
}
