package com.hospital.erp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          CustomAuthenticationSuccessHandler successHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {


        http

        .csrf(csrf -> csrf.disable())


        .authorizeHttpRequests(auth -> auth

                // Public pages - static resources
                .requestMatchers(
                        "/login",
                        "/register",
                        "/forgot-password",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**",
                        "/webjars/**",
                        "/dashboard.css",
                        "/dashboard.js",
                        "/*.css",
                        "/*.js"
                )
                .permitAll()


                // Admin pages
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")


                // Doctor pages
                .requestMatchers("/doctor/**")
                .hasRole("DOCTOR")


                // Patient pages
                .requestMatchers("/patient/**")
                .hasRole("PATIENT")


                // All other pages
                .anyRequest()
                .authenticated()
        )



        .userDetailsService(customUserDetailsService)



        .formLogin(login -> login

                .loginPage("/login")

                .loginProcessingUrl("/login")

                .successHandler(successHandler)

                .failureUrl("/login?error=true")

                .permitAll()
        )



        .rememberMe(remember -> remember

                .key("HospitalERPSecretKey2026")

                .tokenValiditySeconds(86400 * 30) // 30 days

                .rememberMeParameter("remember-me")

        )



        .logout(logout -> logout

                .logoutUrl("/logout")

                .logoutSuccessUrl("/login?logout=true")

                .permitAll()
        );


        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}