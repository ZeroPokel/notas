package com.mafv.notas.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String headerToken = request.getHeader("Authorization");

        if (headerToken != null && headerToken.startsWith("Bearer ")) {

            String token = headerToken.replace("Bearer ", "");

            UsernamePasswordAuthenticationToken gAuthenticationToken = TokenUtils.gAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(gAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
    
}
