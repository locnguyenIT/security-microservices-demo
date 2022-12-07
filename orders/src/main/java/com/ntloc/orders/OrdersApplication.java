package com.ntloc.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//basePackages is scan package to fetch all Feign Client from that package
@EnableFeignClients(
)
@EnableEurekaClient
//ScanBasePackages is scan package to fetch all class from that package
@SpringBootApplication(
        scanBasePackages = {
                "com.ntloc.orders",
                "com.ntloc.exception"
        }
)
//PropertySources to use properties file to run different environments (local, docker, ...)
//@PropertySources({
//        @PropertySource("classpath:client-${spring.profiles.active}.properties")
//})
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}
