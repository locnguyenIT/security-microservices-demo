package com.ntloc.customer.util;

import com.ntloc.customer.CustomerEntity;
import com.ntloc.customer.CustomerRepository;
import com.ntloc.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import static com.ntloc.customer.CustomerConstant.*;

@Slf4j
public class SecurityUtil {

    public static String getCurrentUsername() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication();
        if (jwt == null) {
            throw new AuthenticationCredentialsNotFoundException(CREDENTIAL_NOT_FOUND);
        }
        String preferred_username = jwt.getClaims().get(PREFERRED_USERNAME).toString();
        return preferred_username;
    }

    public static CustomerEntity getCurrentCustomerLogin(CustomerRepository customerRepository) {
        String username = getCurrentUsername();
        CustomerEntity customerEntity = customerRepository.findByNtIdIgnoreCase(username).orElseThrow(() ->
                new NotFoundException(CUSTOMER_NOT_FOUND));
        log.info("Current user login: ntId: {} - name: {}", customerEntity.getNtId(), customerEntity.getName());
        return customerEntity;
    }
}
