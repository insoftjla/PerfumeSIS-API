package ru.perfumess.security.cookies;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CookieFilter extends GenericFilterBean {

    private final CookieProvider cookieProvider;

    public CookieFilter(CookieProvider cookieProvider) {
        this.cookieProvider = cookieProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie cookie = cookieProvider.resolveToken((HttpServletRequest) servletRequest);

        if(cookie != null && cookieProvider.validateToken(cookie)){
            Authentication authentication = cookieProvider.getAuthentication(cookie);

            if (authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
