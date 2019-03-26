package com.ddoong2.entitygraphtest;

import com.ddoong2.entitygraphtest.hello.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class EntityGraphTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EntityGraphTestApplication.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MyGroupRepository myGroupRepository;

    @Override
    public void run(String... args) throws Exception {
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

    @GetMapping("/customers")
    public ResponseEntity customer() {
        ModelMapper modelMapper = new ModelMapper();

        List<Customer> customerList = customerRepository.findByLastName("Kim");
        return ResponseEntity.ok(
                customerList.stream()
                        .map(customer -> modelMapper.map(customer, CustomerDto.class))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/customers-with-team")
    public ResponseEntity customerWithTeam() {
        ModelMapper modelMapper = new ModelMapper();

        List<Customer> customerList = customerRepository.findByLastNameOrderById("Kim");
        return ResponseEntity.ok(
                customerList.stream()
                        .map(customer -> modelMapper.map(customer, CustomerWithTeamDto.class))
                        .collect(Collectors.toList())
        );
    }
}
