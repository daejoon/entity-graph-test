package com.ddoong2.entitygraphtest.hello;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyGroupRepository extends JpaRepository<MyGroup, Long> {
}
