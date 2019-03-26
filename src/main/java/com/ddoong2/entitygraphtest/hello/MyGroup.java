package com.ddoong2.entitygraphtest.hello;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @ToString
@NoArgsConstructor
@Entity
public class MyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Builder
    public MyGroup(String name) {
        this.name = name;
    }
}
