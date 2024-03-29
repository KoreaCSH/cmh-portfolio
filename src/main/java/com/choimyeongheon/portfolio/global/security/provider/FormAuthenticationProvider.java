package com.choimyeongheon.portfolio.global.security.provider;

import com.choimyeongheon.portfolio.global.security.service.AdminAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class FormAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        AdminAdapter adminAdapter = (AdminAdapter) userDetailsService.loadUserByUsername(userId);

        if (!passwordEncoder.matches(password, adminAdapter.getAdmin().getPassword())) {
            throw new BadCredentialsException("BadCredentials");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminAdapter.getAdmin(), null, adminAdapter.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
