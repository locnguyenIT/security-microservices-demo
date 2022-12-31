package com.ntloc.product.config;

import com.ntloc.exception.CustomOAuth2AccessDeniedHandler;
import com.ntloc.exception.CustomOAuth2AuthenticationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
                    oauth2.authenticationEntryPoint(new CustomOAuth2AuthenticationHandler());
                    oauth2.accessDeniedHandler(new CustomOAuth2AccessDeniedHandler());
                })
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JWTGrantedAuthorityConverter());
        return jwtAuthenticationConverter;
    }

}
