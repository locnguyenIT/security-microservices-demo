package com.ntloc.customer.util;

import com.ntloc.customer.CustomerEntity;
import com.ntloc.customer.CustomerRepository;
import com.ntloc.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static com.ntloc.customer.CustomerConstant.CUSTOMER_NOT_FOUND;
import static com.ntloc.customer.CustomerConstant.PREFERRED_USERNAME;

@Slf4j
public class SecurityUtil {

    public static CustomerEntity getCurrentCustomerLogin(CustomerRepository customerRepository) {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = jwtAuthenticationToken.getTokenAttributes().get(PREFERRED_USERNAME).toString();
        CustomerEntity customerEntity = customerRepository.findByNtIdIgnoreCase(username).orElseThrow(() ->
                new NotFoundException(CUSTOMER_NOT_FOUND));
        log.info("Current user login: ntId: {} - name: {}", customerEntity.getNtId(), customerEntity.getName());
        return customerEntity;
    }
}
