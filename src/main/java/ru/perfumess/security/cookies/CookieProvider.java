package ru.perfumess.security.cookies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.perfumess.model.Role;
import ru.perfumess.security.jwt.JwtTokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class CookieProvider {

    private static final String TOKEN_API = "token_api";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Autowired
    public CookieProvider(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    public Cookie createToken(String username, List<Role> roles) {
        Cookie result = new Cookie(TOKEN_API, jwtTokenProvider.create(username, roles));
        result.setPath("/");
        return result;
    }

    public Authentication getAuthentication(Cookie cookie) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtTokenProvider.getUsername(cookie.getValue()));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Cookie resolveToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = cookies != null
                ? Arrays.stream(cookies)
                    .filter(c -> c.getName().equals(TOKEN_API))
                    .findAny().orElse(null)
                : null;
        if (cookie != null) {
            log.debug("METHOD [resolveCookie] cookie: name {} value {}", cookie.getName(), cookie.getValue());
        } else log.debug("METHOD [resolveCookie] cookie is NULL");
        return cookie;
    }

    public boolean validateToken(Cookie cookie) {
        return jwtTokenProvider.validate(cookie.getValue());
    }

}
