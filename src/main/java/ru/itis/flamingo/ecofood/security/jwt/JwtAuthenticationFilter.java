package ru.itis.flamingo.ecofood.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.flamingo.ecofood.exception.UnauthorizedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtService.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtService.validateToken(token)) {
                Authentication auth = jwtService.getAuthentication(new JwtToken(token));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (UnauthorizedException ex) {
            throw ex;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
