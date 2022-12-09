package com.ntloc.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CustomerDataSource {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            CustomerEntity user = CustomerEntity.builder()
                    .ntId("user")
                    .name("username")
                    .email("user@gmail.com").build();
            CustomerEntity aoy1hc = CustomerEntity.builder()
                    .ntId("aoy1hc")
                    .name("Nguyen Thanh Loc")
                    .email("loc.nguyenthanh2@vn.bosch.com").build();

            customerRepository.saveAll(List.of(user, aoy1hc));
        };
    }
}
