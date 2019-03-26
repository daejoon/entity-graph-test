package com.ddoong2.entitygraphtest.hello;

import lombok.*;

import javax.persistence.*;

@Getter @ToString @EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    private MyGroup myGroup;

    @Builder
    public Customer(String firstName, String lastName, MyGroup myGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myGroup = myGroup;
    }
}
