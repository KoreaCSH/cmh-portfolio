package com.choimyeongheon.portfolio.global.security;

import com.choimyeongheon.portfolio.domain.admin.domain.Role;
import com.choimyeongheon.portfolio.domain.admin.service.AdminService;
import com.choimyeongheon.portfolio.global.security.handler.FormAccessDeniedHandler;
import com.choimyeongheon.portfolio.global.security.handler.FormAuthenticationEntryPoint;
import com.choimyeongheon.portfolio.global.security.handler.FormAuthenticationFailureHandler;
import com.choimyeongheon.portfolio.global.security.handler.FormAuthenticationSuccessHandler;
import com.choimyeongheon.portfolio.global.security.provider.FormAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AdminService adminService;
    private final UserDetailsService userDetailsService;

    // Exception 처리하기
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                        .ignoringRequestMatchers("/sign-up"))

                .formLogin(form ->
                        form.loginPage("/login/form")
                                .defaultSuccessUrl("/home")
                                .failureUrl("/error-alert")
                                .usernameParameter("userId")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .successHandler(authenticationSuccessHandler())
                                .failureHandler(authenticationFailureHandler())
                                .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))

                .httpBasic(basic -> basic.authenticationEntryPoint(authenticationEntryPoint()))
                .exceptionHandling(configurer -> configurer.accessDeniedHandler(accessDeniedHandler()))

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/error", "/css/**", "/js/**", "/images/**", "/admin/works/display", "/admin/home-images/display").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.getRole())
                        .anyRequest().permitAll())

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new FormAuthenticationProvider(userDetailsService, passwordEncoder());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new FormAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new FormAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new FormAuthenticationSuccessHandler(adminService);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new FormAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // css, image, js 파일 등은 security filter 를 거치지 않도록 설정하는 스프링 빈
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
