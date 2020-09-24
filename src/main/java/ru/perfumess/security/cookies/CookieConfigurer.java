package ru.perfumess.security.cookies;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CookieConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final CookieProvider authCookieProvider;

    public CookieConfigurer(CookieProvider authCookieProvider) {
        this.authCookieProvider = authCookieProvider;
    }


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        CookieFilter authCookieFilter = new CookieFilter(authCookieProvider);
        httpSecurity.addFilterBefore(authCookieFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
