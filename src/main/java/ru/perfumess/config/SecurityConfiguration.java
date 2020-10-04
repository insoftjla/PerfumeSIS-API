package ru.perfumess.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.perfumess.security.cookies.CookieConfigurer;
import ru.perfumess.security.cookies.CookieProvider;

@Configuration
@EnableConfigurationProperties
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String MAIN_ENDPOINT = "/";
    private static String AUTHENTICATION_ENDPOINT = "/api/v1/auth/**";
    private static String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static String USER_ENDPOINT = "/api/v1/user/**";
    private static String PUBLIC_ENDPOINT = "/api/v1/public/**";
    private static String CONTENT_ENDPOINT = "/api/v1/content/**";

    private final CookieProvider authCookieProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(MAIN_ENDPOINT).permitAll()
                .antMatchers(AUTHENTICATION_ENDPOINT).permitAll()
                .antMatchers(PUBLIC_ENDPOINT).permitAll()
                .antMatchers(CONTENT_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(USER_ENDPOINT).hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .apply(new CookieConfigurer(authCookieProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
