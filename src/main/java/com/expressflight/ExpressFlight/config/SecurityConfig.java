package com.expressflight.ExpressFlight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);


        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT email, password, active FROM member WHERE email=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT email, role FROM role WHERE email=?"
        );


        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .antMatchers("/api/v1/flight/search-flight").hasRole("MEMBER")
                                .antMatchers("/api/v1/**").hasRole("EMPLOYEE")
                                .antMatchers("/api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticate-login")
                        .permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"))
                .logout(logout -> logout.permitAll());

        return http.build();
    }

}
